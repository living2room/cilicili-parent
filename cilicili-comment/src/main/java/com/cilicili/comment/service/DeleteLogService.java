package com.cilicili.comment.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cilicili.comment.domain.DeleteLog;
import com.cilicili.comment.domain.Like;
import com.cilicili.comment.mapper.DeleteLogMapper;

@Service
public class DeleteLogService {
	@Resource
	private DeleteLogMapper deleteLogMapper;
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<DeleteLog> findAll(){
		try {
			return this.deleteLogMapper.selectList(null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public DeleteLog findById(int deleteLogId){
		try {
			return this.deleteLogMapper.selectById(deleteLogId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public void insert(DeleteLog deleteLog) {
		try {
			this.deleteLogMapper.insert(deleteLog);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
	
	/*
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int updateById(DeleteLog deleteLog) {
		try {
			//return this.deleteLogMapper.update(deleteLog, null);
			return this.deleteLogMapper.updateById(deleteLog);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
	*/
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int updateById(int deleteLogId) {
		try {
			DeleteLog deleteLog=this.deleteLogMapper.selectById(deleteLogId);
			return this.deleteLogMapper.updateById(deleteLog);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public void deleteById(int deleteLogId){
		try {
			this.deleteLogMapper.deleteById(deleteLogId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
}
