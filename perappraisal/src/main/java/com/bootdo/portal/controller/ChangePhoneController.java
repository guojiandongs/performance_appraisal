package com.bootdo.portal.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.utils.R;
import com.bootdo.portal.util.UtilTools;
import com.kingdom.kdmall.mall.controller.action.acctcenter.account.SecurityQuestion;
import com.kingdom.kop.framework.context.KopSetting;
import com.kingdom.kop.framework.user.IUserService;
import com.kingdom.kop.framework.user.UserServiceFactory;
import com.kingdom.kop.framework.utils.StringUtil;
import com.szkingdom.kingdom.CommonOperation.CommonAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/changephone")
//@SuppressWarnings( { "unused", "unchecked", "rawtypes" })
public class ChangePhoneController  {
	private static final String JSON_MESSAGE = "111";
	
	/*@Autowired
	@Qualifier("v2SecurityQuestion")
	private SecurityQuestion securityQuestion;*/
	
	private String password;
	private String bankpwd;
	private String orgpwd;
	private String channelcode;
	private String bankaccount;
	private static final int defaultPhonevalidateTime = 5;// 按分钟计
	private String mobiletel;
	private String txt_validatecode;
	private String mobile;
	private Map<String,String> alterPhone ;
	
	/**
	 * 变更手机号
	 * @return
	 */
	public String updatePhone(){
		HashMap paramsMap = new HashMap();
		paramsMap.put("mobiletelno", mobiletel);
		//Map rsMap = new HashMap();
		List<Map<String, Object>> rsMap = new ArrayList<Map<String, Object>>();
		CommonAction c = CommonAction.getInstance();
//		String apinameAndVersion = "kingdom.kifp.get_cif_all_user,v1.0";
		String apinameAndVersion = "kingdom.kifp.check_aus_exists,v1.0";
		JSONObject obj = c.commonCopeMethodForJava(paramsMap, apinameAndVersion);
		try {
			if(obj.size()>0 && obj != null){
				JSONObject json = obj.getJSONObject("kdjson");
				if("1".equalsIgnoreCase(json.getString("flag"))){
					//this.json = JsonMessageUtil.getErrorJson("该手机号已被注册！");
				}else{
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return this.JSON_MESSAGE;
		}
		String custid= UserServiceFactory.getUserService().getUserSession("custid");
		String custmerno = UserServiceFactory.getUserService().getUserSession("custmerno");
		paramsMap.put("custid", custid);
		paramsMap.put("customerno", custmerno);
		paramsMap.put("mobiletel", mobiletel);
		try {
			apinameAndVersion = "kingdom.kifp.upd_aus_pheno_no,v1.0";
			obj = c.commonCopeMethodForJava(paramsMap, apinameAndVersion);
			if(obj.size()>0 && obj != null){
				JSONObject json = obj.getJSONObject("kdjson");
				if("1".equalsIgnoreCase(json.getString("flag"))){
				}else{
				}
			}else{
			}
		} catch (Exception e) {
		}
		return this.JSON_MESSAGE;
	}

	/* 手机验证码校验 */
	public boolean verifyPhoneCode() {
		IUserService userService = UserServiceFactory.getUserService();
		String sessionValidCode = userService.getUserSession("real_validateCode");
		String sessionValiTime = userService.getUserSession("invalidateTime");
		long noti = System.currentTimeMillis();
		String ttmobile = null;
		if (mobile != null && mobile.trim().length() == 11) {
			ttmobile = mobile;
		}
		if (mobiletel != null && mobiletel.length() == 11) {
			ttmobile = mobiletel;
		}
		String realPhoneNum = userService.getUserSession("phoneNum");
		if (null != KopSetting.isUsePhoneValidatCode
				&& KopSetting.isUsePhoneValidatCode.trim().equals("1")) {
			if (StringUtil.isEmpty(txt_validatecode)
					|| StringUtil.isEmpty(sessionValidCode)
					|| !txt_validatecode.equals(sessionValidCode)
					|| Long.parseLong(sessionValiTime) < noti
					|| !realPhoneNum
							.equalsIgnoreCase(ttmobile == null ? realPhoneNum
									: ttmobile)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 校验短信验证码
	 * 
	 * @return
	 */
	public String isValidatCodeMatch() {
		if (!verifyPhoneCode()) {
			UserServiceFactory.getUserService().setUserSession("alterPassServAbliable", "false");
			return this.JSON_MESSAGE;
		}
		;
		UserServiceFactory.getUserService().setUserSession("alterPassServAbliable", "true");
		return this.JSON_MESSAGE;

	}
	
	/**
	 * 验证身份证号码
	 * @return
	 */
	private boolean validateIDcard(HttpServletRequest request,String idcard){
		//String idcard = String.valueOf(alterPhone.get("idcard"));

		HttpSession session = request.getSession(true);
		String customerno = (String)session.getAttribute("custid");
		if(customerno==null){
			customerno = (String)session.getAttribute("custid");
		}

		//String customerno = UserServiceFactory.getUserService().getUserSession("customerno");// 取session数据
		HashMap paramsMap = new HashMap();
		paramsMap.put("customerno", customerno);
		Map rsMap = new  HashMap();
		List<Map<String, Object>> certificate = new ArrayList<Map<String, Object>>();
		try {
			CommonAction c = CommonAction.getInstance();
			String apinameAndVersion = "kingdom.kifp.get_sel_user_info,v1.0";
			JSONObject obj = c.commonCopeMethodForJava(paramsMap, apinameAndVersion);
			if(obj.size()>0 && obj != null){
				JSONObject json = obj.getJSONObject("kdjson");
				if("1".equalsIgnoreCase(json.getString("flag"))){
					JSONArray jsonarray = json.getJSONArray("items");
					certificate = (List)jsonarray;
					rsMap = certificate.get(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		String certificateno = String.valueOf(rsMap.get("certificateno"));
		if (idcard.equals(certificateno)) {
			return true;
		}
		return false;
	}
	/**
	 * 校验登录密码
	 * @return
	 */
	private boolean validatePwd(HttpServletRequest request,String password){
		IUserService us = UserServiceFactory.getUserService();
		//String username = us.getUserSession("username");
		HttpSession session = request.getSession(true);
		String username = (String)session.getAttribute("username");
		//String password = (String)alterPhone.get("loginPasswd");
		if(username==null){
			username = (String)session.getAttribute("username");
		}
		HashMap paramsMap = new HashMap();
		paramsMap.put("username", username  );
		paramsMap.put("serverid", "1");
		paramsMap.put("exchangeid", "1");
		paramsMap.put("openid", "null");
		paramsMap.put("custId", username);
		paramsMap.put("password", password);
		paramsMap.put("name", username);
		String ipAddress = "";
		//ipAddress = getIpAddr(ThreadContextHolder.getHttpRequest());
		paramsMap.put("ip", ipAddress);
		Map rsMap = new HashMap();
		List<Map<String, Object>> cust = new ArrayList<Map<String, Object>>();
		try {
		CommonAction c = CommonAction.getInstance();
		JSONObject obj = new JSONObject();
		String apinameAndVersion  = "kingdom.kifp.get_cust_login,v1.0";
		obj = c.commonCopeMethodForJava(paramsMap, apinameAndVersion);
			if(obj.size()>0 && obj != null){
				JSONObject json = obj.getJSONObject("kdjson");
				if("1".equalsIgnoreCase(json.getString("flag"))){
					JSONArray jsonarray = json.getJSONArray("items");
					cust = (List)jsonarray;
					rsMap = cust.get(0);
				}
			}
			//rsMap = bexService.doKifpBex4Map("api_cust_login", paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		String custid = (String)rsMap.get("custid");
		String name = (String)rsMap.get("name");
		if (!StringUtil.isEmpty(custid)
				&& !StringUtil.isEmpty(name)) {
			return true;
		}
		return false;
	}
	
//	public boolean checkSafeQuestion() {
//		String serial = alterPhone.get("question");
//		String quesNo= "Q"+serial;
//		String quesKey="0"+serial;
//		String ansNo="ANS"+serial;
//		String ansKey=alterPhone.get("answer");
//		return securityQuestion.verifyQAOneByOne(quesNo, quesKey, ansNo, ansKey,true);
//	}
//
	
	/**
	 * 变更手机号验证
	 * @return
	 */
	@PostMapping("/changphone")
	@ResponseBody
	public R validate4ChangeMobile(HttpServletRequest httpRequest,
								   @RequestParam("loginpwd") String loginpwd,
								   @RequestParam("idcard") String idcard,
								   @RequestParam(value = "question",required = false) String question,
								   @RequestParam(value = "answer",required = false) String answer
										){
		if(!this.validatePwd(httpRequest,loginpwd)){
			return R.error("密码错误");
		}
		if(!this.validateIDcard(httpRequest,idcard)){
			return R.error("身份证号错误");
		}
//		if(!this.checkSafeQuestion()){
//		}
		return R.ok("66666");
	}
	
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBankpwd() {
		return bankpwd;
	}
	public void setBankpwd(String bankpwd) {
		this.bankpwd = bankpwd;
	}
	public String getOrgpwd() {
		return orgpwd;
	}
	public void setOrgpwd(String orgpwd) {
		this.orgpwd = orgpwd;
	}
	public String getChannelcode() {
		return channelcode;
	}
	public void setChannelcode(String channelcode) {
		this.channelcode = channelcode;
	}
	public String getBankaccount() {
		return bankaccount;
	}
	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	public Map<String, String> getAlterPhone() {
		return alterPhone;
	}

	public void setAlterPhone(Map<String, String> alterPhone) {
		this.alterPhone = alterPhone;
	}

	public String getMobiletel() {
		return mobiletel;
	}

	public void setMobiletel(String mobiletel) {
		this.mobiletel = mobiletel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
