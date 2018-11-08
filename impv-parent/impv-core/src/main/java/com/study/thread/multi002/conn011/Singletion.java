package com.study.thread.multi002.conn011;

/**
 * @Description: Created by Admin on 2016/11/11.
 */
public class Singletion {

}

/**
 * 内部类单例模式 适应多线程 高并发 效率较高
 */
class Danli {
    private static class InnerSignal {
        private static Danli instance = new Danli();
    }

    private Danli getInstance() {
        return InnerSignal.instance;
    }
}


/**
 * 懒汉
 */
class Lhan {
    private static Lhan signal = null;

    public Lhan getSignal() {
        if (signal == null) {
            return new Lhan();
        }
        return signal;
    }
}

/**
 * 恶汉
 */
class Ehan {
    private static Ehan signal = new Ehan();

    private Ehan() {
    }

    private Ehan getSignal() {
        return signal;
    }
}
