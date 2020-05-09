package com.bootdo.portal.controller;

import java.util.HashMap;
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

import com.bootdo.portal.domain.OptimizationDO;
import com.bootdo.portal.service.OptimizationService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 理财优选
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2019-11-28 09:56:11
 */
 
@Controller
@RequestMapping("/portal/optimization")
public class OptimizationController {
	@Autowired
	private OptimizationService optimizationService;
	
	@GetMapping()
	@RequiresPermissions("portal:optimization:optimization")
	String Optimization(){
	    return "portal/optimization/optimization";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("portal:optimization:optimization")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OptimizationDO> optimizationList = optimizationService.list(query);
		int total = optimizationService.count(query);
		PageUtils pageUtils = new PageUtils(optimizationList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("portal:optimization:add")
	String add(){
	    return "portal/optimization/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("portal:optimization:edit")
	String edit(@PathVariable("id") String id,Model model){
		OptimizationDO optimization = optimizationService.get(id);
		model.addAttribute("optimization", optimization);
	    return "portal/optimization/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("portal:optimization:add")
	public R save( OptimizationDO optimization){
		if(optimizationService.save(optimization)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("portal:optimization:edit")
	public R update( OptimizationDO optimization){
		optimizationService.update(optimization);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("portal:optimization:remove")
	public R remove( String id){
		if(optimizationService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("portal:optimization:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		optimizationService.batchRemove(ids);
		return R.ok();
	}
	
}
