package com.xxl.sso.core.user;

import java.io.Serializable;
import java.util.Map;

/**
 * xxl sso user，单点登录对象，
 * 数据格式：XxlSsoUser{userid='1000', username='user', plugininfo=null, version='d01d2dff3c00416c9837525c2494f5b0', expireMinute=1440, expireFreshTime=1634785581561}
 *
 * @author xuxueli 2018-04-02 19:59:49
 */
public class XxlSsoUser implements Serializable {
    private static final long serialVersionUID = 42L;

    // field
    private String userid;
    private String username;
    private Map<String, String> plugininfo;

    private String version;
    private int expireMinute;
    private long expireFreshTime;


    // set get
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, String> getPlugininfo() {
        return plugininfo;
    }

    public void setPlugininfo(Map<String, String> plugininfo) {
        this.plugininfo = plugininfo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getExpireMinute() {
        return expireMinute;
    }

    public void setExpireMinute(int expireMinute) {
        this.expireMinute = expireMinute;
    }

    public long getExpireFreshTime() {
        return expireFreshTime;
    }

    public void setExpireFreshTime(long expireFreshTime) {
        this.expireFreshTime = expireFreshTime;
    }

    @Override
    public String toString() {
        return "XxlSsoUser{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", plugininfo=" + plugininfo +
                ", version='" + version + '\'' +
                ", expireMinute=" + expireMinute +
                ", expireFreshTime=" + expireFreshTime +
                '}';
    }

}
