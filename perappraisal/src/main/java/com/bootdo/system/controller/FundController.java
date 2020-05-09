package com.bootdo.system.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.common.utils.*;
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

import com.bootdo.system.domain.FundDO;
import com.bootdo.system.service.FundService;

/**
 * 基金表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-27 17:02:57
 */
 
@Controller
@RequestMapping("/system/fund")
public class FundController {
	@Autowired
	private FundService fundService;
	
	@GetMapping()
	@RequiresPermissions("system:fund:fund")
	String Fund(){
	    return "system/fund/fund";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:fund:fund")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FundDO> fundList = fundService.list(query);
		int total = fundService.count(query);
		PageUtils pageUtils = new PageUtils(fundList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:fund:add")
	String add(){
	    return "system/fund/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:fund:edit")
	String edit(@PathVariable("id") String id,Model model){
		FundDO fund = fundService.get(id);
		model.addAttribute("fund", fund);
	    return "system/fund/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:fund:add")
	public R save( FundDO fund){
		fund.setId(UUID.randomUUID().toString().replace("-", ""));
		fund.setAuthor(ShiroUtils.getUserId().toString());
		try {
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			fund.setCreateDate(format1.parse(DateUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(fundService.save(fund)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:fund:edit")
	public R update( FundDO fund){
		fundService.update(fund);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:fund:remove")
	public R remove( String id){
		if(fundService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:fund:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		fundService.batchRemove(ids);
		return R.ok();
	}
	
}
