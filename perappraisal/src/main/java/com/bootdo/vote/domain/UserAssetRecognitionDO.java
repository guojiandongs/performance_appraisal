package com.bootdo.vote.domain;

import java.io.Serializable;
import java.util.Date;

public class UserAssetRecognitionDO
        implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String custid;
    private String openid;
    private String chineseName;
    private String documentType;
    private String documentId;
    private String stillprincipaltotal;
    private String allInterests;
    private String withdrawal;
    private String bankName;
    private String bankId;
    private String bankMobile;
    private Date recordsDate;

    public void setCustid(String custid)
    {
        this.custid = custid;
    }

    public String getCustid()
    {
        return this.custid;
    }

    public void setOpenid(String openid)
    {
        this.openid = openid;
    }

    public String getOpenid()
    {
        return this.openid;
    }

    public void setChineseName(String chineseName)
    {
        this.chineseName = chineseName;
    }

    public String getChineseName()
    {
        return this.chineseName;
    }

    public void setDocumentType(String documentType)
    {
        this.documentType = documentType;
    }

    public String getDocumentType()
    {
        return this.documentType;
    }

    public void setDocumentId(String documentId)
    {
        this.documentId = documentId;
    }

    public String getDocumentId()
    {
        return this.documentId;
    }

    public void setStillprincipaltotal(String stillprincipaltotal)
    {
        this.stillprincipaltotal = stillprincipaltotal;
    }

    public String getStillprincipaltotal()
    {
        return this.stillprincipaltotal;
    }

    public void setAllInterests(String allInterests)
    {
        this.allInterests = allInterests;
    }

    public String getAllInterests()
    {
        return this.allInterests;
    }

    public void setWithdrawal(String withdrawal)
    {
        this.withdrawal = withdrawal;
    }

    public String getWithdrawal()
    {
        return this.withdrawal;
    }

    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }

    public String getBankName()
    {
        return this.bankName;
    }

    public void setBankId(String bankId)
    {
        this.bankId = bankId;
    }

    public String getBankId()
    {
        return this.bankId;
    }

    public void setBankMobile(String bankMobile)
    {
        this.bankMobile = bankMobile;
    }

    public String getBankMobile()
    {
        return this.bankMobile;
    }

    public void setRecordsDate(Date recordsDate)
    {
        this.recordsDate = recordsDate;
    }

    public Date getRecordsDate()
    {
        return this.recordsDate;
    }
}