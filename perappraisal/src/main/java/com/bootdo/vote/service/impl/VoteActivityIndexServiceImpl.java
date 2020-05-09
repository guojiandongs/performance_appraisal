package com.bootdo.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.vote.dao.VoteActivityIndexDao;
import com.bootdo.vote.domain.VoteActivityIndexDO;
import com.bootdo.vote.service.VoteActivityIndexService;



@Service
public class VoteActivityIndexServiceImpl implements VoteActivityIndexService {
	@Autowired
	private VoteActivityIndexDao voteActivityIndexDao;
	
	@Override
	public VoteActivityIndexDO get(String id){
		return voteActivityIndexDao.get(id);
	}
	
	@Override
	public List<VoteActivityIndexDO> list(Map<String, Object> map){
		return voteActivityIndexDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return voteActivityIndexDao.count(map);
	}
	
	@Override
	public int save(VoteActivityIndexDO voteActivityIndex){
		return voteActivityIndexDao.save(voteActivityIndex);
	}
	
	@Override
	public int update(VoteActivityIndexDO voteActivityIndex){
		return voteActivityIndexDao.update(voteActivityIndex);
	}
	
	@Override
	public int remove(String id){
		return voteActivityIndexDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return voteActivityIndexDao.batchRemove(ids);
	}
	
}
