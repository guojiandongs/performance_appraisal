package com.bootdo.kpi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.KoauthCommentService;
import com.bootdo.kpi.domain.PerformanceAppraisalUserDO;
import com.bootdo.kpi.service.PerformanceAppraisalUserService;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import com.bootdo.kpi.domain.PerAssessedPersonnelDO;
import com.bootdo.kpi.service.PerAssessedPersonnelService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 被考核人
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-08 08:21:29
 */
 
@Controller
@RequestMapping("/kpi/perAssessedPersonnel")
public class PerAssessedPersonnelController {
    private static Log logger = LogFactory.getLog(PerAssessedPersonnelController.class);
	@Autowired
	private PerAssessedPersonnelService perAssessedPersonnelService;
    @Autowired
    private PerformanceAppraisalUserService performanceAppraisalUserService;
    @Autowired
    private UserService userService;
	
	@GetMapping()
	String PerAssessedPersonnel(@RequestParam String ids,Model model){
	    model.addAttribute("appraisalId",ids);
	    return "kpi/perAssessedPersonnel/perAssessedPersonnel";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PerAssessedPersonnelDO> perAssessedPersonnelList = perAssessedPersonnelService.list(query);
		int total = perAssessedPersonnelService.count(query);
		PageUtils pageUtils = new PageUtils(perAssessedPersonnelList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(@RequestParam String appraisalId,Model model){
        model.addAttribute("appraisalId",appraisalId);
	    return "kpi/perAssessedPersonnel/choosePerson";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("kpi:perAssessedPersonnel:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PerAssessedPersonnelDO perAssessedPersonnel = perAssessedPersonnelService.get(id);
		model.addAttribute("perAssessedPersonnel", perAssessedPersonnel);
	    return "kpi/perAssessedPersonnel/edit";
	}

	@GetMapping("/addAppraisalUser/{examineeUserId}")
	String addAppraisalUser(@PathVariable("examineeUserId") Long examineeUserId,Model model){
        UserDO userDO = userService.get(examineeUserId);
	    model.addAttribute("examineeUserId", examineeUserId);
	    model.addAttribute("username", userDO.getName());
	    return "kpi/perAssessedPersonnel/chooseAppraisalUser";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("kpi:perAssessedPersonnel:add")
	public R save( PerAssessedPersonnelDO perAssessedPersonnel){
		if(perAssessedPersonnelService.save(perAssessedPersonnel)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("kpi:perAssessedPersonnel:edit")
	public R update( PerAssessedPersonnelDO perAssessedPersonnel){
		perAssessedPersonnelService.update(perAssessedPersonnel);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Integer id){
		if(perAssessedPersonnelService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("kpi:perAssessedPersonnel:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		perAssessedPersonnelService.batchRemove(ids);
		return R.ok();
	}

    /**
     * 删除
     */
    @PostMapping( "/batchAdd")
    @ResponseBody
    public R batchAdd(@RequestParam("ids[]") Integer[] ids,@RequestParam("appraisalId") String appraisalId){
        try{
            for (int i = 0; i < ids.length; i++) {
                PerAssessedPersonnelDO personnelDO = new PerAssessedPersonnelDO();
                personnelDO.setExamineeUserId(ids[i]);
                personnelDO.setAppraisalId(Integer.parseInt(appraisalId));
                Map<String, Object> map = new HashMap<>();
                map.put("examineeUserId",ids[i]);
                List<PerAssessedPersonnelDO> perList = perAssessedPersonnelService.list(map);
                if(perList.size()==0){
                    perAssessedPersonnelService.save(personnelDO);
                }
            }
        }catch (Exception e){
            logger.error("添加审核人员失败！",e);
            return R.ok("添加失败！");
        }
        return R.ok("添加成功！");
    }

    @GetMapping("/tree/{examineeUserId}")
    @ResponseBody
    Tree<DeptDO> tree(@PathVariable("examineeUserId") String examineeUserId) {
        Tree<DeptDO>  tree = perAssessedPersonnelService.getTree(examineeUserId);
        return tree;
    }
}
