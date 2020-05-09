package com.bootdo.kpi.service;

import com.bootdo.common.domain.Tree;
import com.bootdo.kpi.domain.PerAssessedPersonnelDO;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.MenuDO;

import java.util.List;
import java.util.Map;

/**
 * 被考核人
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-08 08:21:29
 */
public interface PerAssessedPersonnelService {
	
	PerAssessedPersonnelDO get(Integer id);
	
	List<PerAssessedPersonnelDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PerAssessedPersonnelDO perAssessedPersonnel);
	
	int update(PerAssessedPersonnelDO perAssessedPersonnel);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

    Tree<DeptDO> getTree(String examineeUserId);
}
