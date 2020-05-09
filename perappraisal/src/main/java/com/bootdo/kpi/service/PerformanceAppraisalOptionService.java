package com.bootdo.kpi.service;

import com.bootdo.kpi.domain.PerformanceAppraisalOptionDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-03 19:38:15
 */
public interface PerformanceAppraisalOptionService {
	
	PerformanceAppraisalOptionDO get(Integer id);
	
	List<PerformanceAppraisalOptionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PerformanceAppraisalOptionDO performanceAppraisalOption);
	
	int update(PerformanceAppraisalOptionDO performanceAppraisalOption);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
