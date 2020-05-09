package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 轮播图
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-26 18:03:03
 */
public class RcharDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private String id;
	//类型名称 轮播图
	private String cname;
	//wechat-微信端 pc-电脑端
	private String plantType;
	//文件名称
	private String fileName;
	//文件路径
	private String fileUrl;
	//图片链接
	private String url;
	//创建时间
	private Date createTime;
	//排序
	private Integer sort;

	/**
	 * 设置：主键
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：类型名称 轮播图
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}
	/**
	 * 获取：类型名称 轮播图
	 */
	public String getCname() {
		return cname;
	}
	/**
	 * 设置：wechat-微信端 pc-电脑端
	 */
	public void setPlantType(String plantType) {
		this.plantType = plantType;
	}
	/**
	 * 获取：wechat-微信端 pc-电脑端
	 */
	public String getPlantType() {
		return plantType;
	}
	/**
	 * 设置：文件名称
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 获取：文件名称
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * 设置：文件路径
	 */
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	/**
	 * 获取：文件路径
	 */
	public String getFileUrl() {
		return fileUrl;
	}
	/**
	 * 设置：图片链接
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：图片链接
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSort() {
		return sort;
	}
}
