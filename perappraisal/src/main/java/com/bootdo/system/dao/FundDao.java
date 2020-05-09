package com.bootdo.system.dao;

import com.bootdo.system.domain.FundDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 基金表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-27 17:02:57
 */
@Mapper
public interface FundDao {

	FundDO get(String id);
	
	List<FundDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(FundDO fund);
	
	int update(FundDO fund);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
