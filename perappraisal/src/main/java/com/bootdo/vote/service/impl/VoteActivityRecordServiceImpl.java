package com.bootdo.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.vote.dao.VoteActivityRecordDao;
import com.bootdo.vote.domain.VoteActivityRecordDO;
import com.bootdo.vote.service.VoteActivityRecordService;



@Service
public class VoteActivityRecordServiceImpl implements VoteActivityRecordService {
	@Autowired
	private VoteActivityRecordDao voteActivityRecordDao;
	
	@Override
	public VoteActivityRecordDO get(String id){
		return voteActivityRecordDao.get(id);
	}
	
	@Override
	public List<VoteActivityRecordDO> list(Map<String, Object> map){
		return voteActivityRecordDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return voteActivityRecordDao.count(map);
	}
	
	@Override
	public int save(VoteActivityRecordDO voteActivityRecord){
		return voteActivityRecordDao.save(voteActivityRecord);
	}
	
	@Override
	public int update(VoteActivityRecordDO voteActivityRecord){
		return voteActivityRecordDao.update(voteActivityRecord);
	}
	
	@Override
	public int remove(String id){
		return voteActivityRecordDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return voteActivityRecordDao.batchRemove(ids);
	}

	@Override
	public List<Map<String, Object>> getVoteResult(Map<String, Object> map) {
		return voteActivityRecordDao.getVoteResult(map);
	}
}
