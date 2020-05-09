package com.bootdo.vote.service.impl;

import com.bootdo.vote.dao.UserAssetRecognitionDao;
import com.bootdo.vote.domain.UserAssetRecognitionDO;
import com.bootdo.vote.service.UserAssetRecognitionService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAssetRecognitionServiceImpl
        implements UserAssetRecognitionService
{

    @Autowired
    private UserAssetRecognitionDao userAssetRecognitionDao;

    public UserAssetRecognitionDO get(String custid)
    {
        return this.userAssetRecognitionDao.get(custid);
    }

    public UserAssetRecognitionDO getByOpenid(String openid)
    {
        return this.userAssetRecognitionDao.getByOpenid(openid);
    }

    public List<UserAssetRecognitionDO> list(Map<String, Object> map)
    {
        return this.userAssetRecognitionDao.list(map);
    }

    public int count(Map<String, Object> map)
    {
        return this.userAssetRecognitionDao.count(map);
    }

    public int save(UserAssetRecognitionDO userAssetRecognition)
    {
        return this.userAssetRecognitionDao.save(userAssetRecognition);
    }

    public int update(UserAssetRecognitionDO userAssetRecognition)
    {
        return this.userAssetRecognitionDao.update(userAssetRecognition);
    }

    public int remove(String custid)
    {
        return this.userAssetRecognitionDao.remove(custid);
    }

    public int batchRemove(String[] custids)
    {
        return this.userAssetRecognitionDao.batchRemove(custids);
    }
}