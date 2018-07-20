package com.song.controller;

import com.song.config.ConfigParam;
import com.song.util.Response;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Song on 2018/2/6.
 */
@Api(tags = {"linux"})
@RestController
@Slf4j
public class LinuxCommondController {
    @Resource
    ConfigParam configParam;

    @GetMapping(value = {"/excute"})
    public Response excute(String cmd) {
        log.info("index被请求");

        try {
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();

            InputStream inputStream = process.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String s = bufferedReader.readLine();
            log.info("result->{}", s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.success();
    }
}
