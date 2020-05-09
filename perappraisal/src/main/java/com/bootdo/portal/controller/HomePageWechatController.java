package com.bootdo.portal.controller;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.blog.domain.ContentDO;
import com.bootdo.blog.service.ContentService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.portal.domain.OptimizationDO;
import com.bootdo.portal.domain.TrdOrderDO;
import com.bootdo.portal.service.OptimizationService;
import com.bootdo.portal.service.TrdOrderService;
import com.bootdo.portal.util.GxphKoauthCommentService;
import com.bootdo.portal.util.UtilTools;
import com.bootdo.system.domain.FundDO;
import com.bootdo.system.domain.RcharDO;
import com.bootdo.system.service.FundService;
import com.bootdo.system.service.RcharService;
import com.kingdom.kop.framework.user.IUserService;
import com.kingdom.kop.framework.user.UserServiceFactory;
import com.kingdom.kop.framework.utils.StringUtil;
import com.szkingdom.kingdom.api.sdk.KOAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.bootdo.common.utils.KoauthCommentService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ROUND_HALF_UP;


/**
 * @author gjd guojiandong@gaoxinzb.com
 */
@RequestMapping("/home/wechat")
@Controller
public class HomePageWechatController {
	private static final int LIMIT = 5;
	@Value("${internet_lending_wechat}")
	private String lending;
	@Autowired
	OptimizationService optimizationService;
	@Autowired
	TrdOrderService trdOrderService;
	@Autowired
    ContentService bContentService;
	@Autowired
	private RcharService rcharService;
	@Autowired
	private FundService fundService;
	@Autowired
	private ContentService contentService;
	@Autowired
	private GxphKoauthCommentService oauthService;

	private static final String SESSION_VALID_CODE = "valid_code";
	private static final String vtype = "admin";


	@GetMapping()
	String homepageWechat(HttpServletRequest httpRequest, Model model) {
		getRcharList(model,"wechat");
		//基金推荐
		List<FundDO> fundlist = fundService.getFundList(model, 0, 1);
		model.addAttribute("onefundlist", fundlist);
		//旅游
		getLvYList(model);
		//科技
		getTechnology(model);
		//私募
		getPlacement(model);
		getOptimizationDOList(model);
		Map<String, String> bondsMap = new HashMap<>(16);
		bondsMap.put("page","1");
		bondsMap.put("pageSize","1");
		//获取网贷列表
		List<Map> bondsList = optimizationService.getBondsList(bondsMap);
		model.addAttribute("bondsList", bondsList);
		model.addAttribute("lending", lending);
		//登录获取username
		HttpSession session = httpRequest.getSession(true);
		String username = (String)session.getAttribute("username");
		model.addAttribute("username",username);
		return "portal/HomePageWechat/index";
	}

