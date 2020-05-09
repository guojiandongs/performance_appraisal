package com.bootdo.vote.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.portal.util.GxphKoauthCommentService;
import com.bootdo.vote.domain.MailRecognitionDO;
import com.bootdo.vote.service.MailRecognitionService;
import org.activiti.rest.editor.model.ModelEditorJsonRestResource;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 债权转让确认表
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-02-13 16:01:18
 */
 
@Controller
@RequestMapping("/transferReco")
public class TransferRecognitionController {
    protected static final Logger logger = LoggerFactory.getLogger(ModelEditorJsonRestResource.class);
	@Autowired
	private MailRecognitionService mailRecognitionService;
    @Autowired
    private GxphKoauthCommentService koauthCommentService;
	
	@GetMapping()
	@RequiresPermissions("vote:mailRecognition:mailRecognition")
	String MailRecognition(){
	    return "vote/mailRecognition/mailRecognition";
	}

    @GetMapping("/client")
    String userAsset(HttpServletRequest httpRequest, Model model, @RequestParam("tag") String tag) { HttpSession session = httpRequest.getSession(true);
        session.setAttribute("openid", tag);
        try {
            HashMap map = new HashMap();
            map.put("openid",tag);
            model.addAttribute("openid", tag);
            List<Map> transList = getAssetByKifp(map);
            model.addAttribute("transList", transList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "vote/client/transfer";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vote:mailRecognition:mailRecognition")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MailRecognitionDO> mailRecognitionList = mailRecognitionService.list(query);
		int total = mailRecognitionService.count(query);
		PageUtils pageUtils = new PageUtils(mailRecognitionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vote:mailRecognition:add")
	String add(){
	    return "vote/mailRecognition/add";
	}

	@GetMapping("/edit/{custid}")
	@RequiresPermissions("vote:mailRecognition:edit")
	String edit(@PathVariable("custid") String custid,Model model){
		MailRecognitionDO mailRecognition = mailRecognitionService.get(custid);
		model.addAttribute("mailRecognition", mailRecognition);
	    return "vote/mailRecognition/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@GetMapping("/save")
	public R save( HttpServletRequest httpRequest, Model model, @RequestParam("orderids") String orderids) {
        HashMap resMap = new HashMap();
        resMap.put("orderids", orderids);
        String apinameAndVersion = "kingdom.kifp.save_transfer_order,v1.0";
        try {
            return this.koauthCommentService.dokoauthMapMsgFlag(apinameAndVersion, resMap);
        } catch (Exception e) {
            logger.error("转让确认异常", e);
        }
        return R.error("转让确认异常");
    }

	/**
	 * 修改
	 */
	@ResponseBody
	@GetMapping("/update")
	public R update(HttpServletRequest httpRequest, Model model, @RequestParam("custid") String custid, @RequestParam("openid") String openid, @RequestParam("chineseName") String chineseName, @RequestParam("documentType") String documentType, @RequestParam("documentId") String documentId, @RequestParam("mail") String mail){
        MailRecognitionDO u = new MailRecognitionDO();
        u.setCustid(custid);
        u.setOpenid(openid);
        u.setChineseName(chineseName);
        u.setDocumentId(documentId);
        u.setDocumentType(documentType);
        u.setMail(mail);
        u.setRecordsDate(new Date());
        int i = 0;
        try {
            i = this.mailRecognitionService.update(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i < 1) {
            return R.error("修改失败");
        }
        return R.ok("修改成功");
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vote:mailRecognition:remove")
	public R remove( String custid){
		if(mailRecognitionService.remove(custid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vote:mailRecognition:batchRemove")
	public R remove(@RequestParam("ids[]") String[] custids){
		mailRecognitionService.batchRemove(custids);
		return R.ok();
	}

    public List<Map> getAssetByKifp(HashMap resMap)
    {
        List<Map> m = new ArrayList<>();
        String apinameAndVersion = "kingdom.kifp.get_invest_not_returned,v1.0";
        try {
            m = this.koauthCommentService.dokoauthListMsg(apinameAndVersion, resMap);
        } catch (Exception e) {
            logger.error("查询资产确认异常", e);
        }
        return m;
    }
	
}
