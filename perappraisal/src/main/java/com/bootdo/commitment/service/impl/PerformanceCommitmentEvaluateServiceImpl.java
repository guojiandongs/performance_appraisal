package com.bootdo.commitment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.commitment.dao.PerformanceCommitmentEvaluateDao;
import com.bootdo.commitment.domain.PerformanceCommitmentEvaluateDO;
import com.bootdo.commitment.service.PerformanceCommitmentEvaluateService;



@Service
public class PerformanceCommitmentEvaluateServiceImpl implements PerformanceCommitmentEvaluateService {
	@Autowired
	private PerformanceCommitmentEvaluateDao performanceCommitmentEvaluateDao;
	
	@Override
	public PerformanceCommitmentEvaluateDO get(Integer id){
		return performanceCommitmentEvaluateDao.get(id);
	}
	
	@Override
	public List<PerformanceCommitmentEvaluateDO> list(Map<String, Object> map){
		return performanceCommitmentEvaluateDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return performanceCommitmentEvaluateDao.count(map);
	}
	
	@Override
	public int save(PerformanceCommitmentEvaluateDO performanceCommitmentEvaluate){
		return performanceCommitmentEvaluateDao.save(performanceCommitmentEvaluate);
	}
	
	@Override
	public int update(PerformanceCommitmentEvaluateDO performanceCommitmentEvaluate){
		return performanceCommitmentEvaluateDao.update(performanceCommitmentEvaluate);
	}
	
	@Override
	public int remove(Integer id){
		return performanceCommitmentEvaluateDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return performanceCommitmentEvaluateDao.batchRemove(ids);
	}
	
}
