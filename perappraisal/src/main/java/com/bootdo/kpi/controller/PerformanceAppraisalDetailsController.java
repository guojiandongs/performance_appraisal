package com.bootdo.kpi.controller;

import java.io.IOException;
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

import com.bootdo.kpi.domain.PerformanceAppraisalDetailsDO;
import com.bootdo.kpi.service.PerformanceAppraisalDetailsService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-03 19:38:15
 */
 
@Controller
@RequestMapping("/kpi/performanceAppraisalDetails")
public class PerformanceAppraisalDetailsController {
	@Autowired
	private PerformanceAppraisalDetailsService performanceAppraisalDetailsService;
	
	@GetMapping()
	@RequiresPermissions("kpi:performanceAppraisalDetails:performanceAppraisalDetails")
	String PerformanceAppraisalDetails(){
	    return "kpi/performanceAppraisalDetails/performanceAppraisalDetails";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("kpi:performanceAppraisalDetails:performanceAppraisalDetails")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PerformanceAppraisalDetailsDO> performanceAppraisalDetailsList = performanceAppraisalDetailsService.list(query);
		int total = performanceAppraisalDetailsService.count(query);
		PageUtils pageUtils = new PageUtils(performanceAppraisalDetailsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("kpi:performanceAppraisalDetails:add")
	String add(){
	    return "kpi/performanceAppraisalDetails/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("kpi:performanceAppraisalDetails:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PerformanceAppraisalDetailsDO performanceAppraisalDetails = performanceAppraisalDetailsService.get(id);
		model.addAttribute("performanceAppraisalDetails", performanceAppraisalDetails);
	    return "kpi/performanceAppraisalDetails/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("kpi:performanceAppraisalDetails:add")
	public R save( PerformanceAppraisalDetailsDO performanceAppraisalDetails){
		if(performanceAppraisalDetailsService.save(performanceAppraisalDetails)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("kpi:performanceAppraisalDetails:edit")
	public R update( PerformanceAppraisalDetailsDO performanceAppraisalDetails){
		performanceAppraisalDetailsService.update(performanceAppraisalDetails);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("kpi:performanceAppraisalDetails:remove")
	public R remove( Integer id){
		if(performanceAppraisalDetailsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("kpi:performanceAppraisalDetails:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		performanceAppraisalDetailsService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 导出
	 */
	@RequestMapping( "/excel")
	@ResponseBody
	@RequiresPermissions("kpi:performanceAppraisalDetails:performanceAppraisalDetails")
	public void excel(HttpServletResponse response) throws IOException {
		performanceAppraisalDetailsService.exportExcel(response);
	}

}
