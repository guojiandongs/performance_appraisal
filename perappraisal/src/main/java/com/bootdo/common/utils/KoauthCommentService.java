package com.bootdo.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.szkingdom.kingdom.CommonOperation.CommonAction;
import com.szkingdom.kingdom.api.sdk.KOAuthToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("koauthCommentService")
public class KoauthCommentService {
    private static Log logger = LogFactory.getLog(KoauthCommentService.class);
    public static String FLAG_MSG_SPLIT = ":";

    /**
     * 调用kifp api,并返回JSON对象
     *
     * @param apiNameAndVersion
     * @param paramsMap
     * @return String格式的JSON对象
     * @throws Exception
     */
    public static String dokoauthJSONObjectString(String apiNameAndVersion, Map paramsMap) throws UtilException {
        JSONObject obj = dokoauthJsonObject(apiNameAndVersion, paramsMap);
        String jsonobjectstr;
        if (obj != null) {
            jsonobjectstr = obj.toJSONString();
        } else {
            throw new UtilException("api访问失败， api=" + apiNameAndVersion + "，参数为：" + paramsMap);
        }
        return jsonobjectstr;
    }

    /**
     * 调用kifp api,并返回JSON对象,需要传入token
     *
     * @param apiNameAndVersion
     * @param paramsMap
     * @param token
     * @return
     * @throws UtilException
     */
    public static String dokoauthJSONObjectString(String apiNameAndVersion, Map paramsMap, KOAuthToken token) throws UtilException {
        JSONObject obj = dokoauthJsonObject(apiNameAndVersion, paramsMap, token);
        String jsonobjectstr;
        if (obj != null) {
            jsonobjectstr = obj.toJSONString();
        } else {
            throw new UtilException("api访问失败， api=" + apiNameAndVersion + "，参数为：" + paramsMap);
        }
        return jsonobjectstr;
    }

    public static String dokoauthForFlagMsg(String apiNameAndVersion, Map paramsMap) throws UtilException {
        String flag;
        JSONObject obj = dokoauthJsonObject(apiNameAndVersion, paramsMap);
        if (obj != null && obj.size() > 0) {
            JSONObject jsonv = obj.getJSONObject("kdjson");
            flag = jsonv.getString("flag") + FLAG_MSG_SPLIT + jsonv.getString("msg");
        } else {
            throw new UtilException("获取返回值 flag失败， api=" + apiNameAndVersion + "，参数为：" + paramsMap);
        }
        return flag;
    }

    public static String dokoauthForFlagMsg(String apiNameAndVersion, Map paramsMap, KOAuthToken token) {
        String flag;
        JSONObject obj = dokoauthJsonObject(apiNameAndVersion, paramsMap, token);
        if (obj != null && obj.size() > 0) {
            JSONObject jsonv = obj.getJSONObject("kdjson");
            flag = jsonv.getString("flag") + FLAG_MSG_SPLIT + jsonv.getString("msg");
        } else {
            throw new UtilException("获取返回值 flag失败， api=" + apiNameAndVersion + "，参数为：" + paramsMap);
        }
        return flag;
    }

    public static JSONObject dokoauthJsonObject(String apiNameAndVersion, Map paramsMap, KOAuthToken token) {
        CommonAction c = CommonAction.getInstance();
        return c.commonCopeMethodForJava(paramsMap, apiNameAndVersion, token);
    }

    public static JSONObject dokoauthJsonObject(String apinameAndVersion, Map paramsMap) {
        CommonAction c = CommonAction.getInstance();
        return c.commonCopeMethodForJava(paramsMap, apinameAndVersion);
    }

    /**
     * 以List<Map>格式返回 kdjson.items内容
     *
     * @param apiNameAndVersion
     * @param paramsMap
     * @return
     * @throws UtilException
     */
    public static List<Map> dokoauthList(String apiNameAndVersion, Map paramsMap) throws UtilException {
        List<Map> lists = new ArrayList<Map>();

        CommonAction c = CommonAction.getInstance();
        JSONObject obj = c.commonCopeMethodForJava(paramsMap, apiNameAndVersion);

        lists = getMaps(lists, obj);

        return lists;
    }

