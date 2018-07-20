package com.song.single;

/**
 * Created by Song on 2018/07/10.
 */
public class Singleton_2 {
    private static Singleton_2 singleton2 = new Singleton_2();

    private Singleton_2() {}

    public static Singleton_2 getinstance() {
        return singleton2;
    }
}
