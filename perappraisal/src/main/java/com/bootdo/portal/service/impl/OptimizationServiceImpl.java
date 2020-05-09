package com.bootdo.portal.service.impl;

import com.bootdo.common.utils.KoauthCommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.portal.dao.OptimizationDao;
import com.bootdo.portal.domain.OptimizationDO;
import com.bootdo.portal.service.OptimizationService;



@Service
public class OptimizationServiceImpl implements OptimizationService {
	@Autowired
	private OptimizationDao optimizationDao;
	
	@Override
	public OptimizationDO get(String id){
		return optimizationDao.get(id);
	}
	
	@Override
	public PageInfo<OptimizationDO> list(Map<String, Object> map, Integer pageIndex, Integer pageSize){
		PageHelper.startPage(pageIndex, pageSize);
		List<OptimizationDO> list = optimizationDao.list(map);
		return new PageInfo<>(list);
	}

	@Override
	public List<OptimizationDO> list(Map<String, Object> map){
		return optimizationDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return optimizationDao.count(map);
	}
	
	@Override
	public int save(OptimizationDO optimization){
		return optimizationDao.save(optimization);
	}
	
	@Override
	public int update(OptimizationDO optimization){
		return optimizationDao.update(optimization);
	}
	
	@Override
	public int remove(String id){
		return optimizationDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return optimizationDao.batchRemove(ids);
	}

	@Override
	public List<Map> getBondsList(Map<String, String> params) {

		String page = params.get("page");
		String pageSize = params.get("pageSize");
		Map<String,String> paramsMap=new HashMap<String, String>();
		paramsMap.put("page", page);
		paramsMap.put("pageSize", pageSize);
		List<Map> bonsList = new ArrayList<Map>();
		try {
			bonsList = KoauthCommentService.dokoauthList("kingdom.kifp.get_product_detail,v1.1", paramsMap);
			for (int i = 0; i < bonsList.size(); i++) {
				String borrowamount = (String)bonsList.get(i).get("borrowamount");
				String hasinvestamount = (String)bonsList.get(i).get("hasinvestamount");
				BigDecimal hy = new BigDecimal(borrowamount).subtract(new BigDecimal(hasinvestamount));
				bonsList.get(i).put("hy",hy);
				String deadlineunit = (String)bonsList.get(i).get("deadlineunit");
				if(deadlineunit.equals("M")){
					deadlineunit = "个月";
				}else{
					deadlineunit = "天";
				}
				bonsList.get(i).put("deadlineunit",deadlineunit);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(bonsList);
		return bonsList;
	}
	
}
