package com.bootdo.vote.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 资产确认表
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-02-13 16:01:18
 */
public class MailRecognitionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String custid;
	//
	private String openid;
	//姓名
	private String chineseName;
	//证件类型 1-身份证号
	private String documentType;
	//证件号
	private String documentId;
	//邮箱
	private String mail;
	//记录时间
	private Date recordsDate;

	/**
	 * 设置：
	 */
	public void setCustid(String custid) {
		this.custid = custid;
	}
	/**
	 * 获取：
	 */
	public String getCustid() {
		return custid;
	}
	/**
	 * 设置：
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * 获取：
	 */
	public String getOpenid() {
		return openid;
	}
	/**
	 * 设置：姓名
	 */
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	/**
	 * 获取：姓名
	 */
	public String getChineseName() {
		return chineseName;
	}
	/**
	 * 设置：证件类型 1-身份证号
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	/**
	 * 获取：证件类型 1-身份证号
	 */
	public String getDocumentType() {
		return documentType;
	}
	/**
	 * 设置：证件号
	 */
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	/**
	 * 获取：证件号
	 */
	public String getDocumentId() {
		return documentId;
	}
	/**
	 * 设置：邮箱
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * 获取：邮箱
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * 设置：记录时间
	 */
	public void setRecordsDate(Date recordsDate) {
		this.recordsDate = recordsDate;
	}
	/**
	 * 获取：记录时间
	 */
	public Date getRecordsDate() {
		return recordsDate;
	}
}