	/**
	 * 网贷登录返回首页
	 * @param httpRequest
	 * @param response
	 * @param model
	 * @param username
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/wdhome")
	public String wdhome (HttpServletRequest httpRequest, HttpServletResponse response,
						  Model model,@RequestParam("username") String username,
						  @RequestParam("token") KOAuthToken token,
						  @RequestParam("custid") String custid
							) throws Exception{
		HttpSession session = httpRequest.getSession(true);
		session.setAttribute("username",username);
		session.setAttribute("token",token);
		session.setAttribute("custid",custid);
		return "redirect:/home/wechat";
	}
	//跳转旅游
	@GetMapping("/wechattourism")
	String tourism(Model model){
		getLvYList(model);
		return "portal/HomePageWechat/wechatTourIsm";
	}
	//跳转关于我们
	@GetMapping("/wechatabUs")
	String abUs(Model model){
		getUs(model);
		return "portal/HomePageWechat/wechatAboutUs";
	}
	//跳转私募
	@GetMapping("/wechatplace")
	String place(Model model){
		getPlacement(model);
		return "portal/HomePageWechat/wechatPlacement";
	}

	/**
	 * 获取轮播图
	 * @param model
	 */
	void getRcharList(Model model,String plantType){
		Map<String,Object> map = new HashMap<>();
		map.put("plantType",plantType);
		List<RcharDO> rcharlist = rcharService.list(map);
		model.addAttribute("rcharlist", rcharlist);
	}

/**
 * 获取封面数据
 * */
void getLvYList(Model model){
	Map<String,Object> map = new HashMap<>();
	map.put("imagetype","wx");
	map.put("levelType","0");
	List<ContentDO> lvYlist = contentService.list(map);

	model.addAttribute("lvYlist", lvYlist.get(0));
}
	/**

	 * 获取关于我们数据
	 * */
	void getUs(Model model){
		Map<String,Object> map = new HashMap<>();
		map.put("imagetype","wx");
		map.put("levelType","1");
		List<ContentDO> abUs = contentService.list(map);
		model.addAttribute("abUs", abUs.get(0));
	}
	/**
	 * 获取普惠科技图片
	 * */
	void getTechnology(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.put("imagetype", "wx");
		map.put("levelType", "2");
		List<ContentDO> technology = contentService.list(map);
		model.addAttribute("technology", technology.get(0));
	}
	/**
	 * 获取私募图片标题
	 * */
	void getPlacement(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.put("imagetype", "wx");
		map.put("levelType", "3");
		List<ContentDO> placement = contentService.list(map);
		model.addAttribute("placement", placement.get(0));
	}


	//获取理财优选
	private void getOptimizationDOList(Model model){
			Map<String, Object> params = new HashMap<>();
		params.put("offset",0);
		params.put("limit",3);
		Query query = new Query(params);
		List<OptimizationDO> optimezatinoList = optimizationService.list(query);
		for (int i = 0; i < optimezatinoList.size(); i++) {
			optimezatinoList.get(i).setPercentage((optimezatinoList.get(i).getBorrowamount().subtract(optimezatinoList.get(i).getHasinvestamount()))
							.divide(optimezatinoList.get(i).getBorrowamount(),2,ROUND_HALF_UP).multiply(new BigDecimal("100")));
		}
		//科技
		getTechnology(model);
		model.addAttribute("optimezatinoList", optimezatinoList);
	}

	@GetMapping("/optimization/weblist")
	String optimizationList(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.put("limit",LIMIT);
		map.put("offset",0);
		List<OptimizationDO> optimizationList = optimizationService.list(map);
		for (int i = 0; i < optimizationList.size(); i++) {
			optimizationList.get(i).setPercentage((optimizationList.get(i).getHasinvestamount())
					.divide(optimizationList.get(i).getBorrowamount(),2,ROUND_HALF_UP).multiply(new BigDecimal("100")));
		}
		model.addAttribute("optimizationList", optimizationList);
		//科技
		getTechnology(model);
		return "portal/HomePageWechat/productlist";
	}

