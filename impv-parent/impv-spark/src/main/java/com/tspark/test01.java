package com.tspark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * @Description:
 * @Author: Created by CaoXiaoLong on 2017/11/29.
 */
public class test01 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("my App");
        JavaSparkContext sc = new JavaSparkContext(conf);

    }
}
