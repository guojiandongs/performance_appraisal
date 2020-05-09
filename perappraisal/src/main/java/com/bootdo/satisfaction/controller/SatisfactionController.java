package com.bootdo.satisfaction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.kpi.domain.*;
import com.bootdo.kpi.service.*;
import com.bootdo.satisfaction.config.AppConfig;
import com.bootdo.satisfaction.config.UrlConstant;
import com.bootdo.satisfaction.domain.DepartmentDTO;
import com.bootdo.satisfaction.domain.ServiceResult;
import com.bootdo.satisfaction.domain.UserDTO;
import com.bootdo.satisfaction.serivce.TokenService;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.DeptService;
import com.bootdo.system.service.UserService;
import com.bootdo.vote.domain.VoteActivityListDO;
import com.bootdo.vote.service.MailRecognitionService;
import com.bootdo.vote.service.VoteActivityListService;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiDepartmentListRequest;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.request.OapiUserSimplelistRequest;
import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.dingtalk.api.response.OapiUserSimplelistResponse;
import com.kingdom.kop.framework.utils.LogUtil;
import com.taobao.api.ApiException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 满意度调查
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-01-02 11:29:01
 */
 
@Controller
@RequestMapping("/satisfaction")
public class SatisfactionController {
    private static final Logger log = LoggerFactory.getLogger(SatisfactionController.class);

    @Value("${corp_id}")
    private String corpid;

    @Autowired
    private PerformanceAppraisalService performanceAppraisalService;
    @Autowired
    private PerformanceAppraisalOptionService performanceAppraisalOptionService;
    @Autowired
    private PerformanceAppraisalOptionBackService performanceAppraisalOptionBackService;
    @Autowired
    private PerformanceAppraisalDetailsService performanceAppraisalDetailsService;
    @Autowired
    private PerformanceAppraisalDetailsBackService performanceAppraisalDetailsBackService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private UserService userService;
    @Autowired
    private PerAssessedUserService perAssessedUserService;

    private TokenService tokenService;
    private AppConfig appConfig;

    @Autowired
    public SatisfactionController(
            TokenService tokenService,
            AppConfig appConfig
    ) {
        this.tokenService = tokenService;
        this.appConfig = appConfig;
    }
    @GetMapping("/gotoDepartmentList")
    String gotoDepartmentList(@RequestParam Map<String, Object> params,Model model){
        //查询列表数据
        model.addAttribute("corpid",corpid);
        return "satisfaction/satisfactionDepartmentList";
    }

    /**
     * 钉钉用户登录，显示当前登录用户的userId和名称
     *
     * @param authCode 免登临时authCode
     * @return 当前用户
     */
    @PostMapping("/login")
    @ResponseBody
    public R login(@RequestParam(required=false) String authCode, HttpServletRequest request) {

        /*String accessToken;

        // 获取accessToken
        ServiceResult<String> accessTokenSr = tokenService.getAccessToken("kpi");
        if (!accessTokenSr.isSuccess()) {
            log.error("accessTokenSr.getCode()--=-=-=-="+accessTokenSr.getCode(), accessTokenSr.getMessage());
            //return ServiceResult.failure(accessTokenSr.getCode(), accessTokenSr.getMessage());
        }
        accessToken = accessTokenSr.getResult();

        // 获取用户userId
        ServiceResult<String> userIdSr = getUserInfo(accessToken, authCode);
        if (!userIdSr.isSuccess()) {
            log.error("userIdSr.getCode()--=-=-=-="+userIdSr.getCode(), userIdSr.getMessage());
            //return ServiceResult.failure(userIdSr.getCode(), userIdSr.getMessage());
        }

        // 获取用户详情
        UserDTO userDto = getUser(accessToken, userIdSr.getResult());*/
        //List<DepartmentDTO> departmentList = listDepartment();
        Map<String,Object> map = new HashMap<>();
        UserDTO userDto = new UserDTO();
        /*userDto.setName("郭建东");
        userDto.setUserid("1");
        userDto.setMobile("13333431780");*/
        userDto.setName("郭建东");
        userDto.setUserid("1");
        userDto.setMobile("18335184320");
        map.put("userDto",userDto);
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("mobile",userDto.getMobile());
        userMap.put("deptId","");
        List<UserDO> userList = userService.list(userMap);
        if(userList.size()>0){
            UserDO userDO = userList.get(0);
            Long userid = userDO.getUserId();
            HttpSession session = request.getSession(true);
            session.setAttribute("userDto", userDO);
            //List<DeptDO> departmentList = deptService.list(new HashMap<>());
            Map<String, Object> deptMap = new HashMap<>();
            deptMap.put("appraisalUserId",userid.toString());
            List<PerAssessedUserDO> perAssessedList = perAssessedUserService.perAssessedList(deptMap);
            map.put("departmentList",perAssessedList);
            return R.ok(map);
        }else{
            return R.error("没有权限，请联系管理员");
        }

    }

