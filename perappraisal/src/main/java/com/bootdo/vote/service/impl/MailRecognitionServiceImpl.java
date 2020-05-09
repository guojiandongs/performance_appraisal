package com.bootdo.vote.service.impl;

import com.bootdo.vote.domain.MailRecognitionDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.vote.dao.MailRecognitionDao;
import com.bootdo.vote.service.MailRecognitionService;



@Service
public class MailRecognitionServiceImpl implements MailRecognitionService {
	@Autowired
	private MailRecognitionDao mailRecognitionDao;
	
	@Override
	public MailRecognitionDO get(String custid){
		return mailRecognitionDao.get(custid);
	}
	
	@Override
	public List<MailRecognitionDO> list(Map<String, Object> map){
		return mailRecognitionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return mailRecognitionDao.count(map);
	}
	
	@Override
	public int save(MailRecognitionDO mailRecognition){
		return mailRecognitionDao.save(mailRecognition);
	}
	
	@Override
	public int update(MailRecognitionDO mailRecognition){
		return mailRecognitionDao.update(mailRecognition);
	}
	
	@Override
	public int remove(String custid){
		return mailRecognitionDao.remove(custid);
	}
	
	@Override
	public int batchRemove(String[] custids){
		return mailRecognitionDao.batchRemove(custids);
	}

    public MailRecognitionDO getByOpenid(String openid)
    {
        return this.mailRecognitionDao.getByOpenid(openid);
    }

}
