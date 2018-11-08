package com.demo.threadInPro;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description: 1000条或更多数据 使用多线程入库操作
 */
public class MyTestCallable {

    @Test
    public void test() {
        //创建一个线程安全的集合 用于多线程中向集合放数据 否则会出现数据丢失的现象！
        List<Boolean> toAddUser = Collections.synchronizedList(new ArrayList<Boolean>());
        List<String> adminList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            adminList.add(String.valueOf(new Random().nextInt(100)));
        }
        bechSaveUser(adminList,toAddUser);
    }


    @Transactional()
    private boolean bechSaveUser(List<String> adminlist, List<Boolean> toAddUser) {
        if (adminlist != null){
            final List<Boolean> result = new ArrayList<>();
            System.out.println("保存开始…………………………………………………………" + Thread.currentThread().getName());
            int size = adminlist.size();
            List<Callable<Boolean>> callList = new ArrayList<Callable<Boolean>>();
            //设置默认的线程数 如果数据多于100条则修改线程数
            int nThreads = 4;
            if (size > 99) nThreads = 10;

            //设置线程池中固定的线程数
            ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
            List<Future<Boolean>> futures = new ArrayList<Future<Boolean>>();

            //拆分List中 1000条数据分给10个线程一起做 提高CPU利用率
            for (int i = 0; i < nThreads+1; i++) { //对未整除的 需要多循环一次
                //处理list拆分中 最后不成倍数的数据
                int lastSubNum = size / nThreads * (i + 1);
                int endNum = 0;
                if (nThreads-i == 0 && size -lastSubNum * nThreads < nThreads) {
                    endNum = adminlist.size();
                } else {
                    endNum = size / nThreads * (i + 1);
                }

                System.out.println("save拆分开始位置 " + size / nThreads * i + "   " + " save拆分结束位置 " + endNum);
                //注意：List.subList区间：左开右闭。 末尾不会自动剪切 需要根据情况手动计算末尾的数据
                final List<String> childTask = adminlist.subList(size / nThreads * i, endNum);
                //由于会多循环一次 最后一次循环list是空 所以要排除这种情况
                if (childTask.size() > 0) {
                    Callable<Boolean> task = new Callable<Boolean>() {
                        //启动线程
                        @Override
                        public Boolean call() throws Exception {
                            for (String str : childTask) {
                                boolean resultstatus = false;
                                //入库保存动作
//                                    resultstatus = isysuserbiz.saveSysUser(sysUser, Long.parseLong(userid));
                                System.out.println(Thread.currentThread().getName());
                                result.add(resultstatus);//如果有其他数据可以向list中放入
                                if (!resultstatus) {
                                    return false;
                                }
                            }
                            return true;
                        }
                    };
                    //					futures.add(executorService.submit(task));  //多线程任务提交方法一
                    callList.add(task); //多线程任务提交方法二
                    toAddUser.addAll(result);//批量向线程安全的集合中添加数据 防止影响性能 也能防止线程间的相互影响
                }
            }
            try {
                //任务批量提交 进一步提高性能
                futures = executorService.invokeAll(callList);
            } catch (InterruptedException e1) {
                System.out.println("任务提交失败 " + e1);
                throw new RuntimeException();
            }

            //遍历多线程执行结果 如果其中有执行失败的数据 数据库就回滚
            for (Future<Boolean> future : futures) {
                try {
                    if (!future.get()) {
                        System.out.println("保存用户失败");
//                        throw new RuntimeException();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            //释放线程池
            executorService.shutdown();
        }
        System.out.println("保存结束………………………………………………………………" + Thread.currentThread().getName());
        return true;
    }
}
