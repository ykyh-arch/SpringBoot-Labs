package cn.iocoder.springboot.lab76.idempotent.service;

import javax.servlet.http.HttpServletRequest;

public interface ITokenService {
    /**
     * 创建token
     * @return
     */
    public  String createToken();

    /**
     * 检验token
     * @param request 从请求头中获取token
     * @return
     */
    public boolean checkToken(HttpServletRequest request) throws Exception;
}
