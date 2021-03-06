package com.bootdo.system.service;

import com.bootdo.system.domain.VerifyDataDO;

import java.util.List;
import java.util.Map;

/**
 * 微信配置表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-02 15:09:48
 */
public interface VerifyDataService {
	
	VerifyDataDO get(String appid);
	
	List<VerifyDataDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(VerifyDataDO verifyData);
	
	int update(VerifyDataDO verifyData);
	
	int remove(String appid);
	
	int batchRemove(String[] appids);
}
