package com.study.thread.multi001.sync005;

/**
 * @Description: synchronized的重入
 */
public class SyncDubbo2 {
    static class parent{
        int count = 10;
        public synchronized void operationSup(){
            count--;
            System.out.println("Parent print count = " + count);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class children extends parent{
        public synchronized void operationSub(){
            while (count>0){
                try {
                    count--;
                    System.out.println("Children print count = "+count);
                    Thread.sleep(100);
                    this.operationSup();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        final children demo = new children();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                demo.operationSub();
            }
        });
        t.start();
    }
}
