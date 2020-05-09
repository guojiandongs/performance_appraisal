package com.bootdo.vote.service;

import com.bootdo.vote.domain.MailRecognitionDO;
import com.bootdo.vote.domain.UserAssetRecognitionDO;

import java.util.List;
import java.util.Map;

/**
 * 资产确认表
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-02-13 16:01:18
 */
public interface MailRecognitionService {
	
	MailRecognitionDO get(String custid);
	
	List<MailRecognitionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MailRecognitionDO mailRecognition);
	
	int update(MailRecognitionDO mailRecognition);
	
	int remove(String custid);
	
	int batchRemove(String[] custids);

    public abstract MailRecognitionDO getByOpenid(String paramString);
}
