package com.bootdo.kpi.dao;

import com.bootdo.kpi.domain.PerformanceAppraisalDO;

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
public interface PerformanceAppraisalDao {

	PerformanceAppraisalDO get(Integer id);
	
	List<PerformanceAppraisalDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PerformanceAppraisalDO performanceAppraisal);
	
	int update(PerformanceAppraisalDO performanceAppraisal);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
