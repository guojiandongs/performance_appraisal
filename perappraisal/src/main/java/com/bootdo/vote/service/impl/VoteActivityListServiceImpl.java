package com.bootdo.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.vote.dao.VoteActivityListDao;
import com.bootdo.vote.domain.VoteActivityListDO;
import com.bootdo.vote.service.VoteActivityListService;



@Service
public class VoteActivityListServiceImpl implements VoteActivityListService {
	@Autowired
	private VoteActivityListDao voteActivityListDao;
	
	@Override
	public VoteActivityListDO get(String id){
		return voteActivityListDao.get(id);
	}
	
	@Override
	public List<VoteActivityListDO> list(Map<String, Object> map){
		return voteActivityListDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return voteActivityListDao.count(map);
	}
	
	@Override
	public int save(VoteActivityListDO voteActivityList){
		return voteActivityListDao.save(voteActivityList);
	}
	
	@Override
	public int update(VoteActivityListDO voteActivityList){
		return voteActivityListDao.update(voteActivityList);
	}
	
	@Override
	public int remove(String id){
		return voteActivityListDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return voteActivityListDao.batchRemove(ids);
	}
	
}
