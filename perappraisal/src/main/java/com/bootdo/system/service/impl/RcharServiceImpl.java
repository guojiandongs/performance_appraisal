package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.RcharDao;
import com.bootdo.system.domain.RcharDO;
import com.bootdo.system.service.RcharService;



@Service
public class RcharServiceImpl implements RcharService {
	@Autowired
	private RcharDao rcharDao;
	
	@Override
	public RcharDO get(String id){
		return rcharDao.get(id);
	}
	
	@Override
	public List<RcharDO> list(Map<String, Object> map){
		return rcharDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return rcharDao.count(map);
	}
	
	@Override
	public int save(RcharDO rchar){
		return rcharDao.save(rchar);
	}
	
	@Override
	public int update(RcharDO rchar){
		return rcharDao.update(rchar);
	}
	
	@Override
	public int remove(String id){
		return rcharDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return rcharDao.batchRemove(ids);
	}
	
}
