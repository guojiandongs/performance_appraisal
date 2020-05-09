package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 微信配置表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-02 15:09:48
 */
public class VerifyDataDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String appid;
	//
	private String secret;
	private String accessToken;
	//
	private String jsapiTicket;
	//签名
	private String signature;
	//
	private String identi;

	/**
	 * 设置：
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}
	/**
	 * 获取：
	 */
	public String getAppid() {
		return appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	/**
	 * 设置：
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	/**
	 * 获取：
	 */
	public String getAccessToken() {
		return accessToken;
	}
	/**
	 * 设置：
	 */
	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}
	/**
	 * 获取：
	 */
	public String getJsapiTicket() {
		return jsapiTicket;
	}
	/**
	 * 设置：签名
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
	/**
	 * 获取：签名
	 */
	public String getSignature() {
		return signature;
	}
	/**
	 * 设置：
	 */
	public void setIdenti(String identi) {
		this.identi = identi;
	}
	/**
	 * 获取：
	 */
	public String getIdenti() {
		return identi;
	}

	@Override
	public String toString() {
		return "VerifyDataDO{" +
				"appid='" + appid + '\'' +
				", secret='" + secret + '\'' +
				", accessToken='" + accessToken + '\'' +
				", jsapiTicket='" + jsapiTicket + '\'' +
				", signature='" + signature + '\'' +
				", identi='" + identi + '\'' +
				'}';
	}
}
