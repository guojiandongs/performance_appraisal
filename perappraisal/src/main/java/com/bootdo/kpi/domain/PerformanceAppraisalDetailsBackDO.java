package com.bootdo.kpi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-09 08:29:51
 */
public class PerformanceAppraisalDetailsBackDO implements Serializable {
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
	//考核时间
	private Date appraisalTime;
	//考核人手机号
	private String appraisalMobile;
	//考核信息id
	private Integer appraisalId;
    //被考核人名字
	private String examineeUserName;
	//考核人部门
    private String userPost;
    private String appraisalType;
    private String appraisalGrade;
    private String appraisalContent;
    private String assessmentTitle;
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
	/**
	 * 设置：考核时间
	 */
	public void setAppraisalTime(Date appraisalTime) {
		this.appraisalTime = appraisalTime;
	}
	/**
	 * 获取：考核时间
	 */
	public Date getAppraisalTime() {
		return appraisalTime;
	}
	/**
	 * 设置：考核人手机号
	 */
	public void setAppraisalMobile(String appraisalMobile) {
		this.appraisalMobile = appraisalMobile;
	}
	/**
	 * 获取：考核人手机号
	 */
	public String getAppraisalMobile() {
		return appraisalMobile;
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

    public String getExamineeUserName() {
        return examineeUserName;
    }

    public void setExamineeUserName(String examineeUserName) {
        this.examineeUserName = examineeUserName;
    }

    public String getUserPost() {
        return userPost;
    }

    public void setUserPost(String userPost) {
        this.userPost = userPost;
    }

    public String getAppraisalType() {
        return appraisalType;
    }

    public void setAppraisalType(String appraisalType) {
        this.appraisalType = appraisalType;
    }

    public String getAppraisalGrade() {
        return appraisalGrade;
    }

    public void setAppraisalGrade(String appraisalGrade) {
        this.appraisalGrade = appraisalGrade;
    }

    public String getAppraisalContent() {
        return appraisalContent;
    }

    public void setAppraisalContent(String appraisalContent) {
        this.appraisalContent = appraisalContent;
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }
}
