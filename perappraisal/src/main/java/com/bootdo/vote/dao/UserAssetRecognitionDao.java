package com.bootdo.vote.dao;

import com.bootdo.vote.domain.UserAssetRecognitionDO;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public abstract interface UserAssetRecognitionDao
{
    public abstract UserAssetRecognitionDO get(String paramString);

    public abstract UserAssetRecognitionDO getByOpenid(String paramString);

    public abstract List<UserAssetRecognitionDO> list(Map<String, Object> paramMap);

    public abstract int count(Map<String, Object> paramMap);

    public abstract int save(UserAssetRecognitionDO paramUserAssetRecognitionDO);

    public abstract int update(UserAssetRecognitionDO paramUserAssetRecognitionDO);

    public abstract int remove(String paramString);

    public abstract int batchRemove(String[] paramArrayOfString);
}