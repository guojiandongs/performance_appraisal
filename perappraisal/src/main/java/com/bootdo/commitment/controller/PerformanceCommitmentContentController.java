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

import com.bootdo.commitment.domain.PerformanceCommitmentContentDO;
import com.bootdo.commitment.service.PerformanceCommitmentContentService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 承诺书内容
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-06 09:10:52
 */
 
@Controller
@RequestMapping("/commitment/performanceCommitmentContent")
public class PerformanceCommitmentContentController {
	@Autowired
	private PerformanceCommitmentContentService performanceCommitmentContentService;
	
	@GetMapping()
	@RequiresPermissions("commitment:performanceCommitmentContent:performanceCommitmentContent")
	String PerformanceCommitmentContent(){
	    return "commitment/performanceCommitmentContent/performanceCommitmentContent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("commitment:performanceCommitmentContent:performanceCommitmentContent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PerformanceCommitmentContentDO> performanceCommitmentContentList = performanceCommitmentContentService.list(query);
		int total = performanceCommitmentContentService.count(query);
		PageUtils pageUtils = new PageUtils(performanceCommitmentContentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("commitment:performanceCommitmentContent:add")
	String add(){
	    return "commitment/performanceCommitmentContent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("commitment:performanceCommitmentContent:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PerformanceCommitmentContentDO performanceCommitmentContent = performanceCommitmentContentService.get(id);
		model.addAttribute("performanceCommitmentContent", performanceCommitmentContent);
	    return "commitment/performanceCommitmentContent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("commitment:performanceCommitmentContent:add")
	public R save( PerformanceCommitmentContentDO performanceCommitmentContent){
		if(performanceCommitmentContentService.save(performanceCommitmentContent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("commitment:performanceCommitmentContent:edit")
	public R update( PerformanceCommitmentContentDO performanceCommitmentContent){
		performanceCommitmentContentService.update(performanceCommitmentContent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("commitment:performanceCommitmentContent:remove")
	public R remove( Integer id){
		if(performanceCommitmentContentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("commitment:performanceCommitmentContent:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		performanceCommitmentContentService.batchRemove(ids);
		return R.ok();
	}
	
}
