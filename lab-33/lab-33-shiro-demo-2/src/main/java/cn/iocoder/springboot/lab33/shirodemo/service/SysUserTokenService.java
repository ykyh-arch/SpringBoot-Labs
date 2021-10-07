
package cn.iocoder.springboot.lab33.shirodemo.service;

import cn.iocoder.springboot.lab33.shirodemo.dataobject.SysUserToken;
import cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * SysUserTokenService 服务层
 * @author Jaquez
 * @date 2021/10/05 14:47
 */
public interface SysUserTokenService extends IService<SysUserToken> {

    /**
     * 生成token
     * @param userId  用户ID
     */
    ResultWrapper createToken(long userId);

    /**
     * 退出，修改 token 值
     * @param userId  用户ID
     */
    void logout(long userId);

}
