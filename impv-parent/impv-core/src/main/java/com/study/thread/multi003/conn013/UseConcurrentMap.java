package com.study.thread.multi003.conn013;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: Created by Admin on 2016/11/11.
 */
public class UseConcurrentMap {
    static ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        map.put("a","a");
        map.put("b","b");
        map.put("c","c");
        map.put("d","d");
        map.putIfAbsent("d","c"); //如果key存在则不做添加操作了

        for(Map.Entry<String,String> entry:map.entrySet()){
            System.out.println(entry.getKey()+"-"+entry.getValue());
        }

        Collections.synchronizedList(new ArrayList<Object>());
    }

}
