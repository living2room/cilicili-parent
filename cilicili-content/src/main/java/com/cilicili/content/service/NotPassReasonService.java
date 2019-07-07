package com.cilicili.content.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cilicili.content.mapper.NotPassReasonMapper;
import com.cilicili.domain.content.NotPassReason;
@Service
public class NotPassReasonService {

	@Resource
	private NotPassReasonMapper nprMapper;
	
	
//	返回所以审核原因
	public List<NotPassReason> selectAll(){
		return nprMapper.selectList(null);
	}
}
