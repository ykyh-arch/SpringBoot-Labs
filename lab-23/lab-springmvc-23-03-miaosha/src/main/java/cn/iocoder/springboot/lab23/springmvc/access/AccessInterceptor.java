package cn.iocoder.springboot.lab23.springmvc.access;

import java.io.OutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import cn.iocoder.springboot.lab23.springmvc.domain.MiaoshaUser;
import cn.iocoder.springboot.lab23.springmvc.redis.AccessKey;
import cn.iocoder.springboot.lab23.springmvc.redis.RedisService;
import cn.iocoder.springboot.lab23.springmvc.result.CodeMsg;
import cn.iocoder.springboot.lab23.springmvc.result.Result;
import cn.iocoder.springboot.lab23.springmvc.service.MiaoshaUserService;
/**
 * 任何一个API对应一个Handler
 * @author Jaquez
 * @date 2021/05/17 10:11
 */
@Service
public class AccessInterceptor  extends HandlerInterceptorAdapter{
	
	@Autowired
	MiaoshaUserService userService;
	
	@Autowired
	RedisService redisService;

	/**
	 * 预处理
	 * @author Jaquez
	 * @date 2021/05/17 10:13
	 * @param request
	 * @param response
	 * @param handler
	 * @return boolean
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//SpringMvc中重要的一个类HandlerMethod
		if(handler instanceof HandlerMethod) {
			MiaoshaUser user = getUser(request, response);
			cn.iocoder.springboot.lab23.springmvc.access.UserContext.setUser(user);
			HandlerMethod hm = (HandlerMethod)handler;
			cn.iocoder.springboot.lab23.springmvc.access.AccessLimit accessLimit = hm.getMethodAnnotation(cn.iocoder.springboot.lab23.springmvc.access.AccessLimit.class);
			if(accessLimit == null) {
				return true;
			}
			int seconds = accessLimit.seconds();
			int maxCount = accessLimit.maxCount();
			boolean needLogin = accessLimit.needLogin();
			String key = request.getRequestURI();
			if(needLogin) {
				if(user == null) {
					render(response, CodeMsg.SESSION_ERROR);
					return false;
				}
				key += "_" + user.getId();
			}else {
				//do nothing
			}
			AccessKey ak = AccessKey.withExpire(seconds);
			Integer count = redisService.get(ak, key, Integer.class);
	    	if(count  == null) {
	    		 redisService.set(ak, key, 1);
	    	}else if(count < maxCount) {
	    		 redisService.incr(ak, key);
	    	}else {
	    		render(response, CodeMsg.ACCESS_LIMIT_REACHED);
	    		return false;
	    	}
		}
		return true;
	}
	
	private void render(HttpServletResponse response, CodeMsg cm)throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		String str  = JSON.toJSONString(Result.error(cm));
		out.write(str.getBytes("UTF-8"));
		out.flush();
		out.close();
	}

	private MiaoshaUser getUser(HttpServletRequest request, HttpServletResponse response) {
		String paramToken = request.getParameter(MiaoshaUserService.COOKI_NAME_TOKEN);
		String cookieToken = getCookieValue(request, MiaoshaUserService.COOKI_NAME_TOKEN);
		if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
			return null;
		}
		String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
		return userService.getByToken(response, token);
	}

	/**
	 * 获取cookie值
	 * @author Jaquez
	 * @date 2021/05/17 10:16
	 * @param request
	 * @param cookiName
	 * @return java.lang.String
	 */
	private String getCookieValue(HttpServletRequest request, String cookiName) {
		Cookie[]  cookies = request.getCookies();
		if(cookies == null || cookies.length <= 0){
			return null;
		}
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals(cookiName)) {
				return cookie.getValue();
			}
		}
		return null;
	}
	
}
