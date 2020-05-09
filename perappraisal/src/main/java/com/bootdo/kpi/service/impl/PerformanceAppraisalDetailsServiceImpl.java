package com.bootdo.kpi.service.impl;

import com.bootdo.kpi.dao.PerformanceAppraisalOptionDao;
import com.bootdo.kpi.domain.DetailTotal;
import com.bootdo.kpi.domain.PerformanceAppraisalUserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.kpi.dao.PerformanceAppraisalDetailsDao;
import com.bootdo.kpi.domain.PerformanceAppraisalDetailsDO;
import com.bootdo.kpi.service.PerformanceAppraisalDetailsService;



@Service
public class PerformanceAppraisalDetailsServiceImpl implements PerformanceAppraisalDetailsService {
	@Autowired
	private PerformanceAppraisalDetailsDao performanceAppraisalDetailsDao;

	@Override
	public PerformanceAppraisalDetailsDO get(Integer id){
		return performanceAppraisalDetailsDao.get(id);
	}
	
	@Override
	public List<PerformanceAppraisalDetailsDO> list(Map<String, Object> map){
		return performanceAppraisalDetailsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return performanceAppraisalDetailsDao.count(map);
	}
	
	@Override
	public int save(PerformanceAppraisalDetailsDO performanceAppraisalDetails){
		return performanceAppraisalDetailsDao.save(performanceAppraisalDetails);
	}
	
	@Override
	public int update(PerformanceAppraisalDetailsDO performanceAppraisalDetails){
		return performanceAppraisalDetailsDao.update(performanceAppraisalDetails);
	}
	
	@Override
	public int remove(Integer id){
		return performanceAppraisalDetailsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return performanceAppraisalDetailsDao.batchRemove(ids);
	}

    @Override
    public List<DetailTotal> total(Map<String, Object> map) {
        return performanceAppraisalDetailsDao.total(map);
    }

}
