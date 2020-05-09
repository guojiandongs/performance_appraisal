package com.bootdo.kpi.service;

import com.bootdo.common.domain.Tree;
import com.bootdo.kpi.domain.PerformanceAppraisalUserDO;
import com.bootdo.system.domain.DeptDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-03 19:38:15
 */
public interface PerformanceAppraisalUserService {
	
	PerformanceAppraisalUserDO get(Integer id);
	
	List<PerformanceAppraisalUserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PerformanceAppraisalUserDO performanceAppraisalUser);
	
	int update(PerformanceAppraisalUserDO performanceAppraisalUser);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

    List<PerformanceAppraisalUserDO> departmentlist(Map<String, Object> map);

    Tree<PerformanceAppraisalUserDO> getTree();
}
