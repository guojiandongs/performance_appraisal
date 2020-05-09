package com.bootdo.satisfaction.controller;

import com.bootdo.commitment.domain.PerformanceCommitmentContentDO;
import com.bootdo.commitment.domain.PerformanceCommitmentEvaluateDO;
import com.bootdo.commitment.service.PerformanceCommitmentContentService;
import com.bootdo.commitment.service.PerformanceCommitmentEvaluateService;
import com.bootdo.common.utils.R;
import com.bootdo.satisfaction.config.AppConfig;
import com.bootdo.satisfaction.config.UrlConstant;
import com.bootdo.satisfaction.domain.DepartmentDTO;
import com.bootdo.satisfaction.domain.ServiceResult;
import com.bootdo.satisfaction.domain.UserDTO;
import com.bootdo.satisfaction.serivce.TokenService;
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
import com.taobao.api.ApiException;
import org.apache.commons.collections4.CollectionUtils;
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
 * 承诺书考评
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-01-02 11:29:01
 */
 
@Controller
@RequestMapping("/commitment")
public class CommitMentController {
    private static final Logger log = LoggerFactory.getLogger(CommitMentController.class);

    @Value("${corp_id}")
    private String corpid;

    @Autowired
    private PerformanceCommitmentContentService performanceCommitmentContentService;
    @Autowired
    private PerformanceCommitmentEvaluateService performanceCommitmentEvaluateService;

    private TokenService tokenService;
    private AppConfig appConfig;

    @Autowired
    public CommitMentController(
            TokenService tokenService,
            AppConfig appConfig
    ) {
        this.tokenService = tokenService;
        this.appConfig = appConfig;
    }
    @GetMapping("/gotoCommitmentList")
    String gotoCommitmentList(@RequestParam Map<String, Object> params,Model model){
        //查询列表数据
        model.addAttribute("corpid",corpid);
        List<PerformanceCommitmentContentDO> contentList = performanceCommitmentContentService.list(new HashMap<>());
        model.addAttribute("contentList",contentList);
        return "satisfaction/commitment";
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

        String accessToken;

        // 获取accessToken
        ServiceResult<String> accessTokenSr = tokenService.getAccessToken("commitment");
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
        UserDTO userDto = getUser(accessToken, userIdSr.getResult());
        List<DepartmentDTO> departmentList = listDepartment();
        Map<String,Object> map = new HashMap<>();
        /*List<DepartmentDTO> departmentList = new ArrayList<>();
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("运营部");
        departmentDTO.setId(111l);
        departmentDTO.setParentid(1l);
        departmentList.add(departmentDTO);
        DepartmentDTO departmentDTO1 = new DepartmentDTO();
        departmentDTO1.setName("产品部");
        departmentDTO1.setId(222l);
        departmentDTO1.setParentid(1l);
        departmentList.add(departmentDTO1);
        DepartmentDTO departmentDTO2 = new DepartmentDTO();
        departmentDTO2.setName("人事部");
        departmentDTO2.setId(333l);
        departmentDTO2.setParentid(1l);
        departmentList.add(departmentDTO2);
        UserDTO userDto = new UserDTO();
        userDto.setName("郭建东");
        userDto.setUserid("1");
        userDto.setMobile("18335184320");*/
        map.put("userDto",userDto);
        map.put("departmentList",departmentList);

        for (int i = 0; i < departmentList.size(); i++) {
            DepartmentDTO depart = departmentList.get(i);
            Long departId = depart.getId();
            List<UserDTO> userDTOList = listDepartmentUsers(departId);
            depart.setUserDTOList(userDTOList);
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("userDto", userDto);
        String userid = userDto.getUserid();
        Map<String, Object> evaluateMap = new HashMap<>();
        evaluateMap.put("appraisalUserId",userid);
        List<PerformanceCommitmentEvaluateDO> evaluateList = performanceCommitmentEvaluateService.list(evaluateMap);
        System.out.println("departmentList========="+departmentList);
        map.put("evaluateList",evaluateList);
        return R.ok(map);
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
        ServiceResult<String> accessTokenSr = tokenService.getAccessToken("commitment");
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

    /*@PostMapping("/user/simplelist")
    @ResponseBody
    public R listDepartmentUsers(Long id) {*/
    public List<UserDTO> listDepartmentUsers(Long id) {
        String accessToken;
        // 获取accessToken
        ServiceResult<String> accessTokenSr = tokenService.getAccessToken("commitment");
        if (!accessTokenSr.isSuccess()) {
            log.error("response.getErrorCode()===========Failed to {}", accessTokenSr.getCode(), accessTokenSr.getMessage());
            //return ServiceResult.failure(accessTokenSr.getCode(), accessTokenSr.getMessage());
        }
        accessToken = accessTokenSr.getResult();

        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.URL_USER_SIMPLELIST);
        OapiUserSimplelistRequest request = new OapiUserSimplelistRequest();
        request.setDepartmentId(id);
        request.setOffset(1l);
        request.setSize(50l);
        request.setOrder("entry_desc");
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
            return results;
        }else{
            return Collections.emptyList();
        }
        /*List<UserDTO> results = new ArrayList<>();
        UserDTO user = new UserDTO();
        user.setUserid("123");
        user.setName("张磊");
        results.add(user);
        UserDTO user1 = new UserDTO();
        user1.setUserid("234");
        user1.setName("界宏宇");
        results.add(user1);
        UserDTO user2 = new UserDTO();
        user2.setUserid("2345");
        user2.setName("绩效抓");
        results.add(user2);
        for (int i = 0; i < 8; i++) {
            UserDTO u = new UserDTO();
            u.setUserid(i+"");
            u.setName("测试"+i);
            results.add(u);
        }
        Map map = new HashMap();
        map.put("results",results);
        //return R.ok(map);
        return results;*/
    }

    /**
     * 添加考核
     * @param options
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/saveCommitment")
    public R saveCommitment(@RequestParam String options,HttpServletRequest request){
        try{
            HttpSession session = request.getSession(true);
            UserDTO userDto = (UserDTO) session.getAttribute("userDto");
            System.out.println("options======="+options);
            String[] optionArray = options.split(",");
            for (int i = 0; i < optionArray.length; i++) {
                String commitmentContentStr = optionArray[i];
                String[] commitmentContentArray = commitmentContentStr.split("_");
                PerformanceCommitmentEvaluateDO evaluateDO = new PerformanceCommitmentEvaluateDO();
                String commitmentId = commitmentContentArray[0];
                String commitmentContent = commitmentContentArray[1];
                String departmentId = commitmentContentArray[2];
                String departmentName = commitmentContentArray[3];
                String userName = commitmentContentArray[4];
                String userId = commitmentContentArray[5];
                evaluateDO.setCommitmentId(Integer.parseInt(commitmentId));
                evaluateDO.setCommitmentContent(commitmentContent);
                evaluateDO.setCommitmentDepartmentId(Integer.parseInt(departmentId));
                evaluateDO.setCommitmentDepartmentName(departmentName);
                evaluateDO.setCommitmentUserId(userId);
                evaluateDO.setCommitmentUserName(userName);
                evaluateDO.setAppraisalUserId(userDto.getUserid());
                evaluateDO.setCommitmentDate(new Date());
                performanceCommitmentEvaluateService.save(evaluateDO);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("msg","提交成功!");
            map.put("code",200);
            return R.ok(map);
        }catch (Exception e){
            System.out.println("添加考核失败原因====="+e);
            return R.error("提交失败，请稍后再试！");
        }
    }
}
