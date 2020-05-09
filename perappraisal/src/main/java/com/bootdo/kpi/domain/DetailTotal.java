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
public class DetailTotal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//得分
	private String appraisalScore;
	//部门
	private String deptName;

	public String getAppraisalScore() {
		return appraisalScore;
	}

	public void setAppraisalScore(String appraisalScore) {
		this.appraisalScore = appraisalScore;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
