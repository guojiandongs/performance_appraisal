package com.bootdo.commitment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.commitment.dao.PerformanceCommitmentContentDao;
import com.bootdo.commitment.domain.PerformanceCommitmentContentDO;
import com.bootdo.commitment.service.PerformanceCommitmentContentService;



@Service
public class PerformanceCommitmentContentServiceImpl implements PerformanceCommitmentContentService {
	@Autowired
	private PerformanceCommitmentContentDao performanceCommitmentContentDao;
	
	@Override
	public PerformanceCommitmentContentDO get(Integer id){
		return performanceCommitmentContentDao.get(id);
	}
	
	@Override
	public List<PerformanceCommitmentContentDO> list(Map<String, Object> map){
		return performanceCommitmentContentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return performanceCommitmentContentDao.count(map);
	}
	
	@Override
	public int save(PerformanceCommitmentContentDO performanceCommitmentContent){
		return performanceCommitmentContentDao.save(performanceCommitmentContent);
	}
	
	@Override
	public int update(PerformanceCommitmentContentDO performanceCommitmentContent){
		return performanceCommitmentContentDao.update(performanceCommitmentContent);
	}
	
	@Override
	public int remove(Integer id){
		return performanceCommitmentContentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return performanceCommitmentContentDao.batchRemove(ids);
	}
	
}
