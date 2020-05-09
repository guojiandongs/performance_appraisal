package com.bootdo.commitment.service;

import com.bootdo.commitment.domain.PerformanceCommitmentEvaluateDO;

import java.util.List;
import java.util.Map;

/**
 * 承诺书评价
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-06 09:10:52
 */
public interface PerformanceCommitmentEvaluateService {
	
	PerformanceCommitmentEvaluateDO get(Integer id);
	
	List<PerformanceCommitmentEvaluateDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PerformanceCommitmentEvaluateDO performanceCommitmentEvaluate);
	
	int update(PerformanceCommitmentEvaluateDO performanceCommitmentEvaluate);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
