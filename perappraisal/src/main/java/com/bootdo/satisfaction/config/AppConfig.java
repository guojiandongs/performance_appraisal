package com.bootdo.satisfaction.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author openapi@dingtalk
 * @date 2020/2/4
 */
@Configuration
public class AppConfig {
    @Value("${app_key}")
    private String appKey;

    @Value("${app_secret}")
    private String appSecret;

    @Value("${commit_app_key}")
    private String commitAppKey;

    @Value("${commit_app_secret}")
    private String commitAppSecret;

    @Value("${agent_id}")
    private String agentId;

    @Value("${corp_id}")
    private String corpId;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCommitAppKey() {
        return commitAppKey;
    }

    public void setCommitAppKey(String commitAppKey) {
        this.commitAppKey = commitAppKey;
    }

    public String getCommitAppSecret() {
        return commitAppSecret;
    }

    public void setCommitAppSecret(String commitAppSecret) {
        this.commitAppSecret = commitAppSecret;
    }
}
