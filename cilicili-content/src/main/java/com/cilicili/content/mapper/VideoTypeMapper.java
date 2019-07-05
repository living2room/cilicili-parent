/**
 * 
 */
package com.cilicili.content.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cilicili.bean.content.VideoType;

/**
 * @author 李明睿
 * 2019年5月24日
 */
@Mapper
public interface VideoTypeMapper extends BaseMapper<VideoType>{

	@Select("select * from tb_video_type,tb_type where tb_video_type.type_id=tb_type.id")
	List< VideoType> selectVideoTypeByType(Page<VideoType> page, QueryWrapper<VideoType> queryWrapperT);
}
