package com.bootdo.portal.dao;

import com.bootdo.portal.domain.TrdOrderDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 购买订单表
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2019-12-02 10:06:52
 */
@Mapper
public interface TrdOrderDao {

	TrdOrderDO get(String id);
	
	List<TrdOrderDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TrdOrderDO trdOrder);
	
	int update(TrdOrderDO trdOrder);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
