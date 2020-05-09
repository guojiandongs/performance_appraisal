package com.bootdo.kpi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.kpi.dao.PerformanceAppraisalDao;
import com.bootdo.kpi.domain.PerformanceAppraisalDO;
import com.bootdo.kpi.service.PerformanceAppraisalService;



@Service
public class PerformanceAppraisalServiceImpl implements PerformanceAppraisalService {
	@Autowired
	private PerformanceAppraisalDao performanceAppraisalDao;
	
	@Override
	public PerformanceAppraisalDO get(Integer id){
		return performanceAppraisalDao.get(id);
	}
	
	@Override
	public List<PerformanceAppraisalDO> list(Map<String, Object> map){
		return performanceAppraisalDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return performanceAppraisalDao.count(map);
	}
	
	@Override
	public int save(PerformanceAppraisalDO performanceAppraisal){
		return performanceAppraisalDao.save(performanceAppraisal);
	}
	
	@Override
	public int update(PerformanceAppraisalDO performanceAppraisal){
		return performanceAppraisalDao.update(performanceAppraisal);
	}
	
	@Override
	public int remove(Integer id){
		return performanceAppraisalDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return performanceAppraisalDao.batchRemove(ids);
	}
	
}
