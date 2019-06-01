package com.cilicili.comment.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cilicili.comment.domain.DeleteLog;

@Mapper
public interface DeleteReasonMapper extends BaseMapper<DeleteLog>{
}