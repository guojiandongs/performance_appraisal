package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.VerifyDataDao;
import com.bootdo.system.domain.VerifyDataDO;
import com.bootdo.system.service.VerifyDataService;



@Service
public class VerifyDataServiceImpl implements VerifyDataService {
	@Autowired
	private VerifyDataDao verifyDataDao;
	
	@Override
	public VerifyDataDO get(String appid){
		return verifyDataDao.get(appid);
	}
	
	@Override
	public List<VerifyDataDO> list(Map<String, Object> map){
		return verifyDataDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return verifyDataDao.count(map);
	}
	
	@Override
	public int save(VerifyDataDO verifyData){
		return verifyDataDao.save(verifyData);
	}
	
	@Override
	public int update(VerifyDataDO verifyData){
		return verifyDataDao.update(verifyData);
	}
	
	@Override
	public int remove(String appid){
		return verifyDataDao.remove(appid);
	}
	
	@Override
	public int batchRemove(String[] appids){
		return verifyDataDao.batchRemove(appids);
	}
	
}
