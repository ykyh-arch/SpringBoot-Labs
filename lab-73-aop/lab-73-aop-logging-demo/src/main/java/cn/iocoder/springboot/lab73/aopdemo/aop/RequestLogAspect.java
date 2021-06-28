package cn.iocoder.springboot.lab73.aopdemo.aop;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求日志切面，参考：https://mp.weixin.qq.com/s/Y2Dk5EJweUl3fvmyE72L-A
 * 切面-》切点-》通知注解
 * @Before => 在切点之前执行代码
 * @After => 在切点之后执行代码
 * @AfterReturning => 切点返回内容后执行代码，可以对切点的返回值进行封装
 * @AfterThrowing => 切点抛出异常后执行
 * @Around => 环绕，在切点前后执行代码
 * @author jaquez
 * @date 2021/06/28 14:31
 **/
@Component
@Aspect
public class RequestLogAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestLogAspect.class);

    /**
     * 申明一个切点
     * @author Jaquez
     * @date 2021/06/28 14:49
     * @return void
     */
    @Pointcut("execution(* cn.iocoder.springboot.lab73.aopdemo.controller..*(..))")
    public void requestServer() {
    }

    /**
     * 在进入Controller方法前，打印出调用方IP、请求URL、HTTP请求类型、调用的方法名
     * @author Jaquez
     * @date 2021/06/28 15:00
     * @param joinPoint
     * @return void
     */
    @Before("requestServer()")
    public void doBefore(JoinPoint joinPoint) {
        //获取请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER.info("IP                 : {}", request.getRemoteAddr());
        LOGGER.info("URL                : {}", request.getRequestURL().toString());
        LOGGER.info("HTTP Method        : {}", request.getMethod());
        //签名对应方法，返回结果为：cn.iocoder.springboot.lab73.aopdemo.controller.DemoController.echo
        LOGGER.info("Class Method       : {}.{}",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName());
    }

    /**
     * 使用@Around打印进入控制层的入参
     * @author Jaquez
     * @date 2021/06/28 15:29
     * @param proceedingJoinPoint
     * @return java.lang.Object
     */
    @Around("requestServer()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        //获取Http请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //执行方法
        Object result = proceedingJoinPoint.proceed();
        /*LOGGER.info("Request Params       : {}", getRequestParams(proceedingJoinPoint));
        LOGGER.info("Result               : {}", result);
        LOGGER.info("Time Cost            : {} ms", System.currentTimeMillis() - start);*/
        //数据封装，目的是为了防止高并发下，数据串行化的问题
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setIp(request.getRemoteAddr());
        requestInfo.setUrl(request.getRequestURL().toString());
        requestInfo.setHttpMethod(request.getMethod());
        requestInfo.setClassMethod(String.format("%s.%s", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                proceedingJoinPoint.getSignature().getName()));
        requestInfo.setRequestParams(getRequestParamsByProceedingJoinPoint(proceedingJoinPoint));
        requestInfo.setResult(result);
        requestInfo.setTimeCost(System.currentTimeMillis() - start);
        LOGGER.info("Request Info      : {}", JSON.toJSONString(requestInfo));

        return result;
    }

    /**
     * 获取请求参数
     * @author Jaquez
     * @date 2021/06/28 15:35
     * @param proceedingJoinPoint
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    private Map<String, Object> getRequestParams(ProceedingJoinPoint proceedingJoinPoint) {
        Map<String, Object> requestParams = new HashMap<>();

        //参数名
        String[] paramNames = ((MethodSignature)proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();

        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];

            //如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                value = file.getOriginalFilename();  //获取文件名
            }

            requestParams.put(paramNames[i], value);
        }

        return requestParams;
    }

    @After("requestServer()")
    public void doAfter(JoinPoint joinPoint) {
        LOGGER.info("This         : {}", joinPoint.getThis());
        LOGGER.info("Target       : {}", joinPoint.getTarget());
    }

    /**
     * 对请求异常处理
     * @author Jaquez
     * @date 2021/06/28 16:11
     * @param joinPoint
     * @param e
     * @return void
     */
    @AfterThrowing(pointcut = "requestServer()", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, RuntimeException e) {
        //获取请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //封装请求错误对象
        RequestErrorInfo requestErrorInfo = new RequestErrorInfo();
        requestErrorInfo.setIp(request.getRemoteAddr());
        requestErrorInfo.setUrl(request.getRequestURL().toString());
        requestErrorInfo.setHttpMethod(request.getMethod());
        requestErrorInfo.setClassMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName()));
        requestErrorInfo.setRequestParams(getRequestParamsByJoinPoint(joinPoint));
        requestErrorInfo.setException(e);
        LOGGER.info("Error Request Info      : {}", JSON.toJSONString(requestErrorInfo));
    }

    /**
     * 获取入参
     * @param proceedingJoinPoint
     *
     * @return
     * */
    private Map<String, Object> getRequestParamsByProceedingJoinPoint(ProceedingJoinPoint proceedingJoinPoint) {
        //参数名
        String[] paramNames = ((MethodSignature)proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();

        return buildRequestParam(paramNames, paramValues);
    }

    private Map<String, Object> getRequestParamsByJoinPoint(JoinPoint joinPoint) {
        //参数名
        String[] paramNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = joinPoint.getArgs();

        return buildRequestParam(paramNames, paramValues);
    }

    private Map<String, Object> buildRequestParam(String[] paramNames, Object[] paramValues) {
        Map<String, Object> requestParams = new HashMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];
            //如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                value = file.getOriginalFilename();  //获取文件名
            }

            requestParams.put(paramNames[i], value);
        }

        return requestParams;
    }

    @Data
    public class RequestInfo {
        private String ip;
        private String url;
        private String httpMethod;
        private String classMethod;
        private Object requestParams;
        private Object result;
        private Long timeCost;

    }

    @Data
    public class RequestErrorInfo {
        private String ip;
        private String url;
        private String httpMethod;
        private String classMethod;
        private Object requestParams;
        private RuntimeException exception;
    }

}
