//package com.song.reacor;
//
//import org.junit.Test;
//
//import java.util.List;
//
///**
// * Created by Song on 2019/08/19.
// */
//public class ReactorTest {
//
//    @Test
//    public void test1() {
//        Mono<String> mono =  Mono.justOrEmpty(request.queryParam("data"))
//                .defaultIfEmpty("this is null")
//                .map(it -> it.concat("! from server webflux!"));
//        return ServerResponse.ok().body(mono,String.class);
//    }
//}
