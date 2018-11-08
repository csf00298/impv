package com.study.thread.multi001.sync005;

/**
 * @Description: synchronized异常 遇到RuntimeException 线程结束
 */
public class SyncException {
     volatile int  count = 20;
    public synchronized void operation() {
        while(--count >=0){
            System.out.println("count num "+count);
            try {
                Thread.currentThread().sleep(100);
                if(count == 10) {
                    try {
                        Integer.parseInt("a");
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
//                        throw new RuntimeException();
                        continue;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final SyncException demo = new SyncException();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                demo.operation();
            }
        });
        t.start();
    }
}
