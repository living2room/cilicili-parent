package com.cilicili.common.dto;

import lombok.Data;

/**
 * 用来接收jqGrid表单提交的请求数据
 * @author Administrator
 *
 */
@Data
public class JqGridDto {
	private String toCreateTime;
	private String toFinalTime;
	private boolean _search;
	private String nd;
	private Integer rows;
	private Integer page;
	private String sidx;
	private String sord;
}
