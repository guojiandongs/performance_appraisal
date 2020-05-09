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

import com.bootdo.vote.domain.VoteActivityRecordDO;
import com.bootdo.vote.service.VoteActivityRecordService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 投票记录表
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-01-02 11:29:02
 */
 
@Controller
@RequestMapping("/vote/voteActivityRecord")
public class VoteActivityRecordController {
	@Autowired
	private VoteActivityRecordService voteActivityRecordService;
	
	@GetMapping()
	@RequiresPermissions("vote:voteActivityRecord:voteActivityRecord")
	String VoteActivityRecord(){
	    return "vote/voteActivityRecord/voteActivityRecord";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vote:voteActivityRecord:voteActivityRecord")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<VoteActivityRecordDO> voteActivityRecordList = voteActivityRecordService.list(query);
		int total = voteActivityRecordService.count(query);
		PageUtils pageUtils = new PageUtils(voteActivityRecordList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vote:voteActivityRecord:add")
	String add(){
	    return "vote/voteActivityRecord/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vote:voteActivityRecord:edit")
	String edit(@PathVariable("id") String id,Model model){
		VoteActivityRecordDO voteActivityRecord = voteActivityRecordService.get(id);
		model.addAttribute("voteActivityRecord", voteActivityRecord);
	    return "vote/voteActivityRecord/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vote:voteActivityRecord:add")
	public R save( VoteActivityRecordDO voteActivityRecord){
		if(voteActivityRecordService.save(voteActivityRecord)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vote:voteActivityRecord:edit")
	public R update( VoteActivityRecordDO voteActivityRecord){
		voteActivityRecordService.update(voteActivityRecord);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vote:voteActivityRecord:remove")
	public R remove( String id){
		if(voteActivityRecordService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vote:voteActivityRecord:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		voteActivityRecordService.batchRemove(ids);
		return R.ok();
	}
	
}
