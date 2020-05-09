package com.bootdo.portal.service;

import com.bootdo.portal.domain.TrdOrderDO;

import java.util.List;
import java.util.Map;

/**
 * 购买订单表
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2019-12-02 10:06:52
 */
public interface TrdOrderService {
	
	TrdOrderDO get(String id);
	
	List<TrdOrderDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TrdOrderDO trdOrder);
	
	int update(TrdOrderDO trdOrder);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	int savetrdOrder(String borrowid,String money,String mobile,String custid);
}
