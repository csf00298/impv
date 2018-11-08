package com.study.thread.multi001.sync006;

/**
 * @Description: 死锁问题，在设计程序时就应该避免双方相互持有对方的锁的情况
 */
public class DeadLock extends Thread{
    private String lock;
    //如果没有用static修饰 每new一个Tthread062 都会产生一个obj对象 就不会产生死锁 如果用static修饰会产生唯一的引用 可以死锁
    private static Object obj1 = new Object();
    private static Object obj2 = new Object();

    public void setLock(String lock) {
        this.lock = lock;
    }

    @Override
    public void run(){
        if(lock.equals("a")){
            synchronized (obj1){ //线程启动时 线程1持有obj1对象 再0.5s时 线程2持有obj1对象
                System.out.println(Thread.currentThread().getName() + " 进入lock1执行");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2){//2s以后 线程1需要obj2 但对象obj2已经被占用 线程进入死锁
                    System.out.println(Thread.currentThread().getName() + " 进入lock2执行");
                }
            }
        }
        if(lock.equals("b")){
            synchronized (obj2){//再0.5s时 线程1持有obj1对象
                System.out.println(Thread.currentThread().getName() + " 进入lock2执行");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj1){//2s以后 线程1需要obj1 但对象obj1已经被占用 线程进入死锁
                    System.out.println(Thread.currentThread().getName() + " 进入lock1执行");
                }
            }
        }
    }


    public static void main(String[] args) {
        DeadLock demo1 = new DeadLock();
        demo1.setLock("a");
        DeadLock demo2 = new DeadLock();
        demo2.setLock("b");
        Thread t1 = new Thread(demo1,"TT1");
        Thread t2 = new Thread(demo2,"TT2");

        t1.start();
        try {
            Thread.sleep(500); //线程进入死锁
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
