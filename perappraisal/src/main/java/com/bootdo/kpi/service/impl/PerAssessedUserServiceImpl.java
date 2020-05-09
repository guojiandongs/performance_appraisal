package com.bootdo.kpi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.kpi.dao.PerAssessedUserDao;
import com.bootdo.kpi.domain.PerAssessedUserDO;
import com.bootdo.kpi.service.PerAssessedUserService;



@Service
public class PerAssessedUserServiceImpl implements PerAssessedUserService {
	@Autowired
	private PerAssessedUserDao perAssessedUserDao;
	
	@Override
	public PerAssessedUserDO get(Integer id){
		return perAssessedUserDao.get(id);
	}
	
	@Override
	public List<PerAssessedUserDO> list(Map<String, Object> map){
		return perAssessedUserDao.list(map);
	}

    @Override
    public List<PerAssessedUserDO> perAssessedList(Map<String, Object> map) {
        return perAssessedUserDao.perAssessedList(map);
    }

    @Override
	public int count(Map<String, Object> map){
		return perAssessedUserDao.count(map);
	}
	
	@Override
	public int save(PerAssessedUserDO perAssessedUser){
		return perAssessedUserDao.save(perAssessedUser);
	}
	
	@Override
	public int update(PerAssessedUserDO perAssessedUser){
		return perAssessedUserDao.update(perAssessedUser);
	}
	
	@Override
	public int remove(Integer id){
		return perAssessedUserDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return perAssessedUserDao.batchRemove(ids);
	}
	
}
