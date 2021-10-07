
package cn.iocoder.springboot.lab33.shirodemo.service.impl;

import cn.iocoder.springboot.lab33.shirodemo.dataobject.SysUserToken;
import cn.iocoder.springboot.lab33.shirodemo.mapper.SysUserTokenMapper;
import cn.iocoder.springboot.lab33.shirodemo.oauth2.OAuth2TokenGenerator;
import cn.iocoder.springboot.lab33.shirodemo.service.SysUserTokenService;
import cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("sysUserTokenService")
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenMapper, SysUserToken> implements SysUserTokenService {

    // 12小时后过期
    private final static int EXPIRE = 3600 * 12;

    /**
     * 用户有且仅有唯一的 token
     * @author Jaquez
     * @date 2021/10/06 09:52
     * @param userId
     * @return cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper
     */
    @Override
    public ResultWrapper createToken(long userId) {
        // 生成一个 token
        String token = OAuth2TokenGenerator.generateValue();
        // 当前时间
        Date now = new Date();
        // 过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
        // 判断是否生成过 token
        SysUserToken sysUserToken = this.getById(userId);
        if (sysUserToken == null) { // 新增 SysUserToken
            sysUserToken = new SysUserToken();
            sysUserToken.setUserId(userId);
            sysUserToken.setToken(token);
            sysUserToken.setUpdateTime(now);
            sysUserToken.setExpireTime(expireTime);
            // 保存 token
            this.save(sysUserToken);
        } else { // 更新 SysUserToken
            sysUserToken.setToken(token);
            sysUserToken.setUpdateTime(now);
            sysUserToken.setExpireTime(expireTime);

            // 更新 token
            this.updateById(sysUserToken);
        }
        // 返回 token 和过期时间
        return ResultWrapper.ok().put("token", token).put("expire", EXPIRE);
    }

    /**
     * 创建一个新的 token 值，从而使用户当前的 Token 失效。优化：可以在表中添加一个删除标识或删除记录
     * @author Jaquez
     * @date 2021/10/06 11:02
     * @param userId
     * @return void
     */
    @Override
    public void logout(long userId) {

        // 生成一个 token
        String token = OAuth2TokenGenerator.generateValue();

        // 修改 token
        SysUserToken tokenEntity = new SysUserToken();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        this.updateById(tokenEntity);
    }
}
