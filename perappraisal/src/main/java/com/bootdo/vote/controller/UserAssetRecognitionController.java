package com.bootdo.vote.controller;

import com.bootdo.common.utils.R;
import com.bootdo.portal.util.GxphKoauthCommentService;
import com.bootdo.vote.domain.UserAssetRecognitionDO;
import com.bootdo.vote.service.UserAssetRecognitionService;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.activiti.rest.editor.model.ModelEditorJsonRestResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/userasset"})
public class UserAssetRecognitionController
{
    private static final int LIMIT = 5;
    protected static final Logger logger = LoggerFactory.getLogger(ModelEditorJsonRestResource.class);

    @Autowired
    private GxphKoauthCommentService koauthCommentService;

    @Autowired
    private UserAssetRecognitionService userAssetRecognitionService;

    @GetMapping
    String userAsset(HttpServletRequest httpRequest, Model model, @RequestParam("tag") String tag) { HttpSession session = httpRequest.getSession(true);
        session.setAttribute("openid", tag);
        try {
            model.addAttribute("openid", tag);
            UserAssetRecognitionDO u = this.userAssetRecognitionService.getByOpenid(tag);

            if (null == u) {
                HashMap map = new HashMap();
                map.put("openid", tag);
                R r = getAssetByKifp(map);
                System.out.println("jjjjjjjjjjjjjjjjjjjjjjjj=" + r.toString());
                UserAssetRecognitionDO newu = new UserAssetRecognitionDO();

                newu.setCustid((String)r.get("customerno"));
                newu.setOpenid(tag);
                newu.setChineseName((String)r.get("chinesename"));
                newu.setDocumentId((String)r.get("certificateno"));
                newu.setDocumentType("1");
                BigDecimal total = new BigDecimal((String)r.get("stillprincipal")).add(new BigDecimal((String)r.get("useableAmt")));
                newu.setStillprincipaltotal(total.toString());
                newu.setAllInterests((String)r.get("hasinterest"));
                newu.setWithdrawal((String)r.get("useableAmt"));
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
        return "vote/client/userasset"; }

    public static void main(String[] args)
    {
        Map m = new HashMap();
        m.put("a", "2");
        m.put("b", "2");
        m.put("c", "2");
        m.put("d", "2");
        R r = R.ok(m);
        System.out.println(r.toString());
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

    @GetMapping({"/save"})
    @ResponseBody
    R save(HttpServletRequest httpRequest, Model model, @RequestParam("custid") String custid, @RequestParam("openid") String openid, @RequestParam("chineseName") String chineseName, @RequestParam("documentType") String documentType, @RequestParam("documentId") String documentId, @RequestParam("stillprincipaltotal") String stillprincipaltotal, @RequestParam("allInterests") String allInterests, @RequestParam("withdrawal") String withdrawal, @RequestParam("bankName") String bankName, @RequestParam("bankId") String bankId, @RequestParam("bankMobile") String bankMobile)
    {
        UserAssetRecognitionDO u = new UserAssetRecognitionDO();
        u.setCustid(custid);
        u.setOpenid(openid);
        u.setChineseName(chineseName);
        u.setDocumentId(documentId);
        u.setDocumentType(documentType);
        u.setStillprincipaltotal(stillprincipaltotal);
        u.setAllInterests(allInterests);
        u.setWithdrawal(withdrawal);
        u.setBankName(bankName);
        u.setBankId(bankId);
        u.setBankMobile(bankMobile);
        u.setRecordsDate(new Date());
        int i = 0;
        try {
            i = this.userAssetRecognitionService.save(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i < 1) {
            return R.error("保存失败");
        }
        return R.ok("保存成功");
    }

    @GetMapping({"/update"})
    @ResponseBody
    R update(HttpServletRequest httpRequest, Model model, @RequestParam("custid") String custid, @RequestParam("openid") String openid, @RequestParam("chineseName") String chineseName, @RequestParam("documentType") String documentType, @RequestParam("documentId") String documentId, @RequestParam("stillprincipaltotal") String stillprincipaltotal, @RequestParam("allInterests") String allInterests, @RequestParam("withdrawal") String withdrawal, @RequestParam("bankName") String bankName, @RequestParam("bankId") String bankId, @RequestParam("bankMobile") String bankMobile)
    {
        UserAssetRecognitionDO u = new UserAssetRecognitionDO();
        u.setCustid(custid);
        u.setOpenid(openid);
        u.setChineseName(chineseName);
        u.setDocumentId(documentId);
        u.setDocumentType(documentType);
        u.setStillprincipaltotal(stillprincipaltotal);
        u.setAllInterests(allInterests);
        u.setWithdrawal(withdrawal);
        u.setBankName(bankName);
        u.setBankId(bankId);
        u.setBankMobile(bankMobile);
        u.setRecordsDate(new Date());
        int i = 0;
        try {
            i = this.userAssetRecognitionService.update(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i < 1) {
            return R.error("修改失败");
        }
        return R.ok("修改成功");
    }
}