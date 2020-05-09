package com.bootdo.kpi.dao;

import com.bootdo.kpi.domain.PerAssessedPersonnelDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 被考核人
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-08 08:21:29
 */
@Mapper
public interface PerAssessedPersonnelDao {

	PerAssessedPersonnelDO get(Integer id);
	
	List<PerAssessedPersonnelDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PerAssessedPersonnelDO perAssessedPersonnel);
	
	int update(PerAssessedPersonnelDO perAssessedPersonnel);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
