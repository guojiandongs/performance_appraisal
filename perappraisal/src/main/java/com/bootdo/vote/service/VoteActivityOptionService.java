package com.bootdo.vote.service;

import com.bootdo.vote.domain.VoteActivityOptionDO;

import java.util.List;
import java.util.Map;

/**
 * 投票活动选项表
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-01-02 11:29:02
 */
public interface VoteActivityOptionService {
	
	VoteActivityOptionDO get(String id);
	
	List<VoteActivityOptionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(VoteActivityOptionDO voteActivityOption);
	
	int update(VoteActivityOptionDO voteActivityOption);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
