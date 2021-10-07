
package cn.iocoder.springboot.lab33.shirodemo.oauth2;

import cn.iocoder.springboot.lab33.shirodemo.dataobject.SysUser;
import cn.iocoder.springboot.lab33.shirodemo.dataobject.SysUserToken;
import cn.iocoder.springboot.lab33.shirodemo.service.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 自定义一个 Realm 认证器
 * @author Jaquez
 * @date 2021/10/05 12:24
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用，即当配置文件配置或方法使用了权限注解)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获得 SysUser 对象
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        Long userId = user.getUserId();

        // 用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);

        // 创建 SimpleAuthorizationInfo 对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 权限信息
        info.setStringPermissions(permsSet);
        // 角色信息
        // info.setRoles(null);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        // 根据 accessToken ，查询用户信息
        SysUserToken tokenEntity = shiroService.queryByToken(accessToken);
        // token 失效
        if (tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new IncorrectCredentialsException("token 无效或失效，请重新登录");
        }

        //  查询用户信息
        SysUser user = shiroService.queryUser(tokenEntity.getUserId());
        // 账号锁定
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定，请联系管理员");
        }

        // 创建 SimpleAuthenticationInfo 对象，存储当前用户认证信息，参考：https://github.com/apache/shiro/blob/master/core/src/main/java/org/apache/shiro/authc/SimpleAuthenticationInfo.java
        return new SimpleAuthenticationInfo(user, accessToken, getName());
    }

}
