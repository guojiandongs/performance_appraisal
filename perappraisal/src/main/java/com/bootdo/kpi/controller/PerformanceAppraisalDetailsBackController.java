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

import com.bootdo.kpi.domain.PerformanceAppraisalDetailsBackDO;
import com.bootdo.kpi.service.PerformanceAppraisalDetailsBackService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-09 08:29:51
 */
 
@Controller
@RequestMapping("/kpi/performanceAppraisalDetailsBack")
public class PerformanceAppraisalDetailsBackController {
	@Autowired
	private PerformanceAppraisalDetailsBackService performanceAppraisalDetailsBackService;
	
	@GetMapping()
	@RequiresPermissions("kpi:performanceAppraisalDetailsBack:performanceAppraisalDetailsBack")
	String PerformanceAppraisalDetailsBack(){
	    return "kpi/performanceAppraisalDetailsBack/performanceAppraisalDetailsBack";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("kpi:performanceAppraisalDetailsBack:performanceAppraisalDetailsBack")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PerformanceAppraisalDetailsBackDO> performanceAppraisalDetailsBackList = performanceAppraisalDetailsBackService.list(query);
		int total = performanceAppraisalDetailsBackService.count(query);
		PageUtils pageUtils = new PageUtils(performanceAppraisalDetailsBackList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("kpi:performanceAppraisalDetailsBack:add")
	String add(){
	    return "kpi/performanceAppraisalDetailsBack/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("kpi:performanceAppraisalDetailsBack:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PerformanceAppraisalDetailsBackDO performanceAppraisalDetailsBack = performanceAppraisalDetailsBackService.get(id);
		model.addAttribute("performanceAppraisalDetailsBack", performanceAppraisalDetailsBack);
	    return "kpi/performanceAppraisalDetailsBack/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("kpi:performanceAppraisalDetailsBack:add")
	public R save( PerformanceAppraisalDetailsBackDO performanceAppraisalDetailsBack){
		if(performanceAppraisalDetailsBackService.save(performanceAppraisalDetailsBack)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("kpi:performanceAppraisalDetailsBack:edit")
	public R update( PerformanceAppraisalDetailsBackDO performanceAppraisalDetailsBack){
		performanceAppraisalDetailsBackService.update(performanceAppraisalDetailsBack);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("kpi:performanceAppraisalDetailsBack:remove")
	public R remove( Integer id){
		if(performanceAppraisalDetailsBackService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("kpi:performanceAppraisalDetailsBack:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		performanceAppraisalDetailsBackService.batchRemove(ids);
		return R.ok();
	}
	
}
