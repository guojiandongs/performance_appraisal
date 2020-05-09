package com.bootdo.system.scheduled;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kingdom.kop.framework.utils.LogUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZhangLei on 2020/1/2 0002
 */
@Service
public class GetWXTokenAndTicket {
    /**
     * 获取token
     * @param apiurl
     * @param appid
     * @param secret
     * @return
     */
    public static String getToken(String apiurl, String appid, String secret) {
        String turl = String.format("%s?grant_type=client_credential&appid=%s&secret=%s", apiurl,appid, secret);

        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(turl);
        JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
        String result = null;
        try {
            HttpResponse res = client.execute(get);
            String responseContent = null; // 响应内容
            HttpEntity entity = res.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
            JsonObject json = jsonparer.parse(responseContent).getAsJsonObject();
            // 将json字符串转换为json对象
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                if (json.get("errcode") != null) {
                    result = "获取access_token出问题了";
                    // 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
                } else {
                    // 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
                    result = json.get("access_token").getAsString();
                }
            }
        } catch (Exception e) {
            LogUtil.error("获取access_token异常", e);
        } finally {
            // 关闭连接 ,释放资源
            client.getConnectionManager().shutdown();
            return result;
        }
    }

    /**
     * 通过token获取ticket
     * @param token
     * @return
     */
    /*public static String getTicket(String token) {
        String turl = String.format("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi",token);
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(turl);
        JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
        String result = null;

        try {
            HttpResponse res = client.execute(get);
            String responseContent = null; // 响应内容
            HttpEntity entity = res.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
            JsonObject json = jsonparer.parse(responseContent).getAsJsonObject();
            // 将json字符串转换为json对象
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                if (json.get("errcode").getAsInt() == 0) {
                    result = json.get("ticket").getAsString();
                } else {
                    result = "获取ticket出问题了";
                }
            }
        } catch (Exception e) {
            LogUtil.error("获取ticket异常", e);
        } finally {
            // 关闭连接 ,释放资源
            client.getConnectionManager().shutdown();
            return result;
        }
    }*/
}
