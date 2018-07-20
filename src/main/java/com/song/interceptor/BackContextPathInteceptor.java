package com.song.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Song on 2018/04/20.
 */
@Configuration
@Slf4j
public class BackContextPathInteceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //本地
        String scheme = request.getScheme();
        int port = request.getServerPort();
//        String basePath = scheme + "://" + serverName + ":" + port;

        //非本地
        String serverName = request.getServerName();
        log.info("serverName={}", serverName);
        String basePath = "http://" + serverName;
        if (modelAndView == null) {
            modelAndView = new ModelAndView();
        }
        modelAndView.addObject("base", basePath);
    }
}
