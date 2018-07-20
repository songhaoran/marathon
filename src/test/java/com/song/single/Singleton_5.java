package com.song.single;

/**
 * Created by Song on 2018/07/10.
 */
public class Singleton_5 {
    private static class SingletonBuilder{
        private static Singleton_5 singleton5 = new Singleton_5();
    }

    public static Singleton_5 getInstance() {
        return SingletonBuilder.singleton5;
    }
}
