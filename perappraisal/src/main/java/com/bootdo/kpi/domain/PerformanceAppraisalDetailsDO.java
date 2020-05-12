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
public class PerformanceAppraisalDetailsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//序号
	private Integer id;
	//考核内容id
	private Integer optionId;
	//考核内容
	private String optionName;
	//考核人id
	private String appraisalUserId;
	//被考核人id
	private Integer examineeUserId;
	//考核人名称
	private String appraisalUserName;
	//考核分数
	private Integer appraisalScore;
	//考核人手机号
	private String appraisalMobile;
	//考核添加时间
    private Date appraisalTime;
    //考核信息id
    private String appraisalId;
    //考核信息id
    private String opinion;
    //被考核人名字
    private String userName;
    //被考核人部门
    private String examineeUserDept;

	public String getExamineeUserDept() {
		return examineeUserDept;
	}

	public void setExamineeUserDept(String examineeUserDept) {
		this.examineeUserDept = examineeUserDept;
	}

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
	 * 设置：考核内容id
	 */
	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}
	/**
	 * 获取：考核内容id
	 */
	public Integer getOptionId() {
		return optionId;
	}
	/**
	 * 设置：考核内容
	 */
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	/**
	 * 获取：考核内容
	 */
	public String getOptionName() {
		return optionName;
	}
	/**
	 * 设置：考核人id
	 */
	public void setAppraisalUserId(String appraisalUserId) {
		this.appraisalUserId = appraisalUserId;
	}
	/**
	 * 获取：考核人id
	 */
	public String getAppraisalUserId() {
		return appraisalUserId;
	}
	/**
	 * 设置：被考核人id
	 */
	public void setExamineeUserId(Integer examineeUserId) {
		this.examineeUserId = examineeUserId;
	}
	/**
	 * 获取：被考核人id
	 */
	public Integer getExamineeUserId() {
		return examineeUserId;
	}
	/**
	 * 设置：考核人名称
	 */
	public void setAppraisalUserName(String appraisalUserName) {
		this.appraisalUserName = appraisalUserName;
	}
	/**
	 * 获取：考核人名称
	 */
	public String getAppraisalUserName() {
		return appraisalUserName;
	}
	/**
	 * 设置：考核分数
	 */
	public void setAppraisalScore(Integer appraisalScore) {
		this.appraisalScore = appraisalScore;
	}
	/**
	 * 获取：考核分数
	 */
	public Integer getAppraisalScore() {
		return appraisalScore;
	}

    public String getAppraisalMobile() {
        return appraisalMobile;
    }

    public void setAppraisalMobile(String appraisalMobile) {
        this.appraisalMobile = appraisalMobile;
    }

    public Date getAppraisalTime() {
        return appraisalTime;
    }

    public void setAppraisalTime(Date appraisalTime) {
        this.appraisalTime = appraisalTime;
    }

    public String getAppraisalId() {
        return appraisalId;
    }

    public void setAppraisalId(String appraisalId) {
        this.appraisalId = appraisalId;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
