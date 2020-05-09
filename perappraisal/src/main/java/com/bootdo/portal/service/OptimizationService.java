package com.bootdo.portal.service;

import com.bootdo.portal.domain.OptimizationDO;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 理财优选
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2019-11-28 09:56:11
 */
public interface OptimizationService {
	
	OptimizationDO get(String id);

	PageInfo<OptimizationDO> list(Map<String, Object> map, Integer pageIndex, Integer pageSize);
	List<OptimizationDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(OptimizationDO optimization);
	
	int update(OptimizationDO optimization);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	List<Map> getBondsList(Map<String, String> params);
}
