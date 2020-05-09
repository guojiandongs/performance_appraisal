package com.bootdo.kpi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.kpi.dao.PerformanceAppraisalOptionBackDao;
import com.bootdo.kpi.domain.PerformanceAppraisalOptionBackDO;
import com.bootdo.kpi.service.PerformanceAppraisalOptionBackService;



@Service
public class PerformanceAppraisalOptionBackServiceImpl implements PerformanceAppraisalOptionBackService {
	@Autowired
	private PerformanceAppraisalOptionBackDao performanceAppraisalOptionBackDao;
	
	@Override
	public PerformanceAppraisalOptionBackDO get(Integer id){
		return performanceAppraisalOptionBackDao.get(id);
	}
	
	@Override
	public List<PerformanceAppraisalOptionBackDO> list(Map<String, Object> map){
		return performanceAppraisalOptionBackDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return performanceAppraisalOptionBackDao.count(map);
	}
	
	@Override
	public int save(PerformanceAppraisalOptionBackDO performanceAppraisalOptionBack){
		return performanceAppraisalOptionBackDao.save(performanceAppraisalOptionBack);
	}
	
	@Override
	public int update(PerformanceAppraisalOptionBackDO performanceAppraisalOptionBack){
		return performanceAppraisalOptionBackDao.update(performanceAppraisalOptionBack);
	}
	
	@Override
	public int remove(Integer id){
		return performanceAppraisalOptionBackDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return performanceAppraisalOptionBackDao.batchRemove(ids);
	}

    @Override
    public List<PerformanceAppraisalOptionBackDO> groupType(Map<String, Object> map) {
        return performanceAppraisalOptionBackDao.groupType(map);
    }

}
