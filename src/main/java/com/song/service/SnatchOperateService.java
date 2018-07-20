package com.song.service;

import com.alibaba.fastjson.JSON;

import com.song.bean.req.ArticleSourceEditReq;
import com.song.bean.table.ArticleSource;
import com.song.dao.ArticleSourceMapper;
import com.song.service.snatch.pageprocesser.WeixinArticlePageProcessor;
import com.song.service.snatch.pipeline.WeixinArticlePipeline;
import com.song.util.Response;
import com.song.util.enums.ArticleSnatchTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Song on 2018/03/19.
 */
@Service
@Slf4j
public class SnatchOperateService {
    @Resource
    ArticleSourceMapper articleSourceMapper;
    @Resource
    WeixinArticlePipeline weixinArticlePipeline;

    ArrayBlockingQueue<Integer> snatchingSourceIdList = new ArrayBlockingQueue<Integer>(20);


    @Transactional(rollbackFor = Exception.class)
    public Response<ArticleSourceEditReq> save(ArticleSourceEditReq edit) {
        if (edit == null) {
            log.error("[saveArticle][未提供必要参数！articleSource={}]", JSON.toJSONString(edit));
            throw new IllegalArgumentException("保存失败！");
        }

        ArticleSource articleSource = new ArticleSource();
        BeanUtils.copyProperties(edit, articleSource);

        int i;
        if (articleSource.getId() == null) {
            articleSource.setCreateAt(Calendar.getInstance().getTime());
            i = articleSourceMapper.insertSelective(articleSource);
        } else {
            articleSource.setUpdateAt(Calendar.getInstance().getTime());
            i = articleSourceMapper.updateByPrimaryKeySelective(articleSource);
        }

        if (i < 1) {
            log.error("[save][保存失败!articleSource={}]", JSON.toJSONString(articleSource));
            Response.failed("保存失败!");
        }

        return Response.success(articleSource);
    }


    @Transactional(rollbackFor = Exception.class)
    public Response changeStatus(Integer sourceId, Boolean isActive) {
        if (sourceId == null || isActive == null) {
            log.error("[changeStatus][未提供必要参数！sourceId={},isActive={}]", sourceId, isActive);
            throw new IllegalArgumentException("操作失败！");
        }

        int i = articleSourceMapper.updateIsActiveById(sourceId, isActive == true ? 1 : 0);
        if (i < 1) {
            log.error("[changeStatus][修改失败!sourceId={},isActive={}]", sourceId, isActive);
            Response.failed("修改失败!");
        }

        return Response.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public Response delete(Integer sourceId) {
        if (sourceId == null) {
            log.error("[delete][未提供必要参数！sourceId={}]", sourceId);
            throw new IllegalArgumentException("操作失败！");
        }

        int i = articleSourceMapper.deleteByPrimaryKey(sourceId);
        if (i < 1) {
            log.error("[delete][删除失败!sourceId={},isActive={}]", sourceId);
            Response.failed("删除失败!");
        }

        return Response.success();
    }

    private String SOGOU_WEIXIN_SEARCH_PREFIX = "http://weixin.sogou.com/weixin?type=1&s_from=input&query=";
    private String SOGOU_WEIXIN_SEARCH_SUFFIX = "&ie=utf8&_sug_=n&_sug_type_=";

    @Transactional(rollbackFor = Exception.class)
    public Response startSnatch(List<Integer> sourceIdList) {
        if (sourceIdList == null) {
            log.error("[startSnatch][未提供必要参数！sourceIdList 为 null]");
            throw new IllegalArgumentException("推荐失败！");
        }
        if (sourceIdList.size() == 0) {
            return Response.success();
        }

        synchronized (this) {
            for (Integer sourceId : sourceIdList) {
                if (snatchingSourceIdList.contains(sourceId)) {
                    return Response.failed("爬取中，请勿重复操作！");
                }
            }
            snatchingSourceIdList.addAll(sourceIdList);
        }

        for (Integer sourceId : sourceIdList) {
            ArticleSource articleSource = articleSourceMapper.selectByPrimaryKey(sourceId);
            if (articleSource == null || articleSource.getIsActive() == null || articleSource.getIsActive() == false) {
                continue;
            }

            Integer snatchType = articleSource.getSourceType();
            if (ArticleSnatchTypeEnum.wx_offical_account.getType() == snatchType) {
                if (StringUtils.isBlank(articleSource.getName())) {
                    continue;
                }

                String url = SOGOU_WEIXIN_SEARCH_PREFIX + articleSource.getName() + SOGOU_WEIXIN_SEARCH_SUFFIX;
                if (StringUtils.isNotBlank(articleSource.getSnatchUrl())) {
                    url = articleSource.getSnatchUrl();
                }

                Spider spider = new Spider(new WeixinArticlePageProcessor());
                spider.addPipeline(weixinArticlePipeline)
                        .addUrl(url)
                        .thread(1)
                        .run();

                while (true) {
                    if (spider.getStatus() == Spider.Status.Stopped) {
                        break;
                    }
                }
                articleSource.setLastSnatchTime(Calendar.getInstance().getTime());
                articleSourceMapper.updateByPrimaryKeySelective(articleSource);
            }
        }
        snatchingSourceIdList.removeAll(sourceIdList);
        return Response.success();
    }

}
