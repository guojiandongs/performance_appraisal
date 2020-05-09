package com.bootdo.kpi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.Constant;
import com.bootdo.system.domain.RoleDO;
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

import com.bootdo.kpi.domain.PerAssessedUserDO;
import com.bootdo.kpi.service.PerAssessedUserService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 考核人
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-08 08:21:29
 */
 
@Controller
@RequestMapping("/kpi/perAssessedUser")
public class PerAssessedUserController {
	@Autowired
	private PerAssessedUserService perAssessedUserService;
	
	@GetMapping()
	@RequiresPermissions("kpi:perAssessedUser:perAssessedUser")
	String PerAssessedUser(){
	    return "kpi/perAssessedUser/perAssessedUser";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("kpi:perAssessedUser:perAssessedUser")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PerAssessedUserDO> perAssessedUserList = perAssessedUserService.list(query);
		int total = perAssessedUserService.count(query);
		PageUtils pageUtils = new PageUtils(perAssessedUserList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("kpi:perAssessedUser:add")
	String add(){
	    return "kpi/perAssessedUser/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("kpi:perAssessedUser:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PerAssessedUserDO perAssessedUser = perAssessedUserService.get(id);
		model.addAttribute("perAssessedUser", perAssessedUser);
	    return "kpi/perAssessedUser/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("kpi:perAssessedUser:add")
	public R save( PerAssessedUserDO perAssessedUser){
		if(perAssessedUserService.save(perAssessedUser)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("kpi:perAssessedUser:edit")
	public R update( PerAssessedUserDO perAssessedUser){
		perAssessedUserService.update(perAssessedUser);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("kpi:perAssessedUser:remove")
	public R remove( Integer id){
		if(perAssessedUserService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("kpi:perAssessedUser:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		perAssessedUserService.batchRemove(ids);
		return R.ok();
	}

    @PostMapping("/update")
    @ResponseBody()
    R update(@RequestParam("examineeUserId") Integer examineeUserId,@RequestParam("menuIds") String menuIds) {
        Map<String, Object> map = new HashMap<>();
        map.put("assessedPersonnelId",examineeUserId);
        List<PerAssessedUserDO> list = perAssessedUserService.list(map);
        for (int i = 0; i < list.size(); i++) {
            perAssessedUserService.remove(list.get(i).getId());
        }
	    PerAssessedUserDO perAssessedUser = new PerAssessedUserDO();
        String[] appraisalUserIds = menuIds.split(",");
        for (int i = 0; i < appraisalUserIds.length; i++) {
            perAssessedUser.setAppraisalUserId(Integer.parseInt(appraisalUserIds[i]));
            perAssessedUser.setAssessedPersonnelId(examineeUserId);
            perAssessedUserService.save(perAssessedUser);
        }
	    return R.ok();
    }

}
