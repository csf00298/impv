package com.study.signal;

/**
 * @Description:单例 恶汉模式.
 */
public class SingletonClass {
    private static final SingletonClass sign = new SingletonClass();

    private SingletonClass() {
    }

    public static SingletonClass getInstance() {
        return sign;
    }
}
