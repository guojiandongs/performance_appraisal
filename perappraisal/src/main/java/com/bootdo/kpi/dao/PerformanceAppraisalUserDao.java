package com.bootdo.kpi.dao;

import com.bootdo.kpi.domain.PerformanceAppraisalUserDO;

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
public interface PerformanceAppraisalUserDao {

	PerformanceAppraisalUserDO get(Integer id);
	
	List<PerformanceAppraisalUserDO> list(Map<String, Object> map);

	List<PerformanceAppraisalUserDO> departmentlist(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(PerformanceAppraisalUserDO performanceAppraisalUser);
	
	int update(PerformanceAppraisalUserDO performanceAppraisalUser);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
