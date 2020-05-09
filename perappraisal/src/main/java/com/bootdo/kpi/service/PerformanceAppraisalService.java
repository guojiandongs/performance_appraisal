package com.bootdo.kpi.service;

import com.bootdo.kpi.domain.PerformanceAppraisalDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-03 19:38:15
 */
public interface PerformanceAppraisalService {
	
	PerformanceAppraisalDO get(Integer id);
	
	List<PerformanceAppraisalDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PerformanceAppraisalDO performanceAppraisal);
	
	int update(PerformanceAppraisalDO performanceAppraisal);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
