package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.FundDao;
import com.bootdo.system.domain.FundDO;
import com.bootdo.system.service.FundService;
import org.springframework.ui.Model;


@Service
public class FundServiceImpl implements FundService {
	@Autowired
	private FundDao fundDao;
	
	@Override
	public FundDO get(String id){
		return fundDao.get(id);
	}
	
	@Override
	public List<FundDO> list(Map<String, Object> map){
		return fundDao.list(map);
	}
	/**
	 * 获取基金列表
	 * @param model
	 */
	public List<FundDO> getFundList(Model model, int start, int end){
		Map<String,Object> onemap = new HashMap<>();
		onemap.put("offset",start);
		onemap.put("limit",end);
		List<FundDO> fundlist = list(onemap);
		return fundlist;
	}
	@Override
	public int count(Map<String, Object> map){
		return fundDao.count(map);
	}
	
	@Override
	public int save(FundDO fund){
		return fundDao.save(fund);
	}
	
	@Override
	public int update(FundDO fund){
		return fundDao.update(fund);
	}
	
	@Override
	public int remove(String id){
		return fundDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return fundDao.batchRemove(ids);
	}
	
}
