package com.bootdo.kpi.domain;

import java.io.Serializable;
import java.sql.Date;


/**
 * 
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-03 19:38:15
 */
public class PerformanceAppraisalDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//序号
	private Integer id;
	//考核开始时间
	private Date startTime;
	//考核结束时间
	private Date endTime;
	//考核标题
	private String assessmentTitle;
	//考核标准
	private String assessmentCriteria;
	//是否启用
	private Long isOpen;

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
	 * 设置：考核开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：考核开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：考核结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：考核结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置：考核标题
	 */
	public void setAssessmentTitle(String assessmentTitle) {
		this.assessmentTitle = assessmentTitle;
	}
	/**
	 * 获取：考核标题
	 */
	public String getAssessmentTitle() {
		return assessmentTitle;
	}
	/**
	 * 设置：考核标准
	 */
	public void setAssessmentCriteria(String assessmentCriteria) {
		this.assessmentCriteria = assessmentCriteria;
	}
	/**
	 * 获取：考核标准
	 */
	public String getAssessmentCriteria() {
		return assessmentCriteria;
	}
	/**
	 * 设置：是否启用
	 */
	public void setIsOpen(Long isOpen) {
		this.isOpen = isOpen;
	}
	/**
	 * 获取：是否启用
	 */
	public Long getIsOpen() {
		return isOpen;
	}
}
