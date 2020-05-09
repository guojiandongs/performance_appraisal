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

import com.bootdo.kpi.domain.PerformanceAppraisalOptionDO;
import com.bootdo.kpi.service.PerformanceAppraisalOptionService;
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
@RequestMapping("/kpi/performanceAppraisalOption")
public class PerformanceAppraisalOptionController {
	@Autowired
	private PerformanceAppraisalOptionService performanceAppraisalOptionService;
	
	@GetMapping()
	@RequiresPermissions("kpi:performanceAppraisalOption:performanceAppraisalOption")
	String PerformanceAppraisalOption(){
	    return "kpi/performanceAppraisalOption/performanceAppraisalOption";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("kpi:performanceAppraisalOption:performanceAppraisalOption")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PerformanceAppraisalOptionDO> performanceAppraisalOptionList = performanceAppraisalOptionService.list(query);
		int total = performanceAppraisalOptionService.count(query);
		PageUtils pageUtils = new PageUtils(performanceAppraisalOptionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("kpi:performanceAppraisalOption:add")
	String add(){
	    return "kpi/performanceAppraisalOption/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("kpi:performanceAppraisalOption:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PerformanceAppraisalOptionDO performanceAppraisalOption = performanceAppraisalOptionService.get(id);
		model.addAttribute("performanceAppraisalOption", performanceAppraisalOption);
	    return "kpi/performanceAppraisalOption/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("kpi:performanceAppraisalOption:add")
	public R save( PerformanceAppraisalOptionDO performanceAppraisalOption){
		if(performanceAppraisalOptionService.save(performanceAppraisalOption)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("kpi:performanceAppraisalOption:edit")
	public R update( PerformanceAppraisalOptionDO performanceAppraisalOption){
		performanceAppraisalOptionService.update(performanceAppraisalOption);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("kpi:performanceAppraisalOption:remove")
	public R remove( Integer id){
		if(performanceAppraisalOptionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("kpi:performanceAppraisalOption:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		performanceAppraisalOptionService.batchRemove(ids);
		return R.ok();
	}
	
}
