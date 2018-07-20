package com.song.controller;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import com.song.config.ConfigParam;
import com.song.util.Html2ImgUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Song on 2018/2/6.
 */
@Api(tags = {"首页"})
@Controller
@Slf4j
public class IndexController {
    @Resource
    ConfigParam configParam;

    @GetMapping(value = {"/", "/index", "/index.ftl"})
    public String index() {
        log.info("index被请求");
        return "index";
    }

    @GetMapping(value = "/get_production")
    @ResponseBody
    public Boolean getProduction() {
        Boolean apns_production = configParam.getApns_production();
        return apns_production;
    }


//    @GetMapping(value = {"/gnerate"})
//    @ResponseBody
//    public String ge(String url) {
//        NativeInterface.open();
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                // SWT组件转Swing组件，不初始化父窗体将无法启动webBrowser
//                JFrame frame = new JFrame("以DJ组件保存指定网页截图");
//                // 加载指定页面，最大保存为640x480的截图
//                frame.getContentPane().add(
//                        new Html2ImgUtil(url, 1024, 768),
//                        BorderLayout.CENTER);
//                frame.setSize(1024, 768);
//                // 仅初始化，但不显示
//                frame.invalidate();
//                frame.pack();
//                frame.setVisible(false);
//            }
//        });
//        NativeInterface.runEventPump();
//        return "";
//    }
}
