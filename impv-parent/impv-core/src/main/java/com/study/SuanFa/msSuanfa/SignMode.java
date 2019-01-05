package com.study.SuanFa.msSuanfa;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例模式
 */
public class SignMode {
    private SignMode(){}

    /**
     * 懒汉模式
     */
    private static SignMode signLanMode = new SignMode();
    public SignMode getSignMode(){
        return signLanMode;
    }

    /**
     * 饿汉 dubbo check
     */
    private static SignMode signErMode = null;
    public static SignMode getSignErMode (){
        if(signErMode == null){
            synchronized (SignMode.class){
                if(signErMode == null){
                    signErMode = new SignMode();
                }
            }
        }
        return signErMode;
    }

    /**
     * 单例 内部类
     */
    private static class SignModeInner{
        private static SignMode sign = new SignMode();
    }
    public SignMode getSignModeInner(){
        return SignModeInner.sign;
    }


    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        Object put = map.put("cn", "cd");
        System.out.println(put.hashCode());


    }
}
