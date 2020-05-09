package com.bootdo.portal.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.portal.domain.TrdOrderDO;
import com.bootdo.portal.service.TrdOrderService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 购买订单表
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2019-12-02 10:06:52
 */
 
@Controller
@RequestMapping("/portal/trdOrder")
public class TrdOrderController {
	@Autowired
	private TrdOrderService trdOrderService;
	
	@GetMapping()
	@RequiresPermissions("portal:trdOrder:trdOrder")
	String TrdOrder(){
	    return "portal/trdOrder/trdOrder";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("portal:trdOrder:trdOrder")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TrdOrderDO> trdOrderList = trdOrderService.list(query);
		int total = trdOrderService.count(query);
		PageUtils pageUtils = new PageUtils(trdOrderList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("portal:trdOrder:add")
	String add(){
	    return "portal/trdOrder/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("portal:trdOrder:edit")
	String edit(@PathVariable("id") String id,Model model){
		TrdOrderDO trdOrder = trdOrderService.get(id);
		model.addAttribute("trdOrder", trdOrder);
	    return "portal/trdOrder/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("portal:trdOrder:add")
	public R save( TrdOrderDO trdOrder){
		if(trdOrderService.save(trdOrder)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("portal:trdOrder:edit")
	public R update( TrdOrderDO trdOrder){
		trdOrderService.update(trdOrder);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("portal:trdOrder:remove")
	public R remove( String id){
		if(trdOrderService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("portal:trdOrder:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		trdOrderService.batchRemove(ids);
		return R.ok();
	}
	
}
