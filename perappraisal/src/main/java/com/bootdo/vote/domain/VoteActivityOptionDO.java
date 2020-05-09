package com.bootdo.vote.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 投票活动选项表
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-01-02 11:29:02
 */
public class VoteActivityOptionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//活动id
	private String activityId;
	//选项名称
	private String activityOptions;
    //排序
    private Integer activitySort;

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
	 * 设置：选项名称
	 */
	public void setActivityOptions(String activityOptions) {
		this.activityOptions = activityOptions;
	}
	/**
	 * 获取：选项名称
	 */
	public String getActivityOptions() {
		return activityOptions;
	}

    /**
     * 设置：排序
     */
    public void setActivitySort(Integer activitySort) {
        this.activitySort = activitySort;
    }
    /**
     * 获取：排序
     */
    public Integer getActivitySort() {
        return activitySort;
    }
}
