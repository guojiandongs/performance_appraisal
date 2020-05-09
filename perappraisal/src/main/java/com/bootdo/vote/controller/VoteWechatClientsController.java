package com.bootdo.vote.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.blog.domain.ContentDO;
import com.bootdo.blog.service.ContentService;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.R;
import com.bootdo.portal.util.GxphKoauthCommentService;
import com.bootdo.vote.domain.VoteActivityIndexDO;
import com.bootdo.vote.domain.VoteActivityListDO;
import com.bootdo.vote.domain.VoteActivityOptionDO;
import com.bootdo.vote.domain.VoteActivityRecordDO;
import com.bootdo.vote.service.VoteActivityIndexService;
import com.bootdo.vote.service.VoteActivityListService;
import com.bootdo.vote.service.VoteActivityOptionService;
import com.bootdo.vote.service.VoteActivityRecordService;
import com.kingdom.kop.framework.utils.DateUtil;
import com.szkingdom.kingdom.api.sdk.KOAuthToken;
import org.activiti.rest.editor.model.ModelEditorJsonRestResource;
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
 * Created by ZhangLei on 2020/1/2 0002
 */
@Controller
@RequestMapping("/wechat/vote/client")
public class VoteWechatClientsController {
    private static final int LIMIT = 5;
    protected static final Logger logger = LoggerFactory.getLogger(ModelEditorJsonRestResource.class);
    @Autowired
    private VoteActivityListService voteActivityListService;
    @Autowired
    private VoteActivityIndexService voteActivityIndexService;
    @Autowired
    private VoteActivityOptionService voteActivityOptionService;
    @Autowired
    private VoteActivityRecordService voteActivityRecordService;
    @Autowired
    private GxphKoauthCommentService koauthCommentService;

    @Autowired
    private ContentService contentService;

    /**
     * 跳转投票列表页vaidDetail
     * @return
     */
    @GetMapping()
    String VoteActivityList(HttpServletRequest httpRequest, Model model,
                            @RequestParam("tag") String tag){

        HttpSession session = httpRequest.getSession(true);
        session.setAttribute("openid",tag);
        getTechnology(model);
        Map<String,Object> onemap = new HashMap<>();
        onemap.put("limit",LIMIT);
        onemap.put("offset",0);
        onemap.put("isShow","0");
        List<VoteActivityListDO> vavdlist = voteActivityListService.list(onemap);
        model.addAttribute("vavdlist",vavdlist);
        Map<String,Object> map = new HashMap<>();
        List<VoteActivityIndexDO> vaidlist =  voteActivityIndexService.list(map);
        if(vaidlist.size()>0){
            model.addAttribute("vaidDetail",vaidlist.get(0));
        }

        return "vote/client/index";
    }

    /**
     * 跳转到投票页面
     * @param httpRequest
     * @param model
     * @param activityId
     * @return
     */
    @GetMapping("/option")
    String VoteActivityOption(HttpServletRequest httpRequest, Model model,
                              @RequestParam("activityId") String activityId){
        Map<String, Object> recordmap = new HashMap<>();
        recordmap.put("activityId",activityId);
        HttpSession session = httpRequest.getSession(true);
        String openid = (String)session.getAttribute("openid");
        recordmap.put("openid",openid);
        List<VoteActivityRecordDO> recordList = voteActivityRecordService.list(recordmap);
        if(recordList.size()>0){
             return voteResult(httpRequest,model,activityId);
        }else{
            getTechnology(model);
            //查询标题和介绍
            Map<String,Object> alsmap = new HashMap<>();
            VoteActivityListDO vald = voteActivityListService.get(activityId);
            if(!vald.getId().isEmpty()){
                model.addAttribute("vald",vald);
            }
            String msg = "投票规则:";
            int zcs = vald.getPerpersonVoteLimit();
            msg+=zcs>0?" 每人投票上限"+zcs+"次 ":" ";
            int tcs = vald.getDailyVoteLimit();
            msg+=tcs>0?" 每日投票上限"+tcs+"次 ":" ";
            model.addAttribute("xianzhi",msg);
            //查询题目选项
            Map<String,Object> map = new HashMap<>();
            map.put("activityId",activityId);
            List<VoteActivityOptionDO> vaodlist = voteActivityOptionService.list(map);
            if(vaodlist.size()>0){
                model.addAttribute("vaodlist",vaodlist);
            }
            return "vote/client/option";
        }
    }

