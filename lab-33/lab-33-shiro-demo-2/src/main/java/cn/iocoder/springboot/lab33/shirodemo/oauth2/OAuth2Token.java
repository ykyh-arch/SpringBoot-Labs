
package cn.iocoder.springboot.lab33.shirodemo.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 认证 token
 * @author Jaquez
 * @date 2021/10/05 12:28
 */
public class OAuth2Token implements AuthenticationToken {

    private String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
