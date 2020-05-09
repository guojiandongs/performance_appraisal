package com.bootdo.vote.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 活动列表
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-01-02 11:29:01
 */
public class VoteActivityListDO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	//活动描述
	private String activityName;
	//活动介绍
	private String activityDescribe;
	//活动开始时间
	private Date activityStartDate;
	private Date activityEndDate;
	//活动截止时间
	//0:没有限制;n:每日投票上限
	private Integer dailyVoteLimit;
	//0:没有限制;n:每人投票上限
	private Integer perpersonVoteLimit;
    //0:显示;1:不显示
	private Integer isShow;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：活动描述
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	/**
	 * 获取：活动描述
	 */
	public String getActivityName() {
		return activityName;
	}
	/**
	 * 设置：活动介绍
	 */
	public void setActivityDescribe(String activityDescribe) {
		this.activityDescribe = activityDescribe;
	}
	/**
	 * 获取：活动介绍
	 */
	public String getActivityDescribe() {
		return activityDescribe;
	}
	/**
	 * 设置：活动开始时间
	 */
	public void setActivityStartDate(Date activityStartDate) {
		this.activityStartDate = activityStartDate;
	}
	/**
	 * 获取：活动开始时间
	 */
	public Date getActivityStartDate() {
		return activityStartDate;
	}
	/**
	 * 设置：活动截止时间
	 */
	public void setActivityEndDate(Date activityEndDate) {
		this.activityEndDate = activityEndDate;
	}
	/**
	 * 获取：活动截止时间
	 */
	public Date getActivityEndDate() {
		return activityEndDate;
	}
	/**
	 * 设置：0:没有限制;n:每日投票上限
	 */
	public void setDailyVoteLimit(Integer dailyVoteLimit) {
		this.dailyVoteLimit = dailyVoteLimit;
	}
	/**
	 * 获取：0:没有限制;n:每日投票上限
	 */
	public Integer getDailyVoteLimit() {
		return dailyVoteLimit;
	}
	/**
	 * 设置：0:没有限制;n:每人投票上限
	 */
	public void setPerpersonVoteLimit(Integer perpersonVoteLimit) {
		this.perpersonVoteLimit = perpersonVoteLimit;
	}
	/**
	 * 获取：0:没有限制;n:每人投票上限
	 */
	public Integer getPerpersonVoteLimit() {
		return perpersonVoteLimit;
	}

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}
