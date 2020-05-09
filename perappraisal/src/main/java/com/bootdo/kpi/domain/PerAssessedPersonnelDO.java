package com.bootdo.kpi.domain;

import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;



/**
 * 被考核人
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-08 08:21:29
 */
public class PerAssessedPersonnelDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//序号
	private Integer id;
	//被考核人
	private Integer examineeUserId;
	//考核信息id
	private Integer appraisalId;
    @Transient
	private String name;
    @Transient
    private String examineeDept;
    @Transient
    private String appraisalUserName;
    @Transient
    private String appraisalUserDept;

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
	 * 设置：被考核人
	 */
	public void setExamineeUserId(Integer examineeUserId) {
		this.examineeUserId = examineeUserId;
	}
	/**
	 * 获取：被考核人
	 */
	public Integer getExamineeUserId() {
		return examineeUserId;
	}
	/**
	 * 设置：考核信息id
	 */
	public void setAppraisalId(Integer appraisalId) {
		this.appraisalId = appraisalId;
	}
	/**
	 * 获取：考核信息id
	 */
	public Integer getAppraisalId() {
		return appraisalId;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExamineeDept() {
        return examineeDept;
    }

    public void setExamineeDept(String examineeDept) {
        this.examineeDept = examineeDept;
    }

    public String getAppraisalUserName() {
        return appraisalUserName;
    }

    public void setAppraisalUserName(String appraisalUserName) {
        this.appraisalUserName = appraisalUserName;
    }

    public String getAppraisalUserDept() {
        return appraisalUserDept;
    }

    public void setAppraisalUserDept(String appraisalUserDept) {
        this.appraisalUserDept = appraisalUserDept;
    }
}
