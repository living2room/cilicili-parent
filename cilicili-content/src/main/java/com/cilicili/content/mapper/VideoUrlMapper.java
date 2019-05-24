/**
 * 
 */
package com.cilicili.content.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cilicili.bean.content.VideoInfo;
import com.cilicili.bean.content.VideoUrl;

/**
 * @author 李明睿 2019年5月24日
 */
@Mapper
public interface VideoUrlMapper extends BaseMapper<VideoUrl> {
}
