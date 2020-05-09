package com.bootdo.kpi.domain;

import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.sql.Date;


/**
 * 
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-03 19:38:15
 */
public class PerformanceAppraisalUserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//序号
	private Integer id;
	//名字
	private String userName;
	//岗位
	private String userPost;
	//所在部门
	private String userDeparment;
	//入职日期
	private Date enrollmentTime;
	//考核资格
	private Long isQualification;
	//手机号
	private String phone;
	//角色
	private String opinion;
    //统计
    @Transient
    private String totalscore;

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
	 * 设置：名字
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：名字
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：岗位
	 */
	public void setUserPost(String userPost) {
		this.userPost = userPost;
	}
	/**
	 * 获取：岗位
	 */
	public String getUserPost() {
		return userPost;
	}
	/**
	 * 设置：
	 */
	public void setUserDeparment(String userDeparment) {
		this.userDeparment = userDeparment;
	}
	/**
	 * 获取：
	 */
	public String getUserDeparment() {
		return userDeparment;
	}
	/**
	 * 设置：入职日期
	 */
	public void setEnrollmentTime(Date enrollmentTime) {
		this.enrollmentTime = enrollmentTime;
	}
	/**
	 * 获取：入职日期
	 */
	public Date getEnrollmentTime() {
		return enrollmentTime;
	}
	/**
	 * 设置：考核资格
	 */
	public void setIsQualification(Long isQualification) {
		this.isQualification = isQualification;
	}
	/**
	 * 获取：考核资格
	 */
	public Long getIsQualification() {
		return isQualification;
	}
	/**
	 * 设置：手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机号
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：意见
	 */
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	/**
	 * 获取：意见
	 */
	public String getOpinion() {
		return opinion;
	}

    public String getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(String totalscore) {
        this.totalscore = totalscore;
    }
}
