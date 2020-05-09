package com.bootdo.system.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.common.utils.DateUtils;
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

import com.bootdo.system.domain.RcharDO;
import com.bootdo.system.service.RcharService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 轮播图
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-26 18:03:03
 */
 
@Controller
@RequestMapping("/system/rchar")
public class RcharController {
	@Autowired
	private RcharService rcharService;
	
	@GetMapping()
	@RequiresPermissions("system:rchar:rchar")
	String Rchar(){
	    return "system/rchar/rchar";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:rchar:rchar")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RcharDO> rcharList = rcharService.list(query);
		int total = rcharService.count(query);
		PageUtils pageUtils = new PageUtils(rcharList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:rchar:add")
	String add(){
	    return "system/rchar/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:rchar:edit")
	String edit(@PathVariable("id") String id,Model model){
		RcharDO rchar = rcharService.get(id);
		model.addAttribute("rchar", rchar);
	    return "system/rchar/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:rchar:add")
	public R save( RcharDO rchar){
		rchar.setId(UUID.randomUUID().toString().replace("-", ""));
		try {
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			rchar.setCreateTime(format1.parse(DateUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(rcharService.save(rchar)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:rchar:edit")
	public R update( RcharDO rchar){
		rcharService.update(rchar);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:rchar:remove")
	public R remove( String id){
		if(rcharService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:rchar:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		rcharService.batchRemove(ids);
		return R.ok();
	}
	
}
