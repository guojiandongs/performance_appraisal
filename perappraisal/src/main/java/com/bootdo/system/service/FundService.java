package com.bootdo.system.service;

import com.bootdo.system.domain.FundDO;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基金表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-27 17:02:57
 */
public interface FundService {
	
	FundDO get(String id);
	
	List<FundDO> list(Map<String, Object> map);

	/**
	 * 获取基金列表
	 * @param model
	 */
	List<FundDO> getFundList(Model model, int start, int end);
	
	int count(Map<String, Object> map);
	
	int save(FundDO fund);
	
	int update(FundDO fund);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
