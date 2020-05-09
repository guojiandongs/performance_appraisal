package com.bootdo.portal.dao;

import com.bootdo.portal.domain.OptimizationDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 理财优选
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2019-11-28 09:56:11
 */
@Mapper
public interface OptimizationDao {

	OptimizationDO get(String id);
	
	List<OptimizationDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OptimizationDO optimization);
	
	int update(OptimizationDO optimization);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
