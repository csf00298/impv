package com.impv.crawler.jd.mycrawler;


import java.io.IOException;

/**
 */
public class BeginTask {
    public static void main(String[] args) {
        MyCrawler crawler = new MyCrawler();
        try {
            crawler.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