    /**
     * 访问/user/getuserinfo接口获取用户userId
     *
     * @param accessToken access_token
     * @param authCode    临时授权码
     * @return 用户userId或错误信息
     */
    private ServiceResult<String> getUserInfo(String accessToken, String authCode) {
        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.URL_GET_USER_INFO);
        OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
        request.setCode(authCode);
        request.setHttpMethod("GET");

        OapiUserGetuserinfoResponse response;
        try {
            response = client.execute(request, accessToken);
        } catch (ApiException e) {
            log.error("Failed to {}", UrlConstant.URL_GET_USER_INFO, e);
            return ServiceResult.failure(e.getErrCode(), "Failed to getUserInfo: " + e.getErrMsg());
        }
        if (!response.isSuccess()) {
            return ServiceResult.failure(response.getErrorCode(), response.getErrmsg());
        }

        return ServiceResult.success(response.getUserid());
    }

    /**
     * 访问/user/get 获取用户名称
     *
     * @param accessToken access_token
     * @param userId      用户userId
     * @return 用户名称或错误信息
     */
    private UserDTO getUser(String accessToken, String userId) {
        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.URL_USER_GET);
        OapiUserGetRequest request = new OapiUserGetRequest();
        request.setUserid(userId);
        request.setHttpMethod("GET");

        OapiUserGetResponse response = null;
        try {
            response = client.execute(request, accessToken);
        } catch (ApiException e) {
            log.error("Failed to {}", UrlConstant.URL_USER_GET, e);
            //return R.error("服务器异常，稍后再试!");
            ServiceResult.failure(e.getErrCode(), "Failed to getUserName: " + e.getErrMsg());
        }

        UserDTO user = new UserDTO();
        user.setName(response.getName());
        user.setUserid(response.getUserid());
        user.setAvatar(response.getAvatar());
        user.setMobile(response.getMobile());
        System.out.println("UserDTO==========="+user);
        return user;
        //return ServiceResult.success(user);
    }


    private List<DepartmentDTO> listDepartment() {
        String accessToken;
        // 获取accessToken
        ServiceResult<String> accessTokenSr = tokenService.getAccessToken("kpi");
        if (!accessTokenSr.isSuccess()) {
            log.error("accessTokenSr.getCode()======"+accessTokenSr.getCode()+accessTokenSr.getMessage());
            //return ServiceResult.failure(accessTokenSr.getCode(), accessTokenSr.getMessage());
        }
        accessToken = accessTokenSr.getResult();

        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.URL_DEPARTMENT_LIST);
        OapiDepartmentListRequest request = new OapiDepartmentListRequest();
        request.setId("1");
        request.setHttpMethod("GET");

        OapiDepartmentListResponse response = null;
        try {
            response = client.execute(request, accessToken);
        } catch (ApiException e) {
            log.error("Failed to {}", UrlConstant.URL_DEPARTMENT_LIST, e);
            //return ServiceResult.failure(e.getErrCode(), "Failed to listDepartment: " + e.getErrMsg());
        }
        if (!response.isSuccess()) {
            log.error("response.getErrorCode()===========Failed to {}", response.getErrorCode(), response.getErrmsg());
            //return ServiceResult.failure(response.getErrorCode(), response.getErrmsg());
        }

        if (CollectionUtils.isNotEmpty(response.getDepartment())) {
            List<DepartmentDTO> results = new ArrayList<>(response.getDepartment().size());
            for (OapiDepartmentListResponse.Department department : response.getDepartment()) {
                DepartmentDTO departmentDTO = new DepartmentDTO();
                departmentDTO.setId(department.getId());
                departmentDTO.setName(department.getName());
                departmentDTO.setCreateDeptGroup(department.getCreateDeptGroup());
                departmentDTO.setAutoAddUser(department.getAutoAddUser());
                departmentDTO.setParentid(department.getParentid());
                results.add(departmentDTO);
            }
            System.out.println("department============"+results);
            return results;
        }
        return Collections.emptyList();
    }

    @GetMapping("/user/simplelist")
    public String listDepartmentUsers(
            @RequestParam("department_id") Long id,
            @RequestParam(name = "offset", required = false, defaultValue = "1") Long offset,
            @RequestParam(name = "size", required = false, defaultValue = "50") Long size,
            @RequestParam(name = "order", required = false, defaultValue = "entry_desc") String order
    ,Model model,HttpServletRequest request) {
        /*String accessToken;
        // 获取accessToken
        ServiceResult<String> accessTokenSr = tokenService.getAccessToken("kpi");
        if (!accessTokenSr.isSuccess()) {
            log.error("response.getErrorCode()===========Failed to {}", accessTokenSr.getCode(), accessTokenSr.getMessage());
            //return ServiceResult.failure(accessTokenSr.getCode(), accessTokenSr.getMessage());
        }
        accessToken = accessTokenSr.getResult();

        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.URL_USER_SIMPLELIST);
        OapiUserSimplelistRequest request = new OapiUserSimplelistRequest();
        request.setDepartmentId(id);
        request.setOffset(offset);
        request.setSize(size);
        request.setOrder(order);
        request.setHttpMethod("GET");

        OapiUserSimplelistResponse response = null;
        try {
            response = client.execute(request, accessToken);
        } catch (ApiException e) {
            log.error("Failed to listDepartment:  Failed to {}", UrlConstant.URL_DEPARTMENT_LIST,  e.getErrCode()+e.getErrMsg());
            //return ServiceResult.failure(e.getErrCode(), "Failed to listDepartment: " + e.getErrMsg());
        }
        if (!response.isSuccess()) {
            log.error("!response.isSuccess()  Failed to {}", UrlConstant.URL_DEPARTMENT_LIST,  response.getErrorCode(), response.getErrmsg());
            //return ServiceResult.failure(response.getErrorCode(), response.getErrmsg());
        }

        if (CollectionUtils.isNotEmpty(response.getUserlist())) {
            List<UserDTO> results = new ArrayList<>(response.getUserlist().size());
            for (OapiUserSimplelistResponse.Userlist userlist : response.getUserlist()) {
                UserDTO user = new UserDTO();
                user.setUserid(userlist.getUserid());
                user.setName(userlist.getName());
                results.add(user);
            }
            model.addAttribute("userList",results);
        }else{
            model.addAttribute("userList",Collections.emptyList());
        }*/
        HttpSession session = request.getSession(true);
        UserDO userDO = (UserDO)session.getAttribute("userDto");
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("mobile",userDO.getMobile());
        userMap.put("deptId","");
        List<UserDO> userList = userService.list(userMap);
        if(userList.size()>0){
            Long userid = userList.get(0).getUserId();
            Map<String, Object> deptMap = new HashMap<>();
            deptMap.put("appraisalUserId",userid.toString());
            deptMap.put("deptId",id);
            List<PerAssessedUserDO> perAssessedList = perAssessedUserService.perAssessedList(deptMap);
            model.addAttribute("perAssessedList",perAssessedList);
        }
        return "satisfaction/satisfactionUserList";
    }

    @GetMapping("/gotoSatisfaction")
    public String gotoSatisfaction(@RequestParam String userId,Model model,HttpServletRequest request){
        //查询列表数据
        Map<String, Object> map = new HashMap<>();
        map.put("isOpen",1);
        List<PerformanceAppraisalDO> list = performanceAppraisalService.list(map);
        PerformanceAppraisalDO performanceAppraisalDO = new PerformanceAppraisalDO();
        if(list.size()>0){
            performanceAppraisalDO = list.get(0);
        }
        //考核选项查询
        List<PerformanceAppraisalOptionDO> optionList = performanceAppraisalOptionService.list(new HashMap<>());
        Map<String, Object> userMap = new HashMap<>();
        /*String accessToken;
        // 查询被考核人信息
        ServiceResult<String> accessTokenSr = tokenService.getAccessToken("kpi");
        if (!accessTokenSr.isSuccess()) {
            log.error("response.getErrorCode()===========Failed to {}", accessTokenSr.getCode(), accessTokenSr.getMessage());
            //return ServiceResult.failure(accessTokenSr.getCode(), accessTokenSr.getMessage());
        }
        accessToken = accessTokenSr.getResult();

        UserDTO userDto1 = getUser(accessToken, userId);
        String mobile = userDto1.getMobile();
        userMap.put("phone",mobile);
        //userMap.put("phone","18734801509");
        List<PerformanceAppraisalUserDO> userList = performanceAppraisalUserService.list(userMap);
        PerformanceAppraisalUserDO performanceAppraisalUserDO = new PerformanceAppraisalUserDO();
        if(userList.size()>0){
            performanceAppraisalUserDO = userList.get(0);
        }
        model.addAttribute("userInfo",performanceAppraisalUserDO);*/
        UserDO userDO = userService.get(Long.parseLong(userId));
        DeptDO deptDO = deptService.get(userDO.getDeptId());
        userDO.setDeptName(deptDO.getName());
        model.addAttribute("userInfo",userDO);
        model.addAttribute("performanceAppraisal",performanceAppraisalDO);
        model.addAttribute("optionList",optionList);
        //考核人角色查询
        HttpSession session = request.getSession(true);
        UserDO userDto = (UserDO) session.getAttribute("userDto");
        //查询考核记录
        int appraisalId = performanceAppraisalDO.getId();//考核信息id
        String appraisalUserId = String.valueOf(userDto.getUserId());//考核人id
        int examinee_UserId = Integer.parseInt(userId); //被考核人id
        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("appraisalUserId",appraisalUserId);
        detailMap.put("examinee_UserId",examinee_UserId);
        detailMap.put("appraisalId",appraisalId);
        List<PerformanceAppraisalDetailsDO> detailList = performanceAppraisalDetailsService.list(detailMap);
        model.addAttribute("detailList",detailList);
        List<DetailTotal> detailTotalList = performanceAppraisalDetailsService.total(new HashMap<>());
        List<DetailTotal> userDetailTotalList = performanceAppraisalDetailsService.total(detailMap);
        DetailTotal detailTotal = new DetailTotal();
        if(userDetailTotalList.size()>0){
            detailTotal = userDetailTotalList.get(0);
        }
        model.addAttribute("optionDOList",detailTotalList);
        model.addAttribute("detailTotal",detailTotal);
        DeptDO deptDOO = deptService.get(userDto.getDeptId());
        model.addAttribute("deptinfo",deptDOO);

        Map<String, Object> optionbackMap = new HashMap<>();
        List<PerformanceAppraisalOptionBackDO> optionbackList = performanceAppraisalOptionBackService.groupType(optionbackMap);
        List<OptionBack> optionBackList = new ArrayList<>();
        List<PerformanceAppraisalDetailsBackDO> detailBackList = performanceAppraisalDetailsBackService.list1(detailMap);
        for (int i = 0; i < optionbackList.size(); i++) {
            OptionBack optionBack = new OptionBack();
            String appraisalType = optionbackList.get(i).getAppraisalType();
            optionBack.setAppraisalType(appraisalType);
            Map<String, Object> optionbackTypeMap = new HashMap<>();
            optionbackTypeMap.put("appraisalType",appraisalType);
            List<PerformanceAppraisalOptionBackDO> backList = performanceAppraisalOptionBackService.list(optionbackTypeMap);
            for (int j = 0; j < backList.size(); j++) {
                PerformanceAppraisalOptionBackDO performanceAppraisalOptionBackDO =  backList.get(j);
                for (int k = 0; k < detailBackList.size(); k++) {
                    String optionId = String.valueOf(detailBackList.get(k).getOptionId());
                    if(optionId.equals(performanceAppraisalOptionBackDO.getId()+"")){
                        backList.get(j).setFlag("true");
                    }
                }
            }
            optionBack.setOptionbackList(backList);
            optionBackList.add(optionBack);
        }
        model.addAttribute("optionBackList",optionBackList);
        return "satisfaction/kpi";
    }

    /**
     * 添加考核
     * @param perdetailListStr
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/saveKpi")
    public R saveKpi(@RequestBody String perdetailListStr,HttpServletRequest request){
        try{
            HttpSession session = request.getSession(true);
            UserDO userDto = (UserDO) session.getAttribute("userDto");
            String userid ="";
            System.out.println("perdetailListStr======="+perdetailListStr);
            JSONObject obj = (JSONObject)JSONObject.parse(perdetailListStr);
            List<PerformanceAppraisalDetailsBackDO> addList = new ArrayList<>();
            String appraisalId = "";
            String appraisalUserId = String.valueOf(userDto.getUserId());
            for (int i = 0; i < obj.size(); i++) {
                String performanceStr = obj.getString("perdetailList["+i+"]");
                if(null!=performanceStr&&!performanceStr.equals("")){
                    PerformanceAppraisalDetailsDO performanceAppraisalDetailsDO = JSONObject.parseObject(performanceStr,PerformanceAppraisalDetailsDO.class);
                    performanceAppraisalDetailsDO.setAppraisalUserId(String.valueOf(userDto.getUserId()));
                    performanceAppraisalDetailsDO.setAppraisalUserName(userDto.getName());
                    performanceAppraisalDetailsDO.setAppraisalMobile(userDto.getMobile());
                    performanceAppraisalDetailsDO.setAppraisalTime(new Date());
                    if(null!=performanceAppraisalDetailsDO.getId()){
                        performanceAppraisalDetailsService.update(performanceAppraisalDetailsDO);
                    }else{
                        performanceAppraisalDetailsService.save(performanceAppraisalDetailsDO);
                    }
                    appraisalId = performanceAppraisalDetailsDO.getAppraisalId();
                    userid = String.valueOf(performanceAppraisalDetailsDO.getExamineeUserId());
                }
                String optionbackStr = obj.getString("optionback"+i);
                if(null!=optionbackStr&&!optionbackStr.equals("")){
                    PerformanceAppraisalDetailsBackDO per = new PerformanceAppraisalDetailsBackDO();
                    per.setAppraisalUserId(appraisalUserId);
                    per.setAppraisalUserName(userDto.getName());
                    per.setAppraisalMobile(userDto.getMobile());
                    per.setAppraisalTime(new Date());
                    per.setOptionId(Integer.parseInt(optionbackStr));
                    per.setExamineeUserId(Integer.parseInt(userid));
                    per.setAppraisalId(Integer.parseInt(appraisalId));
                    performanceAppraisalDetailsBackService.save(per);
                    addList.add(per);
                }
            }

            Map<String, Object> removemap = new HashMap<>();
            removemap.put("examineeUserId",userid);
            removemap.put("appraisalId",appraisalId);
            removemap.put("appraisalUserId",appraisalUserId);
            List<PerformanceAppraisalDetailsBackDO> removeList = performanceAppraisalDetailsBackService.list1(removemap);
            for (int i = 0; i < removeList.size(); i++) {
                performanceAppraisalDetailsBackService.remove(removeList.get(i).getId());
            }
            for (int i = 0; i < addList.size(); i++) {
                performanceAppraisalDetailsBackService.save(addList.get(i));
            }
            Map<String, Object> map = new HashMap<>();
            map.put("userid",userid);
            map.put("msg","提交成功!");
            map.put("code",200);
            return R.ok(map);
        }catch (Exception e){
            System.out.println("添加考核失败原因====="+e);
            return R.error("提交失败，请稍后再试！");
        }
    }

}
