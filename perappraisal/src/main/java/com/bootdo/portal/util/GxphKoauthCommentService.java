package com.bootdo.portal.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.R;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.szkingdom.kingdom.CommonOperation.CommonAction;
import com.szkingdom.kingdom.api.sdk.KOAuthToken;

@Service("gxphKoauthCommentService")
public class GxphKoauthCommentService{

	public String dokoauthMsg(String apinameAndVersion,HashMap paramsMap) throws Exception{
		CommonAction c=CommonAction.getInstance();
		JSONObject obj=c.commonCopeMethodForJava(paramsMap, apinameAndVersion);
		String jsonobjectstr = "";
		if (obj!=null) {
			jsonobjectstr = obj.toJSONString();
		}
		return jsonobjectstr;
	}
	public String dokoauthMsg(String apinameAndVersion,HashMap paramsMap,KOAuthToken token) throws Exception{
		CommonAction c=CommonAction.getInstance();
		JSONObject obj=c.commonCopeMethodForJava(paramsMap, apinameAndVersion,token);
		String jsonobjectstr = "";
		if (obj!=null) {
			jsonobjectstr = obj.toJSONString();
		}
		return jsonobjectstr;
	}

	public JSONObject dokoauthJsonObjectMsg(String apinameAndVersion,HashMap paramsMap,KOAuthToken token) throws Exception{
		CommonAction c=CommonAction.getInstance();
		JSONObject obj=c.commonCopeMethodForJava(paramsMap, apinameAndVersion,token);

		return obj;
	}
	public JSONObject dokoauthJsonObjectMsg(String apinameAndVersion,HashMap paramsMap) throws Exception{
		CommonAction c=CommonAction.getInstance();
		JSONObject obj=c.commonCopeMethodForJava(paramsMap, apinameAndVersion);

		return obj;
	}

	public List<Map> dokoauthListMsg(String apinameAndVersion,HashMap paramsMap) throws Exception{

		List<Map> lists = new ArrayList<Map>();

		CommonAction c=CommonAction.getInstance();
		JSONObject obj=c.commonCopeMethodForJava(paramsMap, apinameAndVersion);

		if(obj!=null){
			JSONObject json=obj.getJSONObject("kdjson");
			if("1".equalsIgnoreCase(json.getString("flag"))){
				if(json.getIntValue("len")>0){
					JSONArray arr=json.getJSONArray("items");
					lists = (List)arr;
				}
			}
		}
		return lists;
	}

	public List<Map> dokoauthListMsg(String apinameAndVersion,HashMap paramsMap,KOAuthToken token)throws Exception {
		// TODO Auto-generated method stub
		List<Map> lists = new ArrayList<Map>();

		CommonAction c=CommonAction.getInstance();
		JSONObject obj=c.commonCopeMethodForJava(paramsMap, apinameAndVersion,token);

		if(obj!=null){
			JSONObject json=obj.getJSONObject("kdjson");
			if("1".equalsIgnoreCase(json.getString("flag"))){
				if(json.getIntValue("len")>0){
					JSONArray arr=json.getJSONArray("items");
					lists = (List)arr;
				}
			}
		}
		return lists;
	}

	/**
	 *
	 * @param apinameAndVersion 传入api名称
	 * @param paramsMap 参数集
	 * @param token 登录后的token
	 * @return 返回List
	 * @throws Exception
	 */
	public List<Map> dokoauthTokenListMsg(String apinameAndVersion,HashMap paramsMap,KOAuthToken token)throws Exception {
		// TODO Auto-generated method stub
		List<Map> lists = new ArrayList<Map>();

		JSONObject obj=CommonAction.getInstance().commonCopeMethodForJava(paramsMap, apinameAndVersion,token);

		if(obj!=null){
			JSONObject json=obj.getJSONObject("kdjson");
			if("1".equalsIgnoreCase(json.getString("flag"))){
				if(json.getIntValue("len")>0){
					JSONArray arr=json.getJSONArray("items");
					lists = (List)arr;
				}
			}
		}
		return lists;
	}

