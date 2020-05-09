package com.bootdo.portal.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 购买订单表
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2019-12-02 10:06:52
 */
public class TrdOrderDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//项目名称
	private String borrowtitle;
	//项目id
	private String borrowid;
	//认购金额
	private BigDecimal investamount;
	//认购时间
	private Date investtime;
	//客户代码
	private String customno;
	//手机号
	private String mobile;
	//姓名
	private String username;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：项目名称
	 */
	public void setBorrowtitle(String borrowtitle) {
		this.borrowtitle = borrowtitle;
	}
	/**
	 * 获取：项目名称
	 */
	public String getBorrowtitle() {
		return borrowtitle;
	}
	/**
	 * 设置：项目id
	 */
	public void setBorrowid(String borrowid) {
		this.borrowid = borrowid;
	}
	/**
	 * 获取：项目id
	 */
	public String getBorrowid() {
		return borrowid;
	}
	/**
	 * 设置：认购金额
	 */
	public void setInvestamount(BigDecimal investamount) {
		this.investamount = investamount;
	}
	/**
	 * 获取：认购金额
	 */
	public BigDecimal getInvestamount() {
		return investamount;
	}
	/**
	 * 设置：认购时间
	 */
	public void setInvesttime(Date investtime) {
		this.investtime = investtime;
	}
	/**
	 * 获取：认购时间
	 */
	public Date getInvesttime() {
		return investtime;
	}
	/**
	 * 设置：客户代码
	 */
	public void setCustomno(String customno) {
		this.customno = customno;
	}
	/**
	 * 获取：客户代码
	 */
	public String getCustomno() {
		return customno;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：姓名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：姓名
	 */
	public String getUsername() {
		return username;
	}
}
