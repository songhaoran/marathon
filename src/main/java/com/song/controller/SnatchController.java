package com.song.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.song.bean.req.ArticleSourceDeleteReq;
import com.song.bean.req.ArticleSourceEditReq;
import com.song.bean.req.ArticleSourceStatusReq;
import com.song.bean.table.ArticleSource;
import com.song.service.SnatchOperateService;
import com.song.service.SnatchQueryService;
import com.song.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Song on 2018/03/19.
 */
@Api(tags = {"snacth"}, description = "爬虫")
@Controller
@RequestMapping(produces = "application/json;charset=UTF-8")
@Slf4j
public class SnatchController {
    @Resource
    private SnatchQueryService snatchQueryService;
    @Resource
    private SnatchOperateService snatchOperateService;


    @RequestMapping(value = "/ultron/snatch", method = RequestMethod.POST)
    @ApiOperation("保存爬取详情")
    @ResponseBody
    public Response<ArticleSourceEditReq> save(HttpServletRequest request,
//                                        @RequestHeader(value = "ul_token") String ul_token,
//                                        @RequestHeader(value = "ul_secret") String ul_secret,
                                               @RequestBody ArticleSourceEditReq edit) {
        log.info("[save] edit={}", JSON.toJSONString(edit));
//        Integer advisorId = sessionCache.getLoginUserIdFromSession(request);
        Response<ArticleSourceEditReq> response = snatchOperateService.save(edit);
        return response;
    }

    @RequestMapping(value = "/ultron/snatch/active", method = RequestMethod.POST)
    @ApiOperation("停用/启用")
    @ResponseBody
    public Response changeActive(HttpServletRequest request,
//                                 @RequestHeader(value = "ul_token") String ul_token,
//                                 @RequestHeader(value = "ul_secret") String ul_secret,
                                 @RequestBody ArticleSourceStatusReq req) {
        log.info("[changeActive] req={}", JSON.toJSONString(req));
//        Integer advisorId = sessionCache.getLoginUserIdFromSession(request);
        Integer advisorId = 1;
        Response response = snatchOperateService.changeStatus(req.getSourceId(), req.getIsActive());
        return response;
    }

    @RequestMapping(value = "/ultron/snatch", method = RequestMethod.DELETE)
    @ApiOperation("删除")
    @ResponseBody
    public Response delete(HttpServletRequest request,
//                                 @RequestHeader(value = "ul_token") String ul_token,
//                                 @RequestHeader(value = "ul_secret") String ul_secret,
                           @RequestBody ArticleSourceDeleteReq req) {
        log.info("[delete] req={}", JSON.toJSONString(req));
//        Integer advisorId = sessionCache.getLoginUserIdFromSession(request);
        Integer advisorId = 1;
        Response response = snatchOperateService.delete(req.getSourceId());
        return response;
    }

    @RequestMapping(value = "/ultron/snatch/start", method = RequestMethod.GET)
    @ApiOperation("爬取")
    @ResponseBody
    public Response startSnatch(HttpServletRequest request,
//                                @RequestHeader(value = "ul_token") String ul_token,
//                                @RequestHeader(value = "ul_secret") String ul_secret,
                                @RequestParam List<Integer> sourceIdList) {
        log.info("[startSnatch] sourceIdList={}", JSON.toJSONString(sourceIdList));
//        Integer advisorId = sessionCache.getLoginUserIdFromSession(request);
        Integer advisorId = 1;
        Response response = snatchOperateService.startSnatch(sourceIdList);
        return response;
    }

    @RequestMapping(value = "/ultron/snatch/list", method = RequestMethod.GET)
    @ApiOperation("爬虫列表")
    public String list(HttpServletRequest request,
                       Model model,
//                       @RequestHeader(value = "ul_token") String ul_token,
//                       @RequestHeader(value = "ul_secret") String ul_secret,
                       @RequestParam(value = "page_num") Integer pageNum,
                       @RequestParam(value = "page_size") Integer pageSize) {
        log.info("[list] page_num={},page_num={}", pageNum, pageSize);
//        LoginUser loginUser = sessionCache.getLoginUserFromSession(request);
        PageInfo<ArticleSource> page = snatchQueryService.page(pageNum, pageSize);
        model.addAttribute("page", page);
        return "snatch/articleSourceList";
    }

    @RequestMapping(value = "/ultron/snatch/edit", method = RequestMethod.GET)
    @ApiOperation("爬虫修改页面")
    public String toEdit(HttpServletRequest request,
                         Model model,
//                       @RequestHeader(value = "ul_token") String ul_token,
//                       @RequestHeader(value = "ul_secret") String ul_secret,
                         @RequestParam(value = "source_id") Integer sourceId) {
        log.info("[list] source_id={}", sourceId);
//        LoginUser loginUser = sessionCache.getLoginUserFromSession(request);
        ArticleSourceEditReq edit = snatchQueryService.detail(sourceId);
        model.addAttribute("obj", edit);
        return "snatch/articleSourceEdit";
    }

    @RequestMapping(value = "/ultron/snatch/create", method = RequestMethod.GET)
    @ApiOperation("爬虫修改页面")
    public String toEdit() {
        return "snatch/articleSourceEdit";
    }
}
