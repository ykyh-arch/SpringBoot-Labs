package cn.iocoder.springboot.lab33.shirodemo.config;

import cn.iocoder.springboot.lab33.shirodemo.oauth2.OAuth2Filter;
import cn.iocoder.springboot.lab33.shirodemo.oauth2.OAuth2Realm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 配置类，参考：https://www.iocoder.cn/Fight/Spring-Boot-+-Vue-+-Shiro-realizes-front-and-rear-end-separation-and-permission-control/?self
 *
 * @author jaquez
 * @date 2021/10/05 12:18
 **/
@Configuration
public class ShiroConfig {

    /**
     * 安全管理器
     * @param oAuth2Realm
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(OAuth2Realm oAuth2Realm) {
        // 创建 DefaultWebSecurityManager 对象
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置其使用的 Realm 为 OAuth2Realm
        securityManager.setRealm(oAuth2Realm);
        // 无需使用记住密码功能
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        // 创建 ShiroFilterFactoryBean 对象，用于创建 ShiroFilter 过滤器
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        // 设置 SecurityManager
        shiroFilter.setSecurityManager(securityManager);
        // 创建 OAuth2Filter 过滤器，并设置名字为 oauth2 。从请求头中获取 token 并执行登录认证
        Map<String, Filter> filters = new HashMap<>();
        filters.put("oauth2", new OAuth2Filter());
        shiroFilter.setFilters(filters);
        // filterMap 设置
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/druid/**", "anon");
        filterMap.put("/app/**", "anon");
        filterMap.put("/sys/login", "anon"); // 登录接口
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/captcha.jpg", "anon"); // 验证码
        filterMap.put("/**", "oauth2"); // 默认剩余的 URL ，需要经过认证
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }

    /**
     * Shiro 生命周期处理器
     * @author Jaquez
     * @date 2021/10/07 14:38
     * @return org.apache.shiro.spring.LifecycleBeanPostProcessor
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启 Shiro 的注解（如：@RequiresRoles，@RequiresPermissions）
     * 需借助 SpringAOP 扫描使用 Shiro 注解的类，并在必要时进行安全逻辑验证
     * 配置以下两个 bean（DefaultAdvisorAutoProxyCreator(可选)和 AuthorizationAttributeSourceAdvisor）即可实现此功能
     * @author Jaquez
     * @date 2021/10/07 14:39
     * @return org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
