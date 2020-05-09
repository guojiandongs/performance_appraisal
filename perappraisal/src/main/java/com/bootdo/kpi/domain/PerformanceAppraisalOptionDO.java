package com.bootdo.kpi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-03 19:38:15
 */
public class PerformanceAppraisalOptionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//序号
	private Integer id;
	//考核内容
	private String appraisalContent;

	/**
	 * 设置：序号
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：序号
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：考核内容
	 */
	public void setAppraisalContent(String appraisalContent) {
		this.appraisalContent = appraisalContent;
	}
	/**
	 * 获取：考核内容
	 */
	public String getAppraisalContent() {
		return appraisalContent;
	}
}
