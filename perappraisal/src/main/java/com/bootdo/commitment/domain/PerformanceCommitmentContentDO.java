package com.bootdo.commitment.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 承诺书评价内容
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-06 17:19:17
 */
public class PerformanceCommitmentContentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//唯一标识
	private Integer id;
	//评价内容id
	private String commitmentContent;

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
    public void setCommitmentContent(String commitmentContent) {
        this.commitmentContent = commitmentContent;
    }
	/**
	 * 获取：评价内容id
	 */
    public String getCommitmentContent() {
        return commitmentContent;
    }

}
