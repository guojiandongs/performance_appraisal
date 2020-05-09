package com.bootdo.portal.util;

import com.alibaba.fastjson.JSONObject;
import com.kingdom.kop.framework.utils.LogUtil;
import com.szkingdom.kingdom.CommonOperation.CommonAction;
import com.szkingdom.kingdom.api.sdk.KOAuthToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilTools {


    private static String api_prefixion = "kingdom.kifp.";
    private static String api_version = ",v1.0";

    public static List doKouath4List(String api_bex, HashMap paramsMap, KOAuthToken token, GxphKoauthCommentService oauthService) {
//        IUserService us = UserServiceFactory.getUserService();
        String doBexid = UtilTools.api_prefixion + api_bex + UtilTools.api_version;
        try {
            return oauthService.dokoauthListMsg(doBexid, paramsMap, token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map doKouath4Map(String api_bex, HashMap paramsMap, KOAuthToken token, GxphKoauthCommentService oauthService) {
//        IUserService us = UserServiceFactory.getUserService();
        String doBexid = UtilTools.api_prefixion + api_bex + UtilTools.api_version;
        try {
            return oauthService.dokoauthListForMap(doBexid, paramsMap, token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject doKouath4Json(String api_bex, HashMap paramsMap, KOAuthToken token, GxphKoauthCommentService oauthService) {
//		IUserService us		= UserServiceFactory.getUserService();
        String doBexid = UtilTools.api_prefixion + api_bex + UtilTools.api_version;

        JSONObject obj;
        try {
            obj = oauthService.dokoauthJsonObjectMsg(doBexid, paramsMap, token);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return obj;
    }

    /**
     * 对象为空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {

        return obj == null;
    }

    /**
     * 对象不为空
     *
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * map为空
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map map) {

        return map == null || map.isEmpty();
    }

    /**
     * map不为空
     *
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    /**
     * list为空
     *
     * @param list
     * @return
     */
    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    /**
     * list不为空
     *
     * @param list
     * @return
     */
    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }

    /**
     * 字符串为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    /**
     * 字符串不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 正则表达式验证
     * @param regex
     * @param str
     * @return
     */
    public static boolean match(String regex,String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public  Map<String,String>  validata(String name, KOAuthToken token, GxphKoauthCommentService oauthService) {
        String a=null;
        List<Map> list=new ArrayList<Map>();
        Map<String,String> map1=new HashMap<String,String>();
        try{
            HashMap param = new HashMap();
            param.put("project_type","kasp");
            param.put("client_type",name);
            CommonAction c = CommonAction.getInstance();
            String apinameAndVersion = "kingdom.kifp.select_verifier,v1.0";
            list = oauthService.dokoauthTokenListMsg(apinameAndVersion, param, token);
            for (Map m:list){
                map1.put((String)m.get("path_key"),(String)m.get("path_value"));
            }
        }catch (Exception e){
            LogUtil.info(e);
        }
        return  map1;
    }

}
