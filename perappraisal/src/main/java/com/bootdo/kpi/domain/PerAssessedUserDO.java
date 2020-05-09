package com.bootdo.kpi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考核人
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-08 08:21:29
 */
public class PerAssessedUserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//序号
	private Integer id;
	//被考核人id
	private Integer assessedPersonnelId;
	//考评人id
	private Integer appraisalUserId;
	private String username;
	private String name;
	private String deptId;

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
	 * 设置：被考核人id
	 */
	public void setAssessedPersonnelId(Integer assessedPersonnelId) {
		this.assessedPersonnelId = assessedPersonnelId;
	}
	/**
	 * 获取：被考核人id
	 */
	public Integer getAssessedPersonnelId() {
		return assessedPersonnelId;
	}
	/**
	 * 设置：考评人id
	 */
	public void setAppraisalUserId(Integer appraisalUserId) {
		this.appraisalUserId = appraisalUserId;
	}
	/**
	 * 获取：考评人id
	 */
	public Integer getAppraisalUserId() {
		return appraisalUserId;
	}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
