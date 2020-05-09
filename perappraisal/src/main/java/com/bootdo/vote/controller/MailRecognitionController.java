package com.bootdo.vote.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.portal.util.GxphKoauthCommentService;
import com.bootdo.vote.domain.UserAssetRecognitionDO;
import org.activiti.rest.editor.model.ModelEditorJsonRestResource;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.bootdo.vote.domain.MailRecognitionDO;
import com.bootdo.vote.service.MailRecognitionService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 资产确认表
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-02-13 16:01:18
 */
 
@Controller
@RequestMapping("/usermail")
public class MailRecognitionController {
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
            model.addAttribute("openid", tag);
            MailRecognitionDO u = this.mailRecognitionService.getByOpenid(tag);

            if (null == u) {
                HashMap map = new HashMap();
                map.put("openid", tag);
                R r = getAssetByKifp(map);
                MailRecognitionDO newu = new MailRecognitionDO();
                newu.setCustid((String)r.get("customerno"));
                newu.setOpenid(tag);
                newu.setChineseName((String)r.get("chinesename"));
                newu.setDocumentId((String)r.get("certificateno"));
                newu.setDocumentType("1");
                model.addAttribute("userasset", newu);
                model.addAttribute("status", "0");
            }
            else {
                model.addAttribute("userasset", u);
                model.addAttribute("status", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "vote/client/usermail";
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
	public R save( HttpServletRequest httpRequest, Model model, @RequestParam("custid") String custid, @RequestParam("openid") String openid, @RequestParam("chineseName") String chineseName, @RequestParam("documentType") String documentType, @RequestParam("documentId") String documentId, @RequestParam("mail") String mail){
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
            i = this.mailRecognitionService.save(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i < 1) {
            return R.error("保存失败");
        }
        return R.ok("保存成功");
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

    public R getAssetByKifp(HashMap resMap)
    {
        Map m = new HashMap();
        String apinameAndVersion = "kingdom.kifp.get_invest_repay_info,v1.0";
        try {
            m = this.koauthCommentService.dokoauthMapMsg(apinameAndVersion, resMap);
        } catch (Exception e) {
            logger.error("查询资产确认异常", e);
        }
        return R.ok(m);
    }
	
}
