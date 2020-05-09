package com.bootdo.vote.service;

import com.bootdo.vote.domain.VoteActivityListDO;

import java.util.List;
import java.util.Map;

/**
 * 活动列表
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-01-02 11:29:01
 */
public interface VoteActivityListService {
	
	VoteActivityListDO get(String id);
	
	List<VoteActivityListDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(VoteActivityListDO voteActivityList);
	
	int update(VoteActivityListDO voteActivityList);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
