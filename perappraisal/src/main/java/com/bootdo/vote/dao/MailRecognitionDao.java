package com.bootdo.vote.dao;

import com.bootdo.vote.domain.MailRecognitionDO;

import java.util.List;
import java.util.Map;

import com.bootdo.vote.domain.UserAssetRecognitionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产确认表
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-02-13 16:01:18
 */
@Mapper
public interface MailRecognitionDao {

	MailRecognitionDO get(String custid);
	
	List<MailRecognitionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MailRecognitionDO mailRecognition);
	
	int update(MailRecognitionDO mailRecognition);
	
	int remove(String custid);
	
	int batchRemove(String[] custids);

    public abstract MailRecognitionDO getByOpenid(String paramString);
}
