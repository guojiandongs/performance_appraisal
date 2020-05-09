package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 基金表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-27 17:02:57
 */
public class FundDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private String id;
	//标的名称
	private String productName;
	//年华利率
	private String annualrate;
	//标注
	private String content;
	//标的链接
	private String productUrl;
	//创建时间
	private Date createDate;
	//创建人
	private String author;
	//作者
	private String name;
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
	 * 设置：标的名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取：标的名称
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置：年华利率
	 */
	public void setAnnualrate(String annualrate) {
		this.annualrate = annualrate;
	}
	/**
	 * 获取：年华利率
	 */
	public String getAnnualrate() {
		return annualrate;
	}
	/**
	 * 设置：标注
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：标注
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：标的链接
	 */
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}
	/**
	 * 获取：标的链接
	 */
	public String getProductUrl() {
		return productUrl;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：创建人
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取：创建人
	 */
	public String getAuthor() {
		return author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
