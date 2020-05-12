package com.bootdo.commitment.dao;

import com.bootdo.commitment.domain.PerformanceCommitmentEvaluateDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 承诺书评价
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-06 09:10:52
 */
@Mapper
public interface PerformanceCommitmentEvaluateDao {

	PerformanceCommitmentEvaluateDO get(Integer id);
	
	List<PerformanceCommitmentEvaluateDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PerformanceCommitmentEvaluateDO performanceCommitmentEvaluate);
	
	int update(PerformanceCommitmentEvaluateDO performanceCommitmentEvaluate);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	List<PerformanceCommitmentEvaluateDO> excelExport();
}
