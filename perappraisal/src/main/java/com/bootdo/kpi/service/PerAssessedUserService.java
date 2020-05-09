package com.bootdo.kpi.service;

import com.bootdo.kpi.domain.PerAssessedUserDO;

import java.util.List;
import java.util.Map;

/**
 * 考核人
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-08 08:21:29
 */
public interface PerAssessedUserService {
	
	PerAssessedUserDO get(Integer id);
	
	List<PerAssessedUserDO> list(Map<String, Object> map);

    List<PerAssessedUserDO> perAssessedList(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PerAssessedUserDO perAssessedUser);
	
	int update(PerAssessedUserDO perAssessedUser);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