    public String voteResult(HttpServletRequest httpRequest,Model model,String activityid){
        /**
         * 跳转到投票结果页面
         * @return
         */
            getTechnology(model);
            //查询标题和介绍
            Map<String,Object> alsmap = new HashMap<>();
            VoteActivityListDO vald = voteActivityListService.get(activityid);
            if(!vald.getId().isEmpty()){
                model.addAttribute("vald",vald);
            }
            Map<String,Object> map  = new HashMap<String,Object>();
            map.put("activityId",activityid);
            HttpSession session = httpRequest.getSession(true);
            String openid = (String)session.getAttribute("openid");
            map.put("openid",openid);
            List<Map<String, Object>> vresultlist = voteActivityRecordService.getVoteResult(map);
            if(vresultlist.size()>0){
                String other = "";
                model.addAttribute("vresultlist",vresultlist);
                for (int i = 0; i < vresultlist.size(); i++) {
                    String otherStr = (String)vresultlist.get(i).get("other");
                    if(null!=other&&other.equals("")){
                        other = otherStr;
                    }
                }
                model.addAttribute("other",other);
            }
            return "vote/client/result";
    }
    /**
     * 跳转到投票结果页面
     * @return
     */
    @GetMapping("/toVoteResult")
    String toVoteResult(HttpServletRequest httpRequest, Model model,
                        @RequestParam("activityid") String activityid){
        getTechnology(model);
        //查询标题和介绍
        Map<String,Object> alsmap = new HashMap<>();
        VoteActivityListDO vald = voteActivityListService.get(activityid);
        if(!vald.getId().isEmpty()){
            model.addAttribute("vald",vald);
        }
        Map<String,Object> map  = new HashMap<String,Object>();
        map.put("activityId",activityid);
        HttpSession session = httpRequest.getSession(true);
        String openid = (String)session.getAttribute("openid");
        map.put("openid",openid);
        List<Map<String, Object>> vresultlist = voteActivityRecordService.getVoteResult(map);
        if(vresultlist.size()>0){
            model.addAttribute("vresultlist",vresultlist);
        }
            return "vote/client/result";
    }
    /**
     * 保存投票选项
     * @return
     */
    @PostMapping(value = "/saveOption")
    @ResponseBody
    R saveOption(HttpServletRequest httpRequest,
                 @RequestParam String params,
                 @RequestParam("activityid") String activityid,
                 @RequestParam("other") String other) {
        HttpSession session = httpRequest.getSession(true);
        String openid = (String)session.getAttribute("openid");
        R r = votingOutstandingCustomer( httpRequest);
        if((int)r.get("code")!=0){
            return r;
        }
        JSONArray   paradms = JSON.parseArray(params);
       R checkr =  votingRestrictions( httpRequest, activityid,  paradms);
       if((int)checkr.get("code")==500){
            return checkr;
       }
        try {
            for (int i = 0; i< paradms.size();i++) {
                JSONObject paramjson = (JSONObject) paradms.get(i);
                String name = paramjson.getString("name");
                VoteActivityRecordDO newv = new VoteActivityRecordDO();
                newv.setId(UUID.randomUUID().toString().replace("-", ""));
                newv.setActivityId(paramjson.getString("activityId"));
                newv.setOpenid(openid);
                newv.setOptionId(paramjson.getString("optionId"));
                newv.setVoteDate(new Date());
                newv.setOther(other);
                voteActivityRecordService.save(newv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("提交失败");
        }
        Map map = new HashMap();
        map.put("activityid",activityid);
       return R.ok(map);

    }

    /**
     * 查询该客户是否有未还款项目
     * @return
     */
    R votingOutstandingCustomer(HttpServletRequest httpRequest){
        HashMap resMap = new HashMap();
        Map interestInfoMap = new HashMap();
        HttpSession session = httpRequest.getSession(true);
        String openid = (String)session.getAttribute("openid");
        resMap.put("openID",openid);
        R r = getVoteQualifications(resMap);
        if((int)r.get("code")==0){
            return R.ok();
        }
        return r;
    }
    /**
     * 查询客户是否有未还款项目
     *
     * @param resMap
     * @return
     */
    public R getVoteQualifications(HashMap resMap) {
        R r = new R();
        String apinameAndVersion = "kingdom.kifp.get_voting_outstanding_customer,v1.0";
        try {
            r = koauthCommentService.dokoauthMapMsgFlag(apinameAndVersion, resMap);
        } catch (Exception e) {
            logger.error("查询投票资格异常", e);
        }
        return r;
    }
    /**
     * 投票时间验证
     * @return
     */
    @GetMapping("/votingDateCheck")
    @ResponseBody
    R votingDateCheck(HttpServletRequest httpRequest,@RequestParam("id") String id){

        R r = votingOutstandingCustomer( httpRequest);
        if((int)r.get("code")!=0){
            return r;
        }
        //投票时间验证
        Map<String,Object> dateStartmap  = new HashMap<String,Object>();
        dateStartmap.put("id",id);
        dateStartmap.put("startDate",DateUtils.format(new Date(),"yyyy-MM-dd"));
        int star = voteActivityListService.count(dateStartmap);
        if(star>0){
            return R.error("投票未开始");
        }
        Map<String,Object> dateEndmap  = new HashMap<String,Object>();
        dateEndmap.put("id",id);
        dateEndmap.put("endDate",DateUtils.format(new Date(),"yyyy-MM-dd"));
        int end = voteActivityListService.count(dateEndmap);
        /*if(end>0){
            return R.error("投票已结束");
        }*/
        return R.ok();
    }
    /**
     * 投票限制验证
     * @param httpRequest
     * @param activityid
     * @param paradms 本次投票list
     * @return
     */
    R votingRestrictions(HttpServletRequest httpRequest,String activityid,JSONArray  paradms){

        HttpSession session = httpRequest.getSession(true);
        String openid = (String)session.getAttribute("openid");
        VoteActivityListDO vald = voteActivityListService.get(activityid);
        //每人投票上限
        Integer perpersonVoteLimit = vald.getPerpersonVoteLimit();
        if(perpersonVoteLimit>0){
            Map<String,Object> map  = new HashMap<String,Object>();
            map.put("activityId",activityid);
            map.put("openid",openid);
            int count = voteActivityRecordService.count(map);
            if(count>=perpersonVoteLimit || paradms.size()>perpersonVoteLimit || paradms.size()+count>perpersonVoteLimit){
                return R.error("超过投票次数限制");
            }
        }
        //每日投票上限
        Integer dailyvotelimit = vald.getDailyVoteLimit();
        if(dailyvotelimit>0){
            Map<String,Object> map  = new HashMap<String,Object>();
            map.put("activityId",activityid);
            map.put("openid",openid);
            map.put("voteDate",DateUtils.format(new Date(),"yyyy-MM-dd"));
            int count = voteActivityRecordService.count(map);
            if(count>=dailyvotelimit || count>dailyvotelimit || paradms.size()+count>dailyvotelimit){
                return R.error("超过当日投票次数限制");
            }
        }
            return R.ok();
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
}
