package com.bootdo.kpi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.kpi.dao.PerformanceAppraisalOptionDao;
import com.bootdo.kpi.domain.PerformanceAppraisalOptionDO;
import com.bootdo.kpi.service.PerformanceAppraisalOptionService;



@Service
public class PerformanceAppraisalOptionServiceImpl implements PerformanceAppraisalOptionService {
	@Autowired
	private PerformanceAppraisalOptionDao performanceAppraisalOptionDao;
	
	@Override
	public PerformanceAppraisalOptionDO get(Integer id){
		return performanceAppraisalOptionDao.get(id);
	}
	
	@Override
	public List<PerformanceAppraisalOptionDO> list(Map<String, Object> map){
		return performanceAppraisalOptionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return performanceAppraisalOptionDao.count(map);
	}
	
	@Override
	public int save(PerformanceAppraisalOptionDO performanceAppraisalOption){
		return performanceAppraisalOptionDao.save(performanceAppraisalOption);
	}
	
	@Override
	public int update(PerformanceAppraisalOptionDO performanceAppraisalOption){
		return performanceAppraisalOptionDao.update(performanceAppraisalOption);
	}
	
	@Override
	public int remove(Integer id){
		return performanceAppraisalOptionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return performanceAppraisalOptionDao.batchRemove(ids);
	}
	
}
