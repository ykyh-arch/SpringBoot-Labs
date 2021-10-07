
package cn.iocoder.springboot.lab33.shirodemo.exception;

import cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 自定义异常处理器
 * @author Jaquez
 * @date 2021/10/05 15:12
 */
@RestControllerAdvice
public class MyExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(MyException.class)
	public ResultWrapper handleMyException(MyException e){
		ResultWrapper r = new ResultWrapper();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());

		return r;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResultWrapper handlerNoFoundException(Exception e) {
		logger.error(e.getMessage(), e);
		return ResultWrapper.error(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResultWrapper handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return ResultWrapper.error("数据库中已存在该记录");
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResultWrapper handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return ResultWrapper.error("没有权限，请联系管理员授权");
	}

	@ExceptionHandler(Exception.class)
	public ResultWrapper handleException(Exception e){
		logger.error(e.getMessage(), e);
		return ResultWrapper.error();
	}
}
