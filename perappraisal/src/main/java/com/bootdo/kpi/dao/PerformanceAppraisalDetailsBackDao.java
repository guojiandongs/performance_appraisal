package com.bootdo.kpi.dao;

import com.bootdo.kpi.domain.PerformanceAppraisalDetailsBackDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-09 08:29:51
 */
@Mapper
public interface PerformanceAppraisalDetailsBackDao {

	PerformanceAppraisalDetailsBackDO get(Integer id);
	
	List<PerformanceAppraisalDetailsBackDO> list(Map<String,Object> map);
	List<PerformanceAppraisalDetailsBackDO> list1(Map<String,Object> map);

	int count(Map<String,Object> map);
	
	int save(PerformanceAppraisalDetailsBackDO performanceAppraisalDetailsBack);
	
	int update(PerformanceAppraisalDetailsBackDO performanceAppraisalDetailsBack);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
