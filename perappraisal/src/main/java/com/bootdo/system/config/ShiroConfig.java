package com.bootdo.system.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.bootdo.common.config.Constant;
import com.bootdo.common.redis.shiro.RedisCacheManager;
import com.bootdo.common.redis.shiro.RedisManager;
import com.bootdo.common.redis.shiro.RedisSessionDAO;
import com.bootdo.system.shiro.UserRealm;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig
{

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.cache.type}")
    private String cacheType;

    @Value("${server.session-timeout}")
    private int tomcatTimeout;

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor()
    {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public ShiroDialect shiroDialect()
    {
        return new ShiroDialect();
    }
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        LinkedHashMap filterChainDefinitionMap = new LinkedHashMap();
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/getVerify", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/docs/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/upload/**", "anon");
        filterChainDefinitionMap.put("/files/**", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/blog", "anon");
        filterChainDefinitionMap.put("/blog/open/**", "anon");
        filterChainDefinitionMap.put("/home/wdhome/**", "anon");
        filterChainDefinitionMap.put("/home/**", "anon");
        filterChainDefinitionMap.put("/home/wechat/**", "anon");

        filterChainDefinitionMap.put("/fund", "anon");
        filterChainDefinitionMap.put("/fund/**", "anon");
        filterChainDefinitionMap.put("/changephone/**", "anon");
        filterChainDefinitionMap.put("/setphone/**", "anon");
        filterChainDefinitionMap.put("/portal/HomePageWechat/**", "anon");
        filterChainDefinitionMap.put("/userasset/**", "anon");
        filterChainDefinitionMap.put("/usermail/**", "anon");
        filterChainDefinitionMap.put("/transferReco/**", "anon");
        filterChainDefinitionMap.put("/home/yzm/**", "anon");
        filterChainDefinitionMap.put("/wechat/**", "anon");
        filterChainDefinitionMap.put("/satisfaction/**", "anon");
        filterChainDefinitionMap.put("/commitment/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(userRealm());

        if (Constant.CACHE_TYPE_REDIS.equals(this.cacheType))
            securityManager.setCacheManager(rediscacheManager());
        else {
            securityManager.setCacheManager(ehCacheManager());
        }
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }
    @Bean
    UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager)
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public RedisManager redisManager()
    {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(this.host);
        redisManager.setPort(this.port);
        redisManager.setExpire(1800);

        redisManager.setPassword(this.password);
        return redisManager;
    }

    public RedisCacheManager rediscacheManager()
    {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO()
    {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }
    @Bean
    public SessionDAO sessionDAO() {
        if (Constant.CACHE_TYPE_REDIS.equals(this.cacheType)) {
            return redisSessionDAO();
        }
        return new MemorySessionDAO();
    }

    @Bean
    public DefaultWebSessionManager sessionManager()
    {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(this.tomcatTimeout * 1000);
        sessionManager.setSessionDAO(sessionDAO());
        Collection listeners = new ArrayList();
        listeners.add(new BDSessionListener());
        sessionManager.setSessionListeners(listeners);
        return sessionManager;
    }
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManager(cacheManager());
        return em;
    }
    @Bean({"cacheManager2"})
    CacheManager cacheManager() {
        return CacheManager.create();
    }
}