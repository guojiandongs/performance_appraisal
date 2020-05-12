package com.bootdo.commitment.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 承诺书评价
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-06 17:19:17
 */
public class PerformanceCommitmentEvaluateDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//唯一标识
	private Integer id;
	//评价内容id
	private Integer commitmentId;
	//评价内容
	private String commitmentContent;
	//被考评人员id
	private String commitmentUserId;
	//人员名称
	private String commitmentUserName;
	//所在部门id
	private Integer commitmentDepartmentId;
	//所在部门
	private String commitmentDepartmentName;
	//考评人员
	private String appraisalUserId;
	//考评时间
	private Date commitmentDate;
	//考评时间
	private Integer commitmentCount;


	public Integer getCommitmentCount() {
		return commitmentCount;
	}

	public void setCommitmentCount(Integer commitmentCount) {
		this.commitmentCount = commitmentCount;
	}

	/**
	 * 设置：唯一标识
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：唯一标识
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：评价内容id
	 */
	public void setCommitmentId(Integer commitmentId) {
		this.commitmentId = commitmentId;
	}
	/**
	 * 获取：评价内容id
	 */
	public Integer getCommitmentId() {
		return commitmentId;
	}
	/**
	 * 设置：评价内容
	 */
	public void setCommitmentContent(String commitmentContent) {
		this.commitmentContent = commitmentContent;
	}
	/**
	 * 获取：评价内容
	 */
	public String getCommitmentContent() {
		return commitmentContent;
	}

	/**
	 * 设置：人员名称
	 */
	public void setCommitmentUserName(String commitmentUserName) {
		this.commitmentUserName = commitmentUserName;
	}
	/**
	 * 获取：人员名称
	 */
	public String getCommitmentUserName() {
		return commitmentUserName;
	}
	/**
	 * 设置：所在部门id
	 */
	public void setCommitmentDepartmentId(Integer commitmentDepartmentId) {
		this.commitmentDepartmentId = commitmentDepartmentId;
	}
	/**
	 * 获取：所在部门id
	 */
	public Integer getCommitmentDepartmentId() {
		return commitmentDepartmentId;
	}
	/**
	 * 设置：所在部门
	 */
	public void setCommitmentDepartmentName(String commitmentDepartmentName) {
		this.commitmentDepartmentName = commitmentDepartmentName;
	}
	/**
	 * 获取：所在部门
	 */
	public String getCommitmentDepartmentName() {
		return commitmentDepartmentName;
	}
	/**
	 * 设置：考评人员
	 */
	public void setAppraisalUserId(String appraisalUserId) {
		this.appraisalUserId = appraisalUserId;
	}
	/**
	 * 获取：考评人员
	 */
	public String getAppraisalUserId() {
		return appraisalUserId;
	}
	/**
	 * 设置：考评时间
	 */
	public void setCommitmentDate(Date commitmentDate) {
		this.commitmentDate = commitmentDate;
	}
	/**
	 * 获取：考评时间
	 */
	public Date getCommitmentDate() {
		return commitmentDate;
	}

    public String getCommitmentUserId() {
        return commitmentUserId;
    }

    public void setCommitmentUserId(String commitmentUserId) {
        this.commitmentUserId = commitmentUserId;
    }

}