	public Map dokoauthMapMsg(String apinameAndVersion,HashMap paramsMap) throws Exception{

		List<Map> lists = new ArrayList<Map>();
		Map map = new HashMap ();
		CommonAction c=CommonAction.getInstance();
		JSONObject obj=c.commonCopeMethodForJava(paramsMap, apinameAndVersion);

		if(obj!=null){
			JSONObject json=obj.getJSONObject("kdjson");
			if("1".equalsIgnoreCase(json.getString("flag"))){
				if(json.getIntValue("len")>0){
					JSONArray arr=json.getJSONArray("items");
					lists = (List)arr;
					if (lists!=null&&lists.size()>0) {
						map = lists.get(0);
					}
				}
			}
		}
		return map;
	}
	public R dokoauthMapMsgFlag(String apinameAndVersion, HashMap paramsMap) throws Exception{

		List<Map> lists = new ArrayList<Map>();
		Map map = new HashMap ();
		CommonAction c=CommonAction.getInstance();
		JSONObject obj=c.commonCopeMethodForJava(paramsMap, apinameAndVersion);

		if(obj!=null){
			JSONObject json=obj.getJSONObject("kdjson");
			if("0".equalsIgnoreCase(json.getString("flag"))){
				return R.error(json.getString("msg"));
			}
		}
		return R.ok();

	}

	/**
	 *
	 * @param paramsMap map参数
	 * @param token 鉴权传入token
	 * @return
	 * @throws Exception
	 */
    public Map dokoauthListForMap(String apinameAndVersion,HashMap paramsMap,KOAuthToken token) throws Exception{

		List<Map> lists = new ArrayList<Map>();

		CommonAction c=CommonAction.getInstance();
		JSONObject obj=c.commonCopeMethodForJava(paramsMap, apinameAndVersion,token);

		if(obj!=null){
			JSONObject json=obj.getJSONObject("kdjson");
			if("1".equalsIgnoreCase(json.getString("flag"))){
				JSONArray arr=json.getJSONArray("items");
				if(arr!=null){
					lists = JSONObject.parseArray(arr.toJSONString(), Map.class);
				}
			}
		}
		Map map = new HashMap();
		if(lists.size()>0){
			map = lists.get(0);
		}
		return map;
	}
	public Integer getTotalCount(List<Map> lists) {
		Integer totalCount = 0;
		if (lists != null && !lists.isEmpty()) {
			Map m = lists.get(0);
			if (m.get("ROWSCOUNT") != null) {
				totalCount = Integer.valueOf(m.get("ROWSCOUNT").toString());
			}else if (m.get("rowscount") != null) {
				totalCount = Integer.valueOf(m.get("rowscount").toString());
			}

		}

		return totalCount;
	}

	public Integer getTotalPage(Integer totalCount , Integer pageSize) {
		Integer totalPage = 0;
		if((totalCount%pageSize)==0){
			totalPage = totalCount / pageSize;
		}
		else{
		    totalPage=totalCount / pageSize + 1;
		}

		return totalPage;
	}


	/**
	 *
	 * @param apinameAndVersion api接口
	 * @param paramsMap 参数map
	 * @param token 鉴权token
	 * @return map
	 */
	public Map dokoauthTokenMapMsg(String apinameAndVersion, Map paramsMap,KOAuthToken token) {
		List<Map> lists = new ArrayList<Map>();
		Map map = new HashMap ();
		JSONObject obj=CommonAction.getInstance().commonCopeMethodForJava(paramsMap, apinameAndVersion,token);

		if(obj!=null){
			JSONObject json=obj.getJSONObject("kdjson");
			if("1".equalsIgnoreCase(json.getString("flag"))){
				if(json.getIntValue("len")>0){
					JSONArray arr=json.getJSONArray("items");
					lists = (List)arr;
					if(lists!=null){
						if (lists.size()>=2) {
							map = lists.get(1);
						}else if(lists.size()>0){
							map = lists.get(0);
						}

					}

				}
			}
		}
		return map;
	}

}
