package com.bootdo.system.controller;

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

import com.bootdo.system.domain.VerifyDataDO;
import com.bootdo.system.service.VerifyDataService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 微信配置表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-02 15:09:48
 */
 
@Controller
@RequestMapping("/system/verifyData")
public class VerifyDataController {
	@Autowired
	private VerifyDataService verifyDataService;
	
	@GetMapping()
	@RequiresPermissions("system:verifyData:verifyData")
	String VerifyData(){
	    return "system/verifyData/verifyData";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:verifyData:verifyData")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<VerifyDataDO> verifyDataList = verifyDataService.list(query);
		int total = verifyDataService.count(query);
		PageUtils pageUtils = new PageUtils(verifyDataList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:verifyData:add")
	String add(){
	    return "system/verifyData/add";
	}

	@GetMapping("/edit/{appid}")
	@RequiresPermissions("system:verifyData:edit")
	String edit(@PathVariable("appid") String appid,Model model){
		VerifyDataDO verifyData = verifyDataService.get(appid);
		model.addAttribute("verifyData", verifyData);
	    return "system/verifyData/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:verifyData:add")
	public R save( VerifyDataDO verifyData){
		if(verifyDataService.save(verifyData)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:verifyData:edit")
	public R update( VerifyDataDO verifyData){
		verifyDataService.update(verifyData);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:verifyData:remove")
	public R remove( String appid){
		if(verifyDataService.remove(appid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:verifyData:batchRemove")
	public R remove(@RequestParam("ids[]") String[] appids){
		verifyDataService.batchRemove(appids);
		return R.ok();
	}
	
}
