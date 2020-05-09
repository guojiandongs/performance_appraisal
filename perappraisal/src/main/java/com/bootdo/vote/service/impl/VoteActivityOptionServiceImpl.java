package com.bootdo.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.vote.dao.VoteActivityOptionDao;
import com.bootdo.vote.domain.VoteActivityOptionDO;
import com.bootdo.vote.service.VoteActivityOptionService;



@Service
public class VoteActivityOptionServiceImpl implements VoteActivityOptionService {
	@Autowired
	private VoteActivityOptionDao voteActivityOptionDao;
	
	@Override
	public VoteActivityOptionDO get(String id){
		return voteActivityOptionDao.get(id);
	}
	
	@Override
	public List<VoteActivityOptionDO> list(Map<String, Object> map){
		return voteActivityOptionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return voteActivityOptionDao.count(map);
	}
	
	@Override
	public int save(VoteActivityOptionDO voteActivityOption){
		return voteActivityOptionDao.save(voteActivityOption);
	}
	
	@Override
	public int update(VoteActivityOptionDO voteActivityOption){
		return voteActivityOptionDao.update(voteActivityOption);
	}
	
	@Override
	public int remove(String id){
		return voteActivityOptionDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return voteActivityOptionDao.batchRemove(ids);
	}
	
}
