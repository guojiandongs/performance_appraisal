package com.bootdo.kpi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-09 08:58:03
 */
public class PerformanceAppraisalOptionBackDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//序号
	private Integer id;
	//考核类型
	private String appraisalType;
	//考核等级
	private String appraisalGrade;
	//考核内容
	private String appraisalContent;
	private String flag;

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
	 * 设置：考核类型
	 */
	public void setAppraisalType(String appraisalType) {
		this.appraisalType = appraisalType;
	}
	/**
	 * 获取：考核类型
	 */
	public String getAppraisalType() {
		return appraisalType;
	}
	/**
	 * 设置：考核等级
	 */
	public void setAppraisalGrade(String appraisalGrade) {
		this.appraisalGrade = appraisalGrade;
	}
	/**
	 * 获取：考核等级
	 */
	public String getAppraisalGrade() {
		return appraisalGrade;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
