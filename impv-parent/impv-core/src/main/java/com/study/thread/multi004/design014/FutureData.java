package com.study.thread.multi004.design014;

/**
 * 代理对象
 */
public class FutureData implements GetData {
    private RealData realData;
    boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        //如果已经装载完毕了，就直接返回
        if(isReady){
            return;
        }
        //如果没装载，进行装载真实对象
        this.realData = realData;
        isReady = true;
        //进行通知
        notify();
    }

    @Override
    public synchronized String getRequest() {
        //如果没装载好 程序就一直处于阻塞状态
        while (!isReady){
            try {
                System.out.println("111111111111111");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //装载好直接获取数据即可
        return this.realData.getRequest();
    }
}
