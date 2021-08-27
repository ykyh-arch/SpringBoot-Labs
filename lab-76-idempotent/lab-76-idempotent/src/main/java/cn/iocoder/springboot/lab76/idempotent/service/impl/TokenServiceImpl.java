package cn.iocoder.springboot.lab76.idempotent.service.impl;

import cn.iocoder.springboot.lab76.idempotent.exception.ServiceException;
import cn.iocoder.springboot.lab76.idempotent.service.ITokenService;
import cn.iocoder.springboot.lab76.idempotent.utils.Constant;
import cn.iocoder.springboot.lab76.idempotent.common.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * IokenService 实现类
 *
 * @author jaquez
 * @date 2021/08/27 15:03
 **/
@Service
public class TokenServiceImpl implements ITokenService {

    /**
     * token引用了redis服务，创建token采用随机算法工具类生成随机uuid字符串,然后放入到redis中(为了防止数据的冗余保留,这里设置过期时间为10000秒,具体可视业务而定)，如果放入成功，最后返回这个token值。checkToken方法就是从header中获取token到值(如果header中拿不到，就从paramter中获取)，如若不存在,直接抛出异常。这个异常信息可以被拦截器捕捉到，然后返回给前端。
     */
    @Autowired
    private RedisService redisService;

    /**
     * 创建token
     *
     * @return
     */
    @Override
    public String createToken() {
        String str = UUID.randomUUID().toString().toUpperCase().replaceAll("-","");
        StringBuilder token = new StringBuilder();
        try {
            token.append(Constant.Redis.TOKEN_PREFIX).append(str);
            redisService.setEx(token.toString(), token.toString(),100000L);
            boolean notEmpty = !StringUtils.isEmpty(token.toString());
            if (notEmpty) {
                return token.toString();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }


    /**
     * 检验token
     *
     * @param request
     * @return
     */
    @Override
    public boolean checkToken(HttpServletRequest request) throws Exception {

        String token = request.getHeader(Constant.TOKEN_NAME);
        // header中不存在token
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(Constant.TOKEN_NAME);
            // parameter中也不存在token
            if (StringUtils.isEmpty(token)) {
                throw new ServiceException(Constant.ResponseCode.ILLEGAL_ARGUMENT, 100);
            }
        }
        // 校验
        if (!redisService.exists(token)) {
            throw new ServiceException(Constant.ResponseCode.REPETITIVE_OPERATION, 200);
        }

        boolean remove = redisService.remove(token);
        if (!remove) {
            throw new ServiceException(Constant.ResponseCode.REPETITIVE_OPERATION, 200);
        }
        return true;
    }
}
