package com.song.dao;

import tk.mybatis.mapper.common.base.delete.DeleteByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;
import tk.mybatis.mapper.common.base.select.SelectByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper;

public interface SongMapper<T> extends InsertSelectiveMapper<T>,
        UpdateByPrimaryKeySelectiveMapper<T>, DeleteByPrimaryKeyMapper<T>, UpdateByPrimaryKeyMapper<T>,
        SelectByPrimaryKeyMapper<T> {
}
