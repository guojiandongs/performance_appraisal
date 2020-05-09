package com.bootdo.vote.controller;

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

import com.bootdo.vote.domain.VoteActivityIndexDO;
import com.bootdo.vote.service.VoteActivityIndexService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-01-14 10:43:34
 */
 
@Controller
@RequestMapping("/vote/voteActivityIndex")
public class VoteActivityIndexController {
	@Autowired
	private VoteActivityIndexService voteActivityIndexService;
	
	@GetMapping()
	@RequiresPermissions("vote:voteActivityIndex:voteActivityIndex")
	String VoteActivityIndex(){
	    return "vote/voteActivityIndex/voteActivityIndex";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vote:voteActivityIndex:voteActivityIndex")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<VoteActivityIndexDO> voteActivityIndexList = voteActivityIndexService.list(query);
		int total = voteActivityIndexService.count(query);
		PageUtils pageUtils = new PageUtils(voteActivityIndexList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vote:voteActivityIndex:add")
	String add(){
	    return "vote/voteActivityIndex/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vote:voteActivityIndex:edit")
	String edit(@PathVariable("id") String id,Model model){
		VoteActivityIndexDO voteActivityIndex = voteActivityIndexService.get(id);
		model.addAttribute("voteActivityIndex", voteActivityIndex);
	    return "vote/voteActivityIndex/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vote:voteActivityIndex:add")
	public R save( VoteActivityIndexDO voteActivityIndex){
		if(voteActivityIndexService.save(voteActivityIndex)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vote:voteActivityIndex:edit")
	public R update( VoteActivityIndexDO voteActivityIndex){
		voteActivityIndexService.update(voteActivityIndex);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vote:voteActivityIndex:remove")
	public R remove( String id){
		if(voteActivityIndexService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vote:voteActivityIndex:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		voteActivityIndexService.batchRemove(ids);
		return R.ok();
	}
	
}
