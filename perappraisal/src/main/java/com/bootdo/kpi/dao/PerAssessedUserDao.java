package com.bootdo.kpi.dao;

import com.bootdo.kpi.domain.PerAssessedUserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考核人
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-08 08:21:29
 */
@Mapper
public interface PerAssessedUserDao {

	PerAssessedUserDO get(Integer id);
	
	List<PerAssessedUserDO> list(Map<String, Object> map);

	List<PerAssessedUserDO> perAssessedList(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(PerAssessedUserDO perAssessedUser);
	
	int update(PerAssessedUserDO perAssessedUser);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

}
