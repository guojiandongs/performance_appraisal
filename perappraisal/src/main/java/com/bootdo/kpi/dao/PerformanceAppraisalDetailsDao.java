package com.bootdo.kpi.dao;

import com.bootdo.kpi.domain.DetailTotal;
import com.bootdo.kpi.domain.PerformanceAppraisalDetailsDO;

import java.util.List;
import java.util.Map;

import com.bootdo.kpi.domain.PerformanceAppraisalUserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-03 19:38:15
 */
@Mapper
public interface PerformanceAppraisalDetailsDao {

	PerformanceAppraisalDetailsDO get(Integer id);
	
	List<PerformanceAppraisalDetailsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PerformanceAppraisalDetailsDO performanceAppraisalDetails);
	
	int update(PerformanceAppraisalDetailsDO performanceAppraisalDetails);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	List<DetailTotal>  total(Map<String, Object> map);
}
