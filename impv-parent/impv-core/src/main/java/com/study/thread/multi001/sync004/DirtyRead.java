package com.study.thread.multi001.sync004;

/**
 * @Description: 脏读
 * 业务整体需要使用完整的synchronized，保持业务的原子性。
 */
public class DirtyRead {
    private String name = "normalName";
    private String pwd = "normalPwd";
    /*synchronized*/
    public void setInfo(String name,String pwd){
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.pwd = pwd;
        System.out.println("setInfo  setValue最终结果：username = " + name + " , password = " + pwd);
    }

    /*synchronized*/
    public String getPwd(){
        System.out.println("getValue方法得到：username = " + name + " , password = " + pwd);
        return pwd;
    }

    public static void main(String[] args) throws InterruptedException {
        final DirtyRead t = new DirtyRead();
        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.setInfo("zs","123456");
            }
        });
        t0.start();

        Thread.sleep(1000);

        t.getPwd();
    }
}
