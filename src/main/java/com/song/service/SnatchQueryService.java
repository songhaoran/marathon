package com.song.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.song.bean.req.ArticleSourceEditReq;
import com.song.bean.table.ArticleSource;
import com.song.dao.ArticleSourceMapper;
import com.song.query.ArticleSourceQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Song on 2018/03/19.
 */
@Service
@Slf4j
public class SnatchQueryService {
    @Resource
    ArticleSourceMapper articleSourceMapper;

    public PageInfo<ArticleSource> page(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 20 : pageSize;

        ArticleSourceQuery articleSourceQuery = new ArticleSourceQuery();
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleSource> list = articleSourceMapper.listByQuery(articleSourceQuery);
        PageInfo<ArticleSource> page = new PageInfo<>(list);
        return page;
    }


    public ArticleSourceEditReq detail(Integer sourceId) {
        if (sourceId == null) {
            log.error("[detail][未提供必要参数！sourceId={}]", sourceId);
            throw new IllegalArgumentException("未提供必要参数！");
        }

        ArticleSource articleSource = articleSourceMapper.selectByPrimaryKey(sourceId);
        if (articleSource == null) {
            articleSource = new ArticleSource();
        }
        ArticleSourceEditReq edit = new ArticleSourceEditReq();
        BeanUtils.copyProperties(articleSource, edit);
        return edit;
    }

}
