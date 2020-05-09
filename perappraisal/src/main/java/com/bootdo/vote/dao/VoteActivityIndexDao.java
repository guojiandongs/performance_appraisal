package com.bootdo.vote.dao;

import com.bootdo.vote.domain.VoteActivityIndexDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-01-02 18:50:47
 */
@Mapper
public interface VoteActivityIndexDao {

	VoteActivityIndexDO get(String id);
	
	List<VoteActivityIndexDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(VoteActivityIndexDO voteActivityIndex);
	
	int update(VoteActivityIndexDO voteActivityIndex);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
