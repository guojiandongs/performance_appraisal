package com.bootdo.portal.controller;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.utils.R;
import com.bootdo.portal.util.GxphKoauthCommentService;
import com.kingdom.kop.framework.action.KopAction;
import com.kingdom.kop.framework.user.IUserService;
import com.kingdom.kop.framework.user.UserServiceFactory;
import com.bootdo.portal.util.UtilTools;
import com.szkingdom.kingdom.api.sdk.KOAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 修改手机号
 * @author lvkc
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/setphone")
@SuppressWarnings( { "unused", "unchecked", "rawtypes" })
public class SetCustPhone {
	
//	private String newphone;
//	private String newphonecode;

	@Autowired
	private GxphKoauthCommentService oauthService;
	@PostMapping("/setphone")
	@ResponseBody
	public R setphone(HttpServletRequest httpRequest,
					  @RequestParam("newphone") String newphone,
					  @RequestParam("phonecode") String newphonecode){
		IUserService us 		= UserServiceFactory.getUserService();

		HttpSession session = httpRequest.getSession(true);
		KOAuthToken token = (KOAuthToken)session.getAttribute("token");
		String custid = (String)session.getAttribute("custid");
		String username = (String)session.getAttribute("username");

//		HttpSession session = httpRequest.getSession(true);
//		KOAuthToken token = (KOAuthToken)session.getAttribute("token");
//		KOAuthToken custid = (KOAuthToken)session.getAttribute("custid");
		HashMap paramsMap = new HashMap();
		paramsMap.put("msgcode", newphonecode);
		paramsMap.put("mobiletel", newphone);
		paramsMap.put("custid", custid);
		paramsMap.put("loginname", username);


//		KOAuthToken token 	= (KOAuthToken)us.getUserSessionObj("token");//获取token



		JSONObject jsonObj = UtilTools.doKouath4Json("upd_aus_pheno_no", paramsMap, token, oauthService);
		if (UtilTools.isEmpty(jsonObj)) {
			R.error("修改手机号失败,请重试！");
		}
		JSONObject jsonv = jsonObj.getJSONObject("kdjson");
		if (!"1".equals(jsonv.getString("flag"))) {
			R.error(jsonv.getString("msg"));
		}
		return R.ok(jsonv.getString("msg"));
	}
	
//	public String getNewphone() {
//		return newphone;
//	}
//	public void setNewphone(String newphone) {
//		this.newphone = newphone;
//	}
//	public String getNewphonecode() {
//		return newphonecode;
//	}
//	public void setNewphonecode(String newphonecode) {
//		this.newphonecode = newphonecode;
//	}

}
