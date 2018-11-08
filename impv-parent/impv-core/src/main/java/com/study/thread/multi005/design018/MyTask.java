package com.study.thread.multi005.design018;

/**
 * @Description: Created by Admin on 2016/11/28.
 */
public class MyTask implements Runnable {
    private int id;
    private String name;

    public MyTask(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println("run taskId =" + this.id);
            Thread.sleep(5 * 1000);
            //System.out.println("end taskId =" + this.taskId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return Integer.toString(this.id);
    }
}
