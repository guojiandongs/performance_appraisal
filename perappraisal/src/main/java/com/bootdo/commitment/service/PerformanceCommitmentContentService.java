package com.bootdo.commitment.service;

import com.bootdo.commitment.domain.PerformanceCommitmentContentDO;

import java.util.List;
import java.util.Map;

/**
 * 承诺书内容
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-06 09:10:52
 */
public interface PerformanceCommitmentContentService {
	
	PerformanceCommitmentContentDO get(Integer id);
	
	List<PerformanceCommitmentContentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PerformanceCommitmentContentDO performanceCommitmentContent);
	
	int update(PerformanceCommitmentContentDO performanceCommitmentContent);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
