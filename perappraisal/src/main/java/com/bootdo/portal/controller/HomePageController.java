package com.bootdo.portal.controller;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.blog.domain.ContentDO;
import com.bootdo.blog.service.ContentService;
import com.bootdo.common.utils.*;
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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingdom.kop.framework.user.IUserService;
import com.kingdom.kop.framework.user.UserServiceFactory;
import com.kingdom.kop.framework.utils.StringUtil;
import com.szkingdom.kingdom.api.sdk.KOAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * @author gjd guojiandong@gaoxinzb.com
 */
@RequestMapping("/home")
@Controller
public class HomePageController {

	@Autowired
	private GxphKoauthCommentService oauthService;

	@Value("${internet_lending}")
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

	@GetMapping()
	String homepage(HttpServletRequest httpRequest, Model model) {
		getRcharList(model,"pc");
		//今日推荐
		getFundList(model, 0, 1, "onefundlist");
		//其他基金
		getFundList(model, 1, 6, "fundlist");
		//旅游
		getLvYList(model);
		//科技
		getTechnology(model);
		//私募
		getPlacement(model);
		getOptimizationDOList(model);
		Map<String, String> bondsMap = new HashMap<>(16);
	 bondsMap.put("page","1");
	 bondsMap.put("pageSize","4");
	 //获取网贷列表
	 List<Map> bondsList = optimizationService.getBondsList(bondsMap);
	 model.addAttribute("bondsList", bondsList);
	 model.addAttribute("lending", lending);
	 //登录获取username
	 HttpSession session = httpRequest.getSession(true);
	 String username = (String)session.getAttribute("username");
	 /*model.addAttribute("username",username);*/
		return "portal/homepage/HomePage";
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
		if(StringUtils.isEmpty(username)){
			return R.error("登录超时,请重新登录");
		}
		IUserService us = UserServiceFactory.getUserService();
		//KOAuthToken token = (KOAuthToken)us.getUserSessionObj("token");
        KOAuthToken token = (KOAuthToken)session.getAttribute("token");
		HashMap param = new HashMap();
		param.put("npassword",StringUtil.valueOf(newPassword));
		param.put("msgcode", phonecode);
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
     * 跳转至账户设置
     * @return
     */
	@GetMapping("/open/accontset")
    String account(Model model,HttpServletRequest httpRequest){
		//旅游
		getLvYList(model);
		//科技
		getTechnology(model);
		//私募
		getPlacement(model);
		HttpSession session = httpRequest.getSession(true);
		String username = (String)session.getAttribute("username");
		model.addAttribute("username",username);
        KOAuthToken token = (KOAuthToken)session.getAttribute("token");

        HashMap paramsMap=new HashMap();
        List safeList = UtilTools.doKouath4List("get_safe_status", paramsMap, token, oauthService);//查询安全级别下面的认证状态
        if (UtilTools.isEmpty(safeList)) return "查询失败";
        Map safeMap=(Map)safeList.get(0);
        if(!safeMap.isEmpty()){
            //this.putData("safeStatus",safeMap);
            model.addAttribute("safeStatus",safeMap);
        }else {
            safeMap.put("individualorinstitution", "100");
            //this.putData("safeStatus",safeMap);
            model.addAttribute("safeStatus",safeMap);
        }
        model.addAttribute("questions",getSecurityQuestion(model,token));
        return "portal/homepage/myselfinfocommonv2";
    }
    /**
     * 密保问题
     * @param
     * @return
     */
    public List getSecurityQuestion(Model model,KOAuthToken token){
        Map data = new HashMap();
        HashMap paramsMap = new HashMap();
        paramsMap.put("dict_id", "D00006");
        List rsList = UtilTools.doKouath4List("get_dict", paramsMap, token,oauthService);
        if(UtilTools.isEmpty(rsList)) return rsList;
        List quetionsList = UtilTools.doKouath4List("get_qanda", paramsMap, token, oauthService);
        IUserService us		= UserServiceFactory.getUserService();
        if (UtilTools.isEmpty(quetionsList)) {
           // us.setUserSession("exsitsqus", "n");
            return rsList;
        }
        List userList = new ArrayList();
        Map questionMap = (Map)quetionsList.get(0);
        String Q1 = (String)questionMap.get("Q1");
        String Q2 = (String)questionMap.get("Q2");
        String Q3 = (String)questionMap.get("Q3");
        for (int i = 0; i < rsList.size(); i++) {
            Map rsMap = (Map)rsList.get(i);
            Map usrQA = new HashMap();
            String dict_key = (String)rsMap.get("dict_key");
            if (Q1.equals(dict_key)) {
                usrQA.put("key", Q1);
                usrQA.put("text",rsMap.get("dict_value"));
            }
            if (Q2.equals(dict_key)) {
                usrQA.put("key", Q2);
                usrQA.put("text",rsMap.get("dict_value"));
            }
            if (Q3.equals(dict_key)) {
                usrQA.put("key", Q3);
                usrQA.put("text",rsMap.get("dict_value"));
            }
            if (!usrQA.isEmpty()) userList.add(usrQA);
        }
//		this.putData("safeQuestionSeted","true");
        //this.putData("usrQA",userList);
        model.addAttribute("usrQA",userList);
        if(UtilTools.isEmpty(userList));
        us.setUserSession("exsitsqus", "y");
        return rsList;
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
						  Model model, @RequestParam("username") String username,
						      		   @RequestParam("token") KOAuthToken token,
						               @RequestParam("custid") String custid) throws Exception{
		HttpSession session = httpRequest.getSession(true);
		session.setAttribute("username",username);
        session.setAttribute("token",token);
		session.setAttribute("custid",custid);
		return "redirect:/home";
	}

	public static void main(String[] args) {
		String value = "{'access_token':'3e1b74ce-f052-4a3d-b557-6c67beb020e9','expires_in':98383685,'refresh_token':'ce0f8838-5e76-42f8-843b-b3a7839f419f','scope':'read'}";
	}
	//跳转到账户订单页面
	@GetMapping("/trdorder/weblist")
	String trdorder(HttpServletRequest httpRequest,Model model){
		Map<String,Object> map = new HashMap<>();
		HttpSession session = httpRequest.getSession(true);
		String username = (String)session.getAttribute("username");
		String custid = (String)session.getAttribute("custid");
		map.put("customno",custid);
		List<TrdOrderDO> trdOrderDOList = trdOrderService.list(map);
		model.addAttribute("trdOrderDOList", trdOrderDOList);
		return "portal/homepage/invest";
	}

	//理财优选支付
	@GetMapping("/trdorderpay/{id}/{money}")
	String trdorderpay(@PathVariable("id") String id,@PathVariable("money") String money,HttpServletRequest httpRequest){
		TrdOrderDO trdOrder = new TrdOrderDO();
		HttpSession session = httpRequest.getSession(true);
		String username = (String)session.getAttribute("username");
		String custid = (String)session.getAttribute("custid");
		trdOrderService.savetrdOrder(id,money,username,custid);
		OptimizationDO optimization = new OptimizationDO();
		optimization.setId(id);
		optimization.setHasinvestamount(new BigDecimal(money));
		optimizationService.update(optimization);
		return "redirect:/home/trdorder/weblist";
	}

	@GetMapping("/open/tourism")
	String tourism(Model model){
		getLvYList(model);
		//科技
		getTechnology(model);
		//私募
		getPlacement(model);
		return "portal/homepage/TourIsm";
	}
	@GetMapping("/open/abUs")
	String abUs(Model model){
		getUs(model);
		//旅游
		getLvYList(model);
		//科技
		getTechnology(model);
		//私募
		getPlacement(model);
		return "portal/homepage/AboutUs";
	}
	@GetMapping("/open/place")
	String place(Model model){
		getPlacement(model);
		//旅游
		getLvYList(model);
		//科技
		getTechnology(model);
		return "portal/homepage/placement";
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
	 * 获取基金列表
	 * @param model
	 */
	void getFundList(Model model,int start,int end,String sendname){
		Map<String,Object> onemap = new HashMap<>();
		onemap.put("offset",start);
		onemap.put("limit",end);
		List<FundDO> fundlist = fundService.list(onemap);
		model.addAttribute(sendname, fundlist);
	}

/**
 * 获取封面数据
 * */
void getLvYList(Model model){
	Map<String,Object> map = new HashMap<>();
	map.put("imagetype","pc");
	map.put("levelType","0");
	List<ContentDO> lvYlist = contentService.list(map);

	model.addAttribute("lvYlist", lvYlist.get(0));
}
	/**
	 * 获取关于我们数据
	 * */
	void getUs(Model model){
		Map<String,Object> map = new HashMap<>();
		map.put("imagetype","pc");
		map.put("levelType","1");
		List<ContentDO> abUs = contentService.list(map);
		model.addAttribute("abUs", abUs.get(0));
	}
	/**
	 * 获取普惠科技图片
	 * */
	void getTechnology(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.put("imagetype", "pc");
		map.put("levelType", "2");
		List<ContentDO> technology = contentService.list(map);
		model.addAttribute("technology", technology.get(0));
	}
	/**
	 * 获取私募图片标题
	 * */
	void getPlacement(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.put("imagetype", "pc");
		map.put("levelType", "3");
		List<ContentDO> placement = contentService.list(map);
		model.addAttribute("placement", placement.get(0));
	}

	@GetMapping("/optimization/{id}")
	String getOptimization(@PathVariable("id") String id, Model model) {
		OptimizationDO optimizationDO = optimizationService.get(id);
		model.addAttribute("optimization", optimizationDO);
		return "portal/homepage/order";
	}

	@GetMapping("/optimization/weblist")
	String optimizationList(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
							@RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize,Model model) {
		Map<String, Object> map = new HashMap<>();
		PageInfo<OptimizationDO> optimizationList = optimizationService.list(map,pageIndex,pageSize);

		for (int i = 0; i < optimizationList.getList().size(); i++) {
			optimizationList.getList().get(i).setPercentage((optimizationList.getList().get(i).getHasinvestamount())
					.divide(optimizationList.getList().get(i).getBorrowamount(),2,ROUND_HALF_UP).multiply(new BigDecimal("100")));
		}
		model.addAttribute("optimizationPage", optimizationList);
		//旅游
		getLvYList(model);
		//科技
		getTechnology(model);
		//私募
		getPlacement(model);
		return "portal/homepage/productlist";
	}

	//获取理财优选
	private void getOptimizationDOList(Model model){
			Map<String, Object> params = new HashMap<>();
		params.put("offset",0);
		params.put("limit",3);
		Query query = new Query(params);
		List<OptimizationDO> optimezatinoList = optimizationService.list(query);
		for (int i = 0; i < optimezatinoList.size(); i++) {
			optimezatinoList.get(i).setPercentage((optimezatinoList.get(i).getHasinvestamount())
							.divide(optimezatinoList.get(i).getBorrowamount(),2,ROUND_HALF_UP).multiply(new BigDecimal("100")));
		}
		model.addAttribute("optimezatinoList", optimezatinoList);
	}

	@ResponseBody
	@GetMapping("/open/list")
	public PageUtils opentList(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<ContentDO> bContentList = bContentService.list(query);
		int total = bContentService.count(query);
		PageUtils pageUtils = new PageUtils(bContentList, total);
		return pageUtils;
	}

	@GetMapping("/open/post/{cid}")
	String post(@PathVariable("cid") Long cid, Model model) {
		ContentDO bContentDO = bContentService.get(cid);
		model.addAttribute("bContent", bContentDO);
		model.addAttribute("gtmModified", DateUtils.format(bContentDO.getGtmModified()));
		return "blog/index/post";
	}
	@GetMapping("/open/page/{categories}")
	String about(@PathVariable("categories") String categories, Model model) {
		Map<String, Object> map = new HashMap<>(16);
		map.put("categories", categories);
		ContentDO bContentDO =null;
		if(bContentService.list(map).size()>0){
			 bContentDO = bContentService.list(map).get(0);
		}
		model.addAttribute("bContent", bContentDO);
		return "blog/index/post";
	}

}
