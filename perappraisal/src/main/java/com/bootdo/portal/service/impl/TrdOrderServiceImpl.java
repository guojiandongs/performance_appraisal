package com.bootdo.portal.service.impl;

import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.portal.dao.TrdOrderDao;
import com.bootdo.portal.domain.TrdOrderDO;
import com.bootdo.portal.service.TrdOrderService;

import javax.servlet.http.HttpServletRequest;


@Service
public class TrdOrderServiceImpl implements TrdOrderService {
	@Autowired
	private TrdOrderDao trdOrderDao;
	
	@Override
	public TrdOrderDO get(String id){
		return trdOrderDao.get(id);
	}
	
	@Override
	public List<TrdOrderDO> list(Map<String, Object> map){
		return trdOrderDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return trdOrderDao.count(map);
	}
	
	@Override
	public int save(TrdOrderDO trdOrder){
		return trdOrderDao.save(trdOrder);
	}

	@Override
	public int savetrdOrder(String borrowid,String money,String mobile,String custid){
		TrdOrderDO trdOrder = new TrdOrderDO();
		trdOrder.setId(UUIDUtils.randomUUID());
		trdOrder.setCustomno(custid);
		trdOrder.setMobile(mobile);
		/*trdOrder.setUsername("测试");*/
		trdOrder.setBorrowid(borrowid);
		trdOrder.setInvestamount(new BigDecimal(money));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			trdOrder.setInvesttime(sdf.parse(DateUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return trdOrderDao.save(trdOrder);
	}
	
	@Override
	public int update(TrdOrderDO trdOrder){
		return trdOrderDao.update(trdOrder);
	}
	
	@Override
	public int remove(String id){
		return trdOrderDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return trdOrderDao.batchRemove(ids);
	}
	
}
