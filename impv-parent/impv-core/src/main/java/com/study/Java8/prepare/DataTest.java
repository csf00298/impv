package com.study.Java8.prepare;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Created by CaoXiaoLong on 2017/11/17.
 */
public class DataTest {
    public static List<String> strList = new ArrayList<>();
    public static List<Integer> intList = new ArrayList<>();
    public static List<Integer> orderList = new ArrayList<>();
    public static List<List<Integer>> intLists = new ArrayList<>();

    static {
        for(int i=0 ; i<1000; i++){
            strList.add(RandomStringUtils.random(10,20,110,true,false).toLowerCase());
            intList.add(RandomUtils.nextInt(0,99999));
            orderList.add(i);
        }
        intLists.add(orderList);
        intLists.add(intList);
    }

    public static String telJsonStr = "{\"details\" : " +
            "[{\"callDuration\" : \"51\",\"calledNum\" : \"13033352058\",\"callingNum\" : \"\",\"callTime\" : \"2017-11-03 07:26:28\"}," +
            "{\"callDuration\" : \"463\",\"calledNum\" : \"\",\"callingNum\" : \"18213427192\",\"callTime\" : \"2017-11-02 21:00:07\"}," +
            "{\"callDuration\" : \"276\",\"calledNum\" : \"18213427192\",\"callingNum\" : \"\",\"callTime\" : \"2017-11-02 20:54:04\"}," +
            "{\"callDuration\" : \"22\",\"calledNum\" : \"\",\"callingNum\" : \"18213427192\",\"callTime\" : \"2017-11-02 20:48:52\"}," +
            "{\"callDuration\" : \"1168\",\"calledNum\" : \"\",\"callingNum\" : \"15887943471\",\"callTime\" : \"2017-11-02 20:33:55\"}," +
            "{\"callDuration\" : \"0\",\"calledNum\" : \"342178\",\"callingNum\" : \"\",\"callTime\" : \"2017-11-02 19:28:14\"}," +
            "{\"callDuration\" : \"1168\",\"calledNum\" : \"15887943471\",\"callingNum\" : \"\",\"callTime\" : \"2017-11-02 20:33:55\"}]}";


    public static void main(String[] args) {
        System.out.println(strList.toString());
        System.out.println(intList.toString());
    }
}
