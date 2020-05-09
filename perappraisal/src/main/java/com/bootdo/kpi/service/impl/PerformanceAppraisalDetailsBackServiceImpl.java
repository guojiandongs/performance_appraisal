package com.bootdo.kpi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.kpi.dao.PerformanceAppraisalDetailsBackDao;
import com.bootdo.kpi.domain.PerformanceAppraisalDetailsBackDO;
import com.bootdo.kpi.service.PerformanceAppraisalDetailsBackService;



@Service
public class PerformanceAppraisalDetailsBackServiceImpl implements PerformanceAppraisalDetailsBackService {
	@Autowired
	private PerformanceAppraisalDetailsBackDao performanceAppraisalDetailsBackDao;
	
	@Override
	public PerformanceAppraisalDetailsBackDO get(Integer id){
		return performanceAppraisalDetailsBackDao.get(id);
	}
	
	@Override
	public List<PerformanceAppraisalDetailsBackDO> list(Map<String, Object> map){
		return performanceAppraisalDetailsBackDao.list(map);
	}

    @Override
    public List<PerformanceAppraisalDetailsBackDO> list1(Map<String, Object> map) {
        return performanceAppraisalDetailsBackDao.list1(map);
    }

    @Override
	public int count(Map<String, Object> map){
		return performanceAppraisalDetailsBackDao.count(map);
	}
	
	@Override
	public int save(PerformanceAppraisalDetailsBackDO performanceAppraisalDetailsBack){
		return performanceAppraisalDetailsBackDao.save(performanceAppraisalDetailsBack);
	}
	
	@Override
	public int update(PerformanceAppraisalDetailsBackDO performanceAppraisalDetailsBack){
		return performanceAppraisalDetailsBackDao.update(performanceAppraisalDetailsBack);
	}
	
	@Override
	public int remove(Integer id){
		return performanceAppraisalDetailsBackDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return performanceAppraisalDetailsBackDao.batchRemove(ids);
	}
	
}
