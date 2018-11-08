package com.study.signal;

/**
 * @Description:单例.
 */
public class SingletonClass  {
    private static class IntanceSingletonClass{
        private static final SingletonClass instance = new SingletonClass();
    }

    private SingletonClass(){}

    public static SingletonClass returnIns(){
        return IntanceSingletonClass.instance;
    }
}
