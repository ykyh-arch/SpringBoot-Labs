
package cn.iocoder.springboot.lab33.shirodemo.anno;

import cn.iocoder.springboot.lab33.shirodemo.dataobject.SysUser;
import cn.iocoder.springboot.lab33.shirodemo.utils.HttpContextUtils;
import cn.iocoder.springboot.lab33.shirodemo.utils.IPUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 日通日志，切面处理
 * @author Jaquez
 * @date 2021/10/06 11:18
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {


	@Pointcut("@annotation(cn.iocoder.springboot.lab33.shirodemo.anno.SysLog)")
	public void logPointCut() { 
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		// 执行方法
		Object result = point.proceed();
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		// 保存日志
		saveSysLog(point, time);
		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		cn.iocoder.springboot.lab33.shirodemo.dataobject.SysLog sysLog = new cn.iocoder.springboot.lab33.shirodemo.dataobject.SysLog();
		SysLog syslog = method.getAnnotation(SysLog.class);
		if(syslog != null){
			// 注解上的描述
			sysLog.setOperation(syslog.value());
		}

		// 请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");

		// 请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			String params = new Gson().toJson(args);
			sysLog.setParams(params);
		}catch (Exception e){
		}

		// 获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		// 设置IP地址
		sysLog.setIp(IPUtils.getIpAddr(request));

		// 用户名
		String username = ((SysUser) SecurityUtils.getSubject().getPrincipal()).getUsername();
		sysLog.setUsername(username);

		sysLog.setTime(time);
		sysLog.setCreateDate(new Date());

		log.info("系统日志信息为：{}",sysLog);
	}
}
