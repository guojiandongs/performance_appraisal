package com.bootdo.vote.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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

import com.bootdo.vote.domain.VoteActivityOptionDO;
import com.bootdo.vote.service.VoteActivityOptionService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 投票活动选项表
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-01-02 11:29:02
 */
 
@Controller
@RequestMapping("/vote/voteActivityOption")
public class VoteActivityOptionController {
	@Autowired
	private VoteActivityOptionService voteActivityOptionService;
	
	@GetMapping("/activityid/{activityid}")
	@RequiresPermissions("vote:voteActivityOption:voteActivityOption")
	String VoteActivityOption(Model model,@PathVariable("activityid") String activityid){
		model.addAttribute("activityid", activityid);
		return "vote/voteActivityOption/voteActivityOption";
	}
	
	@ResponseBody
	@GetMapping("/list/{activityid}")
	@RequiresPermissions("vote:voteActivityOption:voteActivityOption")
	public PageUtils list(@RequestParam Map<String, Object> params,@PathVariable("activityid") String activityid){
		//查询列表数据
		params.put("activityId",activityid);
        Query query = new Query(params);
		List<VoteActivityOptionDO> voteActivityOptionList = voteActivityOptionService.list(query);
		int total = voteActivityOptionService.count(query);
		PageUtils pageUtils = new PageUtils(voteActivityOptionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add/{activityId}")
	@RequiresPermissions("vote:voteActivityOption:add")
	String add(Model model,@PathVariable("activityId") String activityId){
		model.addAttribute("activityId", activityId);
		return "vote/voteActivityOption/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vote:voteActivityOption:edit")
	String edit(@PathVariable("id") String id,Model model){
		VoteActivityOptionDO voteActivityOption = voteActivityOptionService.get(id);
		model.addAttribute("voteActivityOption", voteActivityOption);
	    return "vote/voteActivityOption/edit";
	}
	
	/**
	 * 保存
	 */
	 @ResponseBody
	 @PostMapping("/save")
	 @RequiresPermissions("vote:voteActivityOption:add")
	 public R save( VoteActivityOptionDO voteActivityOption){
	 voteActivityOption.setId(UUID.randomUUID().toString().replace("-", ""));
	 if(voteActivityOptionService.save(voteActivityOption)>0){
	 return R.ok();
	 }
	 return R.error();
	 }
	 /**
	  * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vote:voteActivityOption:edit")
	public R update( VoteActivityOptionDO voteActivityOption){
		voteActivityOptionService.update(voteActivityOption);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vote:voteActivityOption:remove")
	public R remove( String id){
		if(voteActivityOptionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vote:voteActivityOption:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		voteActivityOptionService.batchRemove(ids);
		return R.ok();
	}
	
}
