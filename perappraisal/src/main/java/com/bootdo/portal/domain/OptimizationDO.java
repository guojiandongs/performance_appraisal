package com.bootdo.portal.domain;

import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 理财优选
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2019-11-28 09:56:11
 */
public class OptimizationDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//项目名称
	private String borrowtitle;
	//挂牌价格（元）
	private BigDecimal borrowamount;
	//剩余可投份额
	private BigDecimal hasinvestamount;
	//合同项下应收账款余额（元）
	private BigDecimal receivableamount;
	//本次应收账款待收总额（元）
	private BigDecimal duein;
	//保证金（元）
	private BigDecimal bond;
	//项目链接
	private String url;
	//公告期限开始日期
	private Date noticeperiodstart;
	//公告期限结束日期
	private Date noticeperiodend;
	//剩余可投百分比(临时字段)
	@Transient
	private BigDecimal percentage ;

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
	 * 设置：挂牌价格（元）
	 */
	public void setBorrowamount(BigDecimal borrowamount) {
		this.borrowamount = borrowamount;
	}
	/**
	 * 获取：挂牌价格（元）
	 */
	public BigDecimal getBorrowamount() {
		return borrowamount;
	}
	/**
	 * 设置：剩余可投份额
	 */
	public void setHasinvestamount(BigDecimal hasinvestamount) {
		this.hasinvestamount = hasinvestamount;
	}
	/**
	 * 获取：剩余可投份额
	 */
	public BigDecimal getHasinvestamount() {
		return hasinvestamount;
	}
	/**
	 * 设置：合同项下应收账款余额（元）
	 */
	public void setReceivableamount(BigDecimal receivableamount) {
		this.receivableamount = receivableamount;
	}
	/**
	 * 获取：合同项下应收账款余额（元）
	 */
	public BigDecimal getReceivableamount() {
		return receivableamount;
	}
	/**
	 * 设置：本次应收账款待收总额（元）
	 */
	public void setDuein(BigDecimal duein) {
		this.duein = duein;
	}
	/**
	 * 获取：本次应收账款待收总额（元）
	 */
	public BigDecimal getDuein() {
		return duein;
	}
	/**
	 * 设置：保证金（元）
	 */
	public void setBond(BigDecimal bond) {
		this.bond = bond;
	}
	/**
	 * 获取：保证金（元）
	 */
	public BigDecimal getBond() {
		return bond;
	}
	/**
	 * 设置：项目链接
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：项目链接
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：公告期限开始日期
	 */
	public void setNoticeperiodstart(Date noticeperiodstart) {
		this.noticeperiodstart = noticeperiodstart;
	}
	/**
	 * 获取：公告期限开始日期
	 */
	public Date getNoticeperiodstart() {
		return noticeperiodstart;
	}
	/**
	 * 设置：公告期限结束日期
	 */
	public void setNoticeperiodend(Date noticeperiodend) {
		this.noticeperiodend = noticeperiodend;
	}
	/**
	 * 获取：公告期限结束日期
	 */
	public Date getNoticeperiodend() {
		return noticeperiodend;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}
}
