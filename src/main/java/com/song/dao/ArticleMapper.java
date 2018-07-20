package com.song.dao;

import com.song.bean.table.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper extends SongMapper<Article> {
    @Select("select * from article where title='${title}'")
    List<Article> selectByTitle(@Param("title") String title);
}