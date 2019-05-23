package com.cilicili.comment.mapper;

import com.cilicili.comment.domain.DeleteLog;

public interface DeleteLogMapper {
    int deleteByPrimaryKey(Integer deleteLogId);

    int insert(DeleteLog record);

    int insertSelective(DeleteLog record);

    DeleteLog selectByPrimaryKey(Integer deleteLogId);

    int updateByPrimaryKeySelective(DeleteLog record);

    int updateByPrimaryKey(DeleteLog record);
}