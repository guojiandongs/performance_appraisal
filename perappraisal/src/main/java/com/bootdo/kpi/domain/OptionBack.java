package com.bootdo.kpi.domain;

import java.io.Serializable;
import java.util.List;


/**
 * 
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-03 19:38:15
 */
public class OptionBack implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//类型
	private String appraisalType;
	//分析列表
	private List<PerformanceAppraisalOptionBackDO> optionbackList;

    public String getAppraisalType() {
        return appraisalType;
    }

    public void setAppraisalType(String appraisalType) {
        this.appraisalType = appraisalType;
    }

    public List<PerformanceAppraisalOptionBackDO> getOptionbackList() {
        return optionbackList;
    }

    public void setOptionbackList(List<PerformanceAppraisalOptionBackDO> optionbackList) {
        this.optionbackList = optionbackList;
    }
}
