package com.song.common;

import org.junit.Test;

/**
 * Created by Song on 2018/07/10.
 */
public class StringTest {
    String s = new String("abc");
    String s1 = "abc";
    String s2 = new String("abc");


    @Test
    public void stringTest1() {
        System.out.println("s==s1:" + (s == s1));
        System.out.println("s==s2:" + (s == s2));
        System.out.println("s1==s2:" + (s1 == s2));

        System.out.println("s.equals(s1):" + (s.equals(s1)));
        System.out.println("s.equals(s2):" + (s.equals(s2)));
        System.out.println("s1.equals(s2):" + (s1.equals(s2)));
    }


    String hello = "hello";
    String hel = "hel";
    String lo = "lo";

    @Test
    public void stringTest2() {
        System.out.println("hello == \"hel\" + \"lo\":" + (hello == "hel" + "lo"));
        System.out.println("hello == \"hel\" + lo:" + (hello == "hel" + lo));
    }
}
