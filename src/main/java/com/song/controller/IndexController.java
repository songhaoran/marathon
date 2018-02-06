package com.song.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Song on 2018/2/6.
 */
@Api(tags = {"首页"})
@Controller
public class IndexController {

    @GetMapping(value = {"/","/index","/index.ftl"})
    public String index() {
        return "index";
    }
}
