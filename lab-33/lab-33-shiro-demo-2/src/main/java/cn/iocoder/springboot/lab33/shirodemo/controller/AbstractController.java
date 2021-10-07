package cn.iocoder.springboot.lab33.shirodemo.controller;

import cn.iocoder.springboot.lab33.shirodemo.dataobject.SysUser;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AbstractController 类
 *
 * @author jaquez
 * @date 2021/10/05 19:00
 **/
public class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }

}