    /**
     * 参数为token，以List<Map>格式返回 kdjson.items内容
     *
     * @param apiNameAndVersion
     * @param paramsMap
     * @return
     * @throws UtilException
     */
    public static List<Map> dokoauthList(String apiNameAndVersion, Map paramsMap, KOAuthToken token) throws UtilException {
        List<Map> lists = new ArrayList<Map>();

        CommonAction c = CommonAction.getInstance();
        JSONObject obj = c.commonCopeMethodForJava(paramsMap, apiNameAndVersion, token);

        lists = getMaps(lists, obj);
        return lists;
    }

    private static List<Map> getMaps(List<Map> lists, JSONObject obj) {
        if (obj != null) {
            JSONObject json = obj.getJSONObject("kdjson");
            if ("1".equalsIgnoreCase(json.getString("flag"))) {
                if (json.getIntValue("len") > 0) {
                    JSONArray arr = json.getJSONArray("items");
                    lists = (List) arr;
                }
            }
        }
        return lists;
    }

    /**
     * 得到 json.items[0]的Map对象
     *
     * @param apiNameAndVersion
     * @param paramsMap
     * @return
     */
    public static Map dokoauthListForMap(String apiNameAndVersion, Map paramsMap) {
        Map map = new HashMap(16);
        List<Map> lists = dokoauthList(apiNameAndVersion, paramsMap);
        if (lists.size() > 0) {
            map = lists.get(0);
        }
        return map;
    }

    /**
     * 得到 json.items[0]的Map对象
     *
     * @param apiNameAndVersion
     * @param paramsMap
     * @param token
     * @return
     */
    public static Map dokoauthListForMap(String apiNameAndVersion, Map paramsMap, KOAuthToken token) {
        Map map = new HashMap(16);
        List<Map> lists = dokoauthList(apiNameAndVersion, paramsMap, token);
        if (lists.size() > 0) {
            map = lists.get(0);
        }
        return map;
    }

    public static Integer getTotalCount(List<Map> lists) {
        Integer totalCount = 0;
        if (lists != null && !lists.isEmpty()) {
            Map m = lists.get(0);
            if (m.get("ROWSCOUNT") != null) {
                totalCount = Integer.valueOf(m.get("ROWSCOUNT").toString());
            } else if (m.get("rowscount") != null) {
                totalCount = Integer.valueOf(m.get("rowscount").toString());
            }

        }

        return totalCount;
    }

    public static Integer getTotalPage(Integer totalCount, Integer pageSize) {
        Integer totalPage;
        if ((totalCount % pageSize) == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }

        return totalPage;
    }

    public static List<Map> dokoauthListMsg(String apiNameAndVersion, Map paramsMap) {
        List lists = new ArrayList();

        CommonAction c = CommonAction.getInstance();
        JSONObject obj = c.commonCopeMethodForJava(paramsMap, apiNameAndVersion);

        if (obj != null) {
            JSONObject json = obj.getJSONObject("kdjson");
            if (("1".equalsIgnoreCase(json.getString("flag"))) &&
                    (json.getIntValue("len") > 0)) {
                lists = json.getJSONArray("items");
            }
        }

        return lists;
    }
    public static List<Map> dokoauthListMsg(String apiNameAndVersion, HashMap paramsMap) {
        List lists = new ArrayList();

        CommonAction c = CommonAction.getInstance();
        JSONObject obj = c.commonCopeMethodForJava(paramsMap, apiNameAndVersion);

        if (obj != null) {
            JSONObject json = obj.getJSONObject("kdjson");
            if (("1".equalsIgnoreCase(json.getString("flag"))) &&
                    (json.getIntValue("len") > 0)) {
                lists = json.getJSONArray("items");
            }
        }

        return lists;
    }
}
