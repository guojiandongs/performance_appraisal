package com.bootdo.kpi.service;

import com.bootdo.kpi.domain.PerformanceAppraisalOptionBackDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-09 08:58:03
 */
public interface PerformanceAppraisalOptionBackService {
	
	PerformanceAppraisalOptionBackDO get(Integer id);
	
	List<PerformanceAppraisalOptionBackDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PerformanceAppraisalOptionBackDO performanceAppraisalOptionBack);
	
	int update(PerformanceAppraisalOptionBackDO performanceAppraisalOptionBack);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

    List<PerformanceAppraisalOptionBackDO> groupType(Map<String, Object> map);
}
