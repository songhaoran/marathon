package com.song.service.snatch.pipeline;

import com.alibaba.fastjson.JSON;

import com.song.bean.table.Article;
import com.song.dao.ArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by Song on 2018/2/27.
 */
@Component
@Slf4j
public class WeixinArticlePipeline implements Pipeline {
    @Resource
    private ArticleMapper articleMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> params = resultItems.getAll();
        log.info("[process][文章爬取：params={}]", JSON.toJSONString(params));

        Object url = params.get("url");
        Object title = params.get("title");
        Object subscriptionAccounts = params.get("subscriptionAccounts");
        Object richMediaContent = params.get("richMediaContent");
        Object date = params.get("date");
        if (title != null && richMediaContent != null) {
            Article article = new Article();
            article.setTitle(title.toString());
            if (url != null) {
//            String sourceUrl = new String(Base64.getEncoder().encode(url.toString().getBytes()));
                String sourceUrl = url.toString();
                //避免重复抓取
                List<Article> old = articleMapper.selectByTitle(title.toString());
                if (old.size() > 0) {
                    return;
                }
                article.setSourceUrl(sourceUrl);
            }

            try {
                article.setCreateAt(DateUtils.parseDate(date.toString(), "yyyy-MM-dd"));
            } catch (Exception e) {
                log.error("", e);
            }

            int i = articleMapper.insertSelective(article);
            if (i < 1) {
                log.error("[WeixinArticlePipeline.process][存储文章失败!article={}]", JSON.toJSONString(article));
                return;
            }
        }
    }
}
