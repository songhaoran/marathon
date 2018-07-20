package com.song.single;

/**
 * Created by Song on 2018/07/10.
 */
public class Singleton_3 {
    private static Singleton_3 singleton3;

    private Singleton_3() {}

    private static Singleton_3 getInstance() {
        if (singleton3 == null) {
            synchronized (Singleton_3.class){
                if (singleton3 == null) {
                    singleton3 = new Singleton_3();
                }
            }
        }
        return singleton3;
    }
}
