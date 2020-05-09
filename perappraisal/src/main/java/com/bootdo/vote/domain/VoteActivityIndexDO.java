package com.bootdo.vote.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-01-02 18:50:47
 */
public class VoteActivityIndexDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//投票主页总标题
	private String voteTitle;
	//活动介绍
	private String voteIntroduction;

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
	 * 设置：投票主页总标题
	 */
	public void setVoteTitle(String voteTitle) {
		this.voteTitle = voteTitle;
	}
	/**
	 * 获取：投票主页总标题
	 */
	public String getVoteTitle() {
		return voteTitle;
	}
	/**
	 * 设置：活动介绍
	 */
	public void setVoteIntroduction(String voteIntroduction) {
		this.voteIntroduction = voteIntroduction;
	}
	/**
	 * 获取：活动介绍
	 */
	public String getVoteIntroduction() {
		return voteIntroduction;
	}
}
