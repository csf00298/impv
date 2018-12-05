package com.study.SuanFa.msSuanfa;

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
}
