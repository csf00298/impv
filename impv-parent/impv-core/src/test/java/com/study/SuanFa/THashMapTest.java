package com.study.SuanFa;

import com.study.SuanFa.msSuanfa.THashMap;
import org.junit.Test;

/**
 * @Description:
 * @Author: Created by CaoXiaoLong on 2018/1/26.
 */
public class THashMapTest {
    @Test
    public void test(){
        THashMap hMap = new THashMap<String,Integer>();
        hMap.put("aa",12);
        hMap.put("ab",13);
        hMap.put("ac",14);
        hMap.put("aa",15);
        hMap.put(null,16);

        System.out.println(hMap);
    }
}
