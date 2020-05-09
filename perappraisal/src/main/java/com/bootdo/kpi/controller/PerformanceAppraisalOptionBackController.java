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

import com.bootdo.kpi.domain.PerformanceAppraisalOptionBackDO;
import com.bootdo.kpi.service.PerformanceAppraisalOptionBackService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-09 08:58:03
 */
 
@Controller
@RequestMapping("/kpi/performanceAppraisalOptionBack")
public class PerformanceAppraisalOptionBackController {
	@Autowired
	private PerformanceAppraisalOptionBackService performanceAppraisalOptionBackService;
	
	@GetMapping()
	@RequiresPermissions("kpi:performanceAppraisalOptionBack:performanceAppraisalOptionBack")
	String PerformanceAppraisalOptionBack(){
	    return "kpi/performanceAppraisalOptionBack/performanceAppraisalOptionBack";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("kpi:performanceAppraisalOptionBack:performanceAppraisalOptionBack")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PerformanceAppraisalOptionBackDO> performanceAppraisalOptionBackList = performanceAppraisalOptionBackService.list(query);
		int total = performanceAppraisalOptionBackService.count(query);
		PageUtils pageUtils = new PageUtils(performanceAppraisalOptionBackList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("kpi:performanceAppraisalOptionBack:add")
	String add(){
	    return "kpi/performanceAppraisalOptionBack/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("kpi:performanceAppraisalOptionBack:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PerformanceAppraisalOptionBackDO performanceAppraisalOptionBack = performanceAppraisalOptionBackService.get(id);
		model.addAttribute("performanceAppraisalOptionBack", performanceAppraisalOptionBack);
	    return "kpi/performanceAppraisalOptionBack/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("kpi:performanceAppraisalOptionBack:add")
	public R save( PerformanceAppraisalOptionBackDO performanceAppraisalOptionBack){
		if(performanceAppraisalOptionBackService.save(performanceAppraisalOptionBack)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("kpi:performanceAppraisalOptionBack:edit")
	public R update( PerformanceAppraisalOptionBackDO performanceAppraisalOptionBack){
		performanceAppraisalOptionBackService.update(performanceAppraisalOptionBack);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("kpi:performanceAppraisalOptionBack:remove")
	public R remove( Integer id){
		if(performanceAppraisalOptionBackService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("kpi:performanceAppraisalOptionBack:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		performanceAppraisalOptionBackService.batchRemove(ids);
		return R.ok();
	}
	
}
