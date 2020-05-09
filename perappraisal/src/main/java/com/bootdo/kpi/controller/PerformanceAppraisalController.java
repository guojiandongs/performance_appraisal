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

import com.bootdo.kpi.domain.PerformanceAppraisalDO;
import com.bootdo.kpi.service.PerformanceAppraisalService;
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
@RequestMapping("/kpi/performanceAppraisal")
public class PerformanceAppraisalController {
	@Autowired
	private PerformanceAppraisalService performanceAppraisalService;
	
	@GetMapping()
	@RequiresPermissions("kpi:performanceAppraisal:performanceAppraisal")
	String PerformanceAppraisal(){
	    return "kpi/performanceAppraisal/performanceAppraisal";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("kpi:performanceAppraisal:performanceAppraisal")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PerformanceAppraisalDO> performanceAppraisalList = performanceAppraisalService.list(query);
		int total = performanceAppraisalService.count(query);
		PageUtils pageUtils = new PageUtils(performanceAppraisalList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("kpi:performanceAppraisal:add")
	String add(){
	    return "kpi/performanceAppraisal/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("kpi:performanceAppraisal:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PerformanceAppraisalDO performanceAppraisal = performanceAppraisalService.get(id);
		model.addAttribute("performanceAppraisal", performanceAppraisal);
	    return "kpi/performanceAppraisal/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("kpi:performanceAppraisal:add")
	public R save( PerformanceAppraisalDO performanceAppraisal){
		if(performanceAppraisalService.save(performanceAppraisal)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("kpi:performanceAppraisal:edit")
	public R update( PerformanceAppraisalDO performanceAppraisal){
		performanceAppraisalService.update(performanceAppraisal);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("kpi:performanceAppraisal:remove")
	public R remove( Integer id){
		if(performanceAppraisalService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("kpi:performanceAppraisal:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		performanceAppraisalService.batchRemove(ids);
		return R.ok();
	}
}
