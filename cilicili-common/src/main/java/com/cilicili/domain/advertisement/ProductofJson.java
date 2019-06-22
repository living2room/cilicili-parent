package com.cilicili.domain.advertisement;

import java.util.List;

import lombok.Data;
/**
 * 服务器端返回的json传输对象
 * @author Administrator
 *
 */
@Data
public class ProductofJson {
	/**
	 * 实际数据的数组
	 */
	List rows;
	/**
	 * 总页数
	 */
	private int total;
	/**
	 * 当前页
	 */
	private int page;
	/**
	 * 查下你的记录数
	 */
	private int records;
}
