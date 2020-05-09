package com.bootdo.vote.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 投票记录表
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-01-02 11:29:02
 */
public class VoteActivityRecordDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//活动id
	private String activityId;
	//投票用户微信唯一标识
	private String openid;
	private String optionId;
	private Date voteDate;
	private String other;

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
	 * 设置：活动id
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	/**
	 * 获取：活动id
	 */
	public String getActivityId() {
		return activityId;
	}
	/**
	 * 设置：投票用户微信唯一标识
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * 获取：投票用户微信唯一标识
	 */
	public String getOpenid() {
		return openid;
	}

	public String getOptionId() {
		return optionId;
	}

	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}

	public Date getVoteDate() {
		return voteDate;
	}

	public void setVoteDate(Date voteDate) {
		this.voteDate = voteDate;
	}

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
