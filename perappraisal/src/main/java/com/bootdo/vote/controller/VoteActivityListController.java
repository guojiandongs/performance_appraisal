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

import com.bootdo.vote.domain.VoteActivityListDO;
import com.bootdo.vote.service.VoteActivityListService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 活动列表
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-01-02 11:29:01
 */
 
@Controller
@RequestMapping("/vote/voteActivityList")
public class VoteActivityListController {
	@Autowired
	private VoteActivityListService voteActivityListService;
	
	@GetMapping()
	@RequiresPermissions("vote:voteActivityList:voteActivityList")
	String VoteActivityList(){
	    return "vote/voteActivityList/voteActivityList";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vote:voteActivityList:voteActivityList")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<VoteActivityListDO> voteActivityListList = voteActivityListService.list(query);
		int total = voteActivityListService.count(query);
		PageUtils pageUtils = new PageUtils(voteActivityListList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vote:voteActivityList:add")
	String add(){
	    return "vote/voteActivityList/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vote:voteActivityList:edit")
	String edit(@PathVariable("id") String id,Model model){
		VoteActivityListDO voteActivityList = voteActivityListService.get(id);
		model.addAttribute("voteActivityList", voteActivityList);
	    return "vote/voteActivityList/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vote:voteActivityList:add")
	public R save( VoteActivityListDO voteActivityList){
		voteActivityList.setId(UUID.randomUUID().toString().replace("-", ""));
		if(voteActivityListService.save(voteActivityList)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vote:voteActivityList:edit")
	public R update( VoteActivityListDO voteActivityList){
		voteActivityListService.update(voteActivityList);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vote:voteActivityList:remove")
	public R remove( String id){
		if(voteActivityListService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vote:voteActivityList:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		voteActivityListService.batchRemove(ids);
		return R.ok();
	}
	
}
