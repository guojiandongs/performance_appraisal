package com.bootdo.vote.dao;

import com.bootdo.vote.domain.VoteActivityRecordDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 投票记录表
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-01-02 11:29:02
 */
@Mapper
public interface VoteActivityRecordDao {

	VoteActivityRecordDO get(String id);
	
	List<VoteActivityRecordDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(VoteActivityRecordDO voteActivityRecord);
	
	int update(VoteActivityRecordDO voteActivityRecord);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	List<Map<String, Object>> getVoteResult(Map<String, Object> map);
}
