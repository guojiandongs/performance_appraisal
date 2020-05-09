package com.bootdo.kpi.controller;

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

import com.bootdo.kpi.domain.PerformanceAppraisalUserDO;
import com.bootdo.kpi.service.PerformanceAppraisalUserService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-03 19:38:15
 */
 
@Controller
@RequestMapping("/kpi/performanceAppraisalUser")
public class PerformanceAppraisalUserController {
	@Autowired
	private PerformanceAppraisalUserService performanceAppraisalUserService;
	
	@GetMapping()
	@RequiresPermissions("kpi:performanceAppraisalUser:performanceAppraisalUser")
	String PerformanceAppraisalUser(){
	    return "kpi/performanceAppraisalUser/performanceAppraisalUser";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("kpi:performanceAppraisalUser:performanceAppraisalUser")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PerformanceAppraisalUserDO> performanceAppraisalUserList = performanceAppraisalUserService.list(query);
		int total = performanceAppraisalUserService.count(query);
		PageUtils pageUtils = new PageUtils(performanceAppraisalUserList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("kpi:performanceAppraisalUser:add")
	String add(){
	    return "kpi/performanceAppraisalUser/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("kpi:performanceAppraisalUser:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PerformanceAppraisalUserDO performanceAppraisalUser = performanceAppraisalUserService.get(id);
		model.addAttribute("performanceAppraisalUser", performanceAppraisalUser);
	    return "kpi/performanceAppraisalUser/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("kpi:performanceAppraisalUser:add")
	public R save( PerformanceAppraisalUserDO performanceAppraisalUser){
		if(performanceAppraisalUserService.save(performanceAppraisalUser)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("kpi:performanceAppraisalUser:edit")
	public R update( PerformanceAppraisalUserDO performanceAppraisalUser){
		performanceAppraisalUserService.update(performanceAppraisalUser);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("kpi:performanceAppraisalUser:remove")
	public R remove( Integer id){
		if(performanceAppraisalUserService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("kpi:performanceAppraisalUser:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		performanceAppraisalUserService.batchRemove(ids);
		return R.ok();
	}
	
}