	/**
	 * 理财列表上拉加载
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/optimization/weblistpagelist")
	public PageUtils optimizationPageList(@RequestParam Map<String, Object> params) {
		//查询列表数据
		params.put("limit",LIMIT);
		Query query = new Query(params);
		//查询页码起始数
		int page = query.getPage();
		int offset = page*query.getLimit();
		query.setOffset(offset);
		List<OptimizationDO> optimizationList = optimizationService.list(query);
		for (int i = 0; i < optimizationList.size(); i++) {
			optimizationList.get(i).setPercentage((optimizationList.get(i).getHasinvestamount())
					.divide(optimizationList.get(i).getBorrowamount(),2,ROUND_HALF_UP).multiply(new BigDecimal("100")));
		}
		int total = optimizationService.count(query);
		PageUtils pageUtils = new PageUtils(optimizationList, total,offset);
		return pageUtils;
	}

	@GetMapping("/optimization/{id}")
	String getOptimization(@PathVariable("id") String id, Model model) {
		OptimizationDO optimizationDO = optimizationService.get(id);
		model.addAttribute("optimization", optimizationDO);
		//科技
		getTechnology(model);
		return "portal/HomePageWechat/order";
	}

	//理财优选支付
	@GetMapping("/trdorderpay/{id}/{money}")
	String trdorderpay(@PathVariable("id") String id,@PathVariable("money") String money,Model model,HttpServletRequest httpRequest){
		TrdOrderDO trdOrder = new TrdOrderDO();
		HttpSession session = httpRequest.getSession(true);
		String username = (String)session.getAttribute("username");
		String custid = (String)session.getAttribute("custid");
		trdOrderService.savetrdOrder(id,money,username,custid);
		OptimizationDO optimization = new OptimizationDO();
		optimization.setId(id);
		optimization.setHasinvestamount(new BigDecimal(money));
		optimizationService.update(optimization);
		//科技
		getTechnology(model);
		return "redirect:/home/wechat/trdorder/weblist";
	}
	//跳转至我的账户
	@GetMapping("/trdorder/account")
	String account(HttpServletRequest httpRequest,Model model){
		HttpSession session = httpRequest.getSession(true);
		String username = (String)session.getAttribute("username");
		String custid = (String)session.getAttribute("custid");
		model.addAttribute("username",username);
		model.addAttribute("custid",custid);

		return "portal/HomePageWechat/account";
	}

	//跳转到账户订单页面
	@GetMapping("/trdorder/weblist")
	String trdorder(Model model){
		Map<String,Object> map = new HashMap<>();
		map.put("customno","1159396");
		List<TrdOrderDO> trdOrderDOList = trdOrderService.list(map);
		model.addAttribute("trdOrderDOList", trdOrderDOList);
		//科技
		getTechnology(model);
		return "portal/HomePageWechat/invest";
	}
	//修改登录密码
	@GetMapping("/trdorder/forget")
	String pwd(HttpServletRequest httpRequest,Model model){
		HttpSession session = httpRequest.getSession(true);
		String username = (String)session.getAttribute("username");
		model.addAttribute("username",username);
		return "portal/HomePageWechat/forget";
	}
	/**
	 * 修改登录密码
	 * @return
	 */
	@ResponseBody
	@PostMapping("/resetloginpwd")
	public R resetLoginPwd(HttpServletRequest httpRequest,
						   @RequestParam("newPassword") String newPassword,
						   @RequestParam("phonecode") String phonecode){
		//登录获取username
		HttpSession session = httpRequest.getSession(true);
		String username = (String)session.getAttribute("username");
		String custid = (String)session.getAttribute("custid");
		if(StringUtils.isEmpty(username)){
			return R.error("登录超时,请重新登录");
		}
		IUserService us = UserServiceFactory.getUserService();
		//KOAuthToken token = (KOAuthToken)us.getUserSessionObj("token");
		KOAuthToken token = (KOAuthToken)session.getAttribute("token");
		HashMap param = new HashMap();
		param.put("npassword", StringUtil.valueOf(newPassword));
		param.put("msgcode", phonecode);
		param.put("custid", custid);
		JSONObject jsonObj = UtilTools.doKouath4Json("upd_aus_pwd", param, token, oauthService);
		if (UtilTools.isEmpty(jsonObj)) {
			R.error("密码修改失败，请重试！");
		}
		JSONObject jsonv = jsonObj.getJSONObject("kdjson");
		if (!"1".equals(jsonv.getString("flag"))) {
			return R.error(jsonv.getString("msg"));

		}
		return R.ok("修改密码成功");
	}
	/**
	 * 校验验证码
	 * @return true or false
	 */
	@ResponseBody
	@PostMapping("/checkValidCode")
	private R checkValidCode(HttpServletRequest httpRequest,String validcode){
		HttpSession session = httpRequest.getSession(true);
		String username = (String)session.getAttribute("username");
		//HttpSession session = getRequest().getSession();
		String sessionValidCode = (String) session.getAttribute(SESSION_VALID_CODE + vtype);
		if (StringUtil.isEmpty(sessionValidCode)
				|| StringUtil.isEmpty(validcode)
				|| !sessionValidCode.toUpperCase().equals(validcode.toUpperCase())) {
			return R.error("验证码错误");
		}
		return R.ok("验证码正确");
	}
	/**
	 * 作用：
	 * 注册发送手机验证码
	 *
	 */
	@ResponseBody
	@PostMapping("/setcode")
	public R sendMsgValidCode(HttpServletRequest httpRequest,
								 @RequestParam("mobile") String mobile,
								  @RequestParam("validcode") String validcode
								 ){
//		String mobile = getRequest().getParameter("mobile");
//		String validcode = getRequest().getParameter("validcode");
		HttpSession session = httpRequest.getSession(true);

			boolean bl =sendShortMessageValidCode(mobile,mobile);
			if(bl){
				//outString("y");
				return R.ok("y");
			}else{
				//outString("n");
				return R.error("n");

			}
	}
	public boolean sendShortMessageValidCode(String mobile,String username) {
		HashMap paramsMap = new HashMap();
		paramsMap.put("phoneno", mobile);
		//paramsMap.put("loginname", username);

		boolean result=false;

		String apinameAndVersion = "kingdom.kifp.get_phone_code,v1.0";
		try {
			String resultStr=KoauthCommentService.dokoauthForFlagMsg(apinameAndVersion, paramsMap);
			if(org.apache.commons.lang3.StringUtils.isNotBlank(resultStr)) {
				String flag= org.apache.commons.lang3.StringUtils.substringBefore(resultStr, KoauthCommentService.FLAG_MSG_SPLIT);
				String msg= org.apache.commons.lang3.StringUtils.substringAfter(resultStr, KoauthCommentService.FLAG_MSG_SPLIT);
				if("1".equalsIgnoreCase(flag)){
					result=true;
				}else if("0".equalsIgnoreCase(flag)){
					R.error("发送短信验证码失败 ,mobile为 "+mobile+" , msg为"+msg);
				}
			}
		} catch (Exception e) {
			R.error("发送短信验证码异常:"+e);
		}

		return result;
	}

