package com.study.thread.multi004.design014;

/**
 * @Description: Created by Admin on 2016/11/21.
 */
public class Main {
    public static void main(String[] args) {
        FutureClient fc = new FutureClient();
        GetData futureGetData = fc.request("请求参数");

        System.out.println("请求发送成功!");
        System.out.println("做其他的事情...");

        String result = futureGetData.getRequest();
        System.out.println(result);
    }
}
