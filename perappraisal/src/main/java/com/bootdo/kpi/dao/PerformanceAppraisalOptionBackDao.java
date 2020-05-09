package com.bootdo.kpi.dao;

import com.bootdo.kpi.domain.PerformanceAppraisalOptionBackDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-09 08:58:03
 */
@Mapper
public interface PerformanceAppraisalOptionBackDao {

	PerformanceAppraisalOptionBackDO get(Integer id);
	
	List<PerformanceAppraisalOptionBackDO> list(Map<String, Object> map);

	List<PerformanceAppraisalOptionBackDO> groupType(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(PerformanceAppraisalOptionBackDO performanceAppraisalOptionBack);
	
	int update(PerformanceAppraisalOptionBackDO performanceAppraisalOptionBack);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