	/**
	 * 跳转至修改手机号页面
	 */

	@GetMapping("/trdorder/setphone")
	String setPhone(){
		return "portal/HomePageWechat/setphone";
	}

	@ResponseBody
	@PostMapping("/setphonecode")
	R setPhoneCode(HttpServletRequest httpRequest,
				   @RequestParam("password") String password,
				   @RequestParam("idcard") String idcard,
				   @RequestParam("newphone") String newphone,
				   @RequestParam("phonecode") String phonecode
				   ){
		HttpSession session = httpRequest.getSession(true);
		String username = (String)session.getAttribute("username");
		String custid = (String)session.getAttribute("custid");
		IUserService us = UserServiceFactory.getUserService();
		KOAuthToken token = (KOAuthToken)session.getAttribute("token");


		return R.ok();
	}
	@ResponseBody
	@PostMapping("/chenkphone")
	public boolean isPhoneExist(@RequestParam("mobile") String mobile) {
		boolean result=true;
		HashMap paramsMap = new HashMap();
		paramsMap.put("mobiletelno", mobile);
		String apinameAndVersion = "kingdom.kifp.check_aus_exists,v1.0";
		try {
			String resultStr=KoauthCommentService.dokoauthForFlagMsg(apinameAndVersion, paramsMap);
			if(org.apache.commons.lang3.StringUtils.isNotBlank(resultStr)) {
				String flag= org.apache.commons.lang3.StringUtils.substringBefore(resultStr, KoauthCommentService.FLAG_MSG_SPLIT);
				String msg= org.apache.commons.lang3.StringUtils.substringAfter(resultStr, KoauthCommentService.FLAG_MSG_SPLIT);
				if("1".equalsIgnoreCase(flag)){
					result=false;
				}else if("0".equalsIgnoreCase(flag)){
					R.error("手机号已被注册， mobile "+mobile+" , msg为"+msg);
				}
			}
		} catch (Exception e) {
			R.error("检查手机号异常"+e);
		}
		return result;
	}



}
