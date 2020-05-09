package com.bootdo.commitment.controller;

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

import com.bootdo.commitment.domain.PerformanceCommitmentEvaluateDO;
import com.bootdo.commitment.service.PerformanceCommitmentEvaluateService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 承诺书评价
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-06 09:10:52
 */
 
@Controller
@RequestMapping("/commitment/performanceCommitmentEvaluate")
public class PerformanceCommitmentEvaluateController {
	@Autowired
	private PerformanceCommitmentEvaluateService performanceCommitmentEvaluateService;
	
	@GetMapping()
	@RequiresPermissions("commitment:performanceCommitmentEvaluate:performanceCommitmentEvaluate")
	String PerformanceCommitmentEvaluate(){
	    return "commitment/performanceCommitmentEvaluate/performanceCommitmentEvaluate";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("commitment:performanceCommitmentEvaluate:performanceCommitmentEvaluate")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PerformanceCommitmentEvaluateDO> performanceCommitmentEvaluateList = performanceCommitmentEvaluateService.list(query);
		int total = performanceCommitmentEvaluateService.count(query);
		PageUtils pageUtils = new PageUtils(performanceCommitmentEvaluateList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("commitment:performanceCommitmentEvaluate:add")
	String add(){
	    return "commitment/performanceCommitmentEvaluate/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("commitment:performanceCommitmentEvaluate:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PerformanceCommitmentEvaluateDO performanceCommitmentEvaluate = performanceCommitmentEvaluateService.get(id);
		model.addAttribute("performanceCommitmentEvaluate", performanceCommitmentEvaluate);
	    return "commitment/performanceCommitmentEvaluate/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("commitment:performanceCommitmentEvaluate:add")
	public R save( PerformanceCommitmentEvaluateDO performanceCommitmentEvaluate){
		if(performanceCommitmentEvaluateService.save(performanceCommitmentEvaluate)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("commitment:performanceCommitmentEvaluate:edit")
	public R update( PerformanceCommitmentEvaluateDO performanceCommitmentEvaluate){
		performanceCommitmentEvaluateService.update(performanceCommitmentEvaluate);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("commitment:performanceCommitmentEvaluate:remove")
	public R remove( Integer id){
		if(performanceCommitmentEvaluateService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("commitment:performanceCommitmentEvaluate:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		performanceCommitmentEvaluateService.batchRemove(ids);
		return R.ok();
	}
	
}
