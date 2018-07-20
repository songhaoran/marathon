package com.song.single;

/**
 * Created by Song on 2018/07/10.
 */
public enum SingletonEnum {
    INSTANCE;

    private Singleton_6 instance;

    SingletonEnum() {
        this.instance = new Singleton_6();
    }

    public Singleton_6 getInstance() {
        return instance;
    }
}
