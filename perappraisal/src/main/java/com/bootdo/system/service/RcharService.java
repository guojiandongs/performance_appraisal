package com.bootdo.system.service;

import com.bootdo.system.domain.RcharDO;

import java.util.List;
import java.util.Map;

/**
 * 轮播图
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-26 18:03:03
 */
public interface RcharService {
	
	RcharDO get(String id);
	
	List<RcharDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RcharDO rchar);
	
	int update(RcharDO rchar);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
