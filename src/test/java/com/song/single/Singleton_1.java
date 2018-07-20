package com.song.single;

/**
 * Created by Song on 2018/07/10.
 * 懒加载/多线程不安全/严格意义的非单例
 */
public class Singleton_1 {
    private static Singleton_1 singleton1;

    private Singleton_1() {

    }

    public static Singleton_1 getInstance() {
        if (singleton1 == null) {
            singleton1 = new Singleton_1();
        }
        return singleton1;
    }
}
