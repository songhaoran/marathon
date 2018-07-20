package com.song.dao;


import com.song.bean.table.ArticleSource;
import com.song.query.ArticleSourceQuery;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Song on 2018/03/16.
 */
@Repository
public interface ArticleSourceMapper extends SongMapper<ArticleSource> {

    @Update("UPDATE article_source SET is_active=#{is_active} WHERE id=#{source_id}")
    int updateIsActiveById(@Param("source_id") Integer sourceId, @Param("is_active") Integer isActive);

    List<ArticleSource> listByQuery(ArticleSourceQuery query);

}