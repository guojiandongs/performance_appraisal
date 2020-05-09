package com.bootdo.system.scheduled;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.system.domain.VerifyDataDO;
import com.bootdo.system.service.VerifyDataService;
import com.bootdo.system.service.impl.VerifyDataServiceImpl;
import com.kingdom.kop.framework.utils.LogUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhangLei on 2020/1/2 0002
 */
@Configuration
@EnableScheduling
public class WxVerifyScheduled {
    private static final Logger logger = LoggerFactory.getLogger(WxVerifyScheduled.class);
    private GetWXTokenAndTicket getWXTokenAndTicket = new GetWXTokenAndTicket();
    private VerifyDataService verifyDataService = new VerifyDataServiceImpl();

    private static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";// 获取access

    public String SECRET;
    //@Scheduled(cron = "/5 * * * ?") // 每10分钟执行一次
    @Scheduled(fixedRate = 1000*60*60*2)// 每2xiaoshi执行一次
    public void getToken() {
        logger.info("getToken定时任务启动"+DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }
    /**
     * 定时任务获取微信ticket
     */
    //@Scheduled(fixedRate = 1000*60*60*2)// 每2xiaoshi执行一次
    /*public void resSignature() {
        JSONObject jsonObj = new JSONObject();
        LogUtil.debug("======================定时任务开始====================");
        try {
            Map<String, Object> map = new HashMap<>();
            List<VerifyDataDO> verifyDataDOList = verifyDataService.list(map);
            if(!CollectionUtils.isNotEmpty(verifyDataDOList)){
                logger.error("verifyDataService.list(map)=null");
                return;
            }
            VerifyDataDO verifyDataDO = verifyDataDOList.get(0);
            logger.error("verifyDataDO.toString="+verifyDataDO.toString());
            String APP_ID = verifyDataDO.getAppid();
            String SECRET = verifyDataDO.getSecret();
            String access_token = getWXTokenAndTicket.getToken(GET_TOKEN_URL,APP_ID,SECRET);
            if (!access_token.isEmpty() && !access_token.equals("")) {
                String jsapi_ticket = getWXTokenAndTicket.getTicket(access_token);
                VerifyDataDO vddo = new VerifyDataDO();
                vddo.setAppid(APP_ID);
                vddo.setAccessToken(access_token);
                vddo.setJsapiTicket(jsapi_ticket);
                int verfity = verifyDataService.update(vddo);
                if(verfity==1){
                    logger.info("微信jsapi_ticket更新成功");
                }else{
                    logger.info("微信jsapi_ticket接口调取失败！！！！！");
                }
                logger.info("==access_token=="+access_token+"==");
                logger.info("==jsapi_ticket=="+jsapi_ticket+"==");
            }else{
                logger.info("获取微信access_token为空");
            }
        } catch (Exception e) {
            logger.error("获取微信门票ticket异常",e);
            e.printStackTrace();
        }
        logger.info("==========定时任务结束=========");
    }*/
}
