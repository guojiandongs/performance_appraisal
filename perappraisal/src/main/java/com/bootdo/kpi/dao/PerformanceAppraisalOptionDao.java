package com.bootdo.kpi.dao;

import com.bootdo.kpi.domain.PerformanceAppraisalOptionDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-03 19:38:15
 */
@Mapper
public interface PerformanceAppraisalOptionDao {

	PerformanceAppraisalOptionDO get(Integer id);
	
	List<PerformanceAppraisalOptionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PerformanceAppraisalOptionDO performanceAppraisalOption);
	
	int update(PerformanceAppraisalOptionDO performanceAppraisalOption);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
