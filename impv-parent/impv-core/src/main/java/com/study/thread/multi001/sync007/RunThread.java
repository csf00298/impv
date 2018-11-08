package com.study.thread.multi001.sync007;

/**
 * @Description: volatile 对所有线程可见 保证所有线程的一致性
 */
public class RunThread extends Thread{
    private volatile boolean isRunning = true;

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run(){
        System.out.println("进入run方法..");
        while (isRunning){

        }
        System.out.println("线程停止");
    }

    public static void main(String[] args) throws InterruptedException {
        RunThread demo = new RunThread();
        demo.start();
        Thread.sleep(1000);
        demo.setIsRunning(false);
        System.out.println("isRunning的值已经被设置了false");
    }
}
