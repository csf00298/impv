package com.study.signal;

/**
 * 单例 懒汉模式
 */
public class SingletonClassForLan {
    private static SingletonClassForLan sign = null;

    private SingletonClassForLan() {

    }

    public static SingletonClassForLan getInstance() {
        if (sign == null) {
            synchronized (SingletonClassForLan.class){
                if(sign == null)
                    sign = new SingletonClassForLan();
            }
        }
        return sign;
    }

}
