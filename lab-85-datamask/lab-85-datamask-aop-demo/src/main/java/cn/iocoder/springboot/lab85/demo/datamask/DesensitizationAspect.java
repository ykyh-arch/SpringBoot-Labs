package cn.iocoder.springboot.lab85.demo.datamask;

import cn.iocoder.springboot.lab85.demo.response.Page;
import cn.iocoder.springboot.lab85.demo.response.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * DesensitizationAspect
 *
 * @author jaquez
 * @date 2022/08/31 17:18
 **/
@Slf4j
@Component
@Aspect
public class DesensitizationAspect {

    /**
     * @description: 切入点
     * @return:
     * @author: Ming
     * @time: 2022/6/22
     */
    @Pointcut("execution(* cn.iocoder.springboot.lab85.demo.controller.*.*(..))")
    public void pointCut() {
    }

    /**
     * @description: 返回值处理
     * @return:
     * @author: Ming
     * @time: 2022/6/22
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // 可接入用户权限 typeEnums 表示用户拥有哪些字段的查看权限，没有的就脱敏处理
        List<ReadableSensitiveTypeEnum> typeEnums = new ArrayList<>();
        log.info("typeEnums: {}",typeEnums);
        Object obj = proceedingJoinPoint.proceed();
        if (obj == null || isPrimitive(obj.getClass())) {
            return obj;
        }
        // 处理数据
        dealData(obj,typeEnums);
        return obj;
    }

    /**
     * @description: 基本数据类型和 String 类型判断
     * @return:
     * @author: Ming
     * @time: 2022/6/23
     */
    private boolean isPrimitive(Class<?> clz) {
        try {
            if (String.class.isAssignableFrom(clz) || clz.isPrimitive()) {
                return true;
            } else {
                return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @description: 数据处理
     * @return:
     * @author: Ming
     * @time: 2022/6/23
     */
    private void dealData(Object obj,List<ReadableSensitiveTypeEnum> typeEnums){
        if (null == obj) {return;}
        if (obj.getClass().isPrimitive()) {return;}// 是否是基本数据类型
        if (obj.getClass().isInterface()) {return;}// 是否是接口
        // 以下根据返回的数据结构处理
        Object data = ((R) obj).getObj();
        if(data != null){
            Class<?> clazz = data.getClass();
            if (clazz.equals(Page.class)) {
                Page page = (Page) data;
                List<?> record = page.getRecords();
                for (Object o : record) {
                    Field[] fields = o.getClass().getDeclaredFields();
                    replace(fields,o,typeEnums);
                }
            }else {
                Field[] fields = clazz.getDeclaredFields();
                replace(fields,data,typeEnums);
            }
        }
    }

    /**
     * @description: 脱敏敏感字段
     * @return:
     * @author: Ming
     * @time: 2022/6/23
     */
    private void replace(Field[] fields,Object o,List<ReadableSensitiveTypeEnum> typeEnums){
        try {
            for (Field f : fields) {
                if(f != null){
                    // 设置 private 字段可访问
                    f.setAccessible(true);
                    // 处理自定义 vo 作为属性（属性类型非自身类对象类型的属性）
                    CustomEntityDesensitizationVerify custom = f.getAnnotation(CustomEntityDesensitizationVerify.class);
                    if(custom != null){
                        Object customEntity = f.get(o);
                        Field[] entityFiled = customEntity.getClass().getDeclaredFields();
                        replace(entityFiled, customEntity,typeEnums);
                    }
                    // 处理 list 属性
                    Class<?> curFieldType = f.getType();
                    if (curFieldType.equals(List.class)) {
                        List<?> record = (List<?>) f.get(o);
                        if(record != null && !record.isEmpty() ){
                            for (Object obj :record) {
                                Field[] ff = obj.getClass().getDeclaredFields();
                                replace(ff,obj,typeEnums);
                            }
                        }
                    }
                    // 处理普通字符串字段
                    ReadableSensitiveVerify annotation = f.getAnnotation(ReadableSensitiveVerify.class);
                    if(annotation != null){
                        f.getType();
                        String valueStr = (String) f.get(o);
                        if(StringUtils.isNotEmpty(valueStr)){
                            ReadableSensitiveTypeEnum type = annotation.value();
                            if(type.equals(ReadableSensitiveTypeEnum.NAME) && !typeEnums.contains(type)){
                                f.set(o, DesensitizationUtils.desensitizedName(valueStr));
                            }
                            if(type.equals(ReadableSensitiveTypeEnum.ID_CARD) && !typeEnums.contains(type) ){
                                f.set(o, DesensitizationUtils.desensitizedIdNumber(valueStr));
                            }
                            if(type.equals(ReadableSensitiveTypeEnum.ADDRESS) && !typeEnums.contains(type)){
                                f.set(o, DesensitizationUtils.desensitizedAddress(valueStr));
                            }
                            if(type.equals(ReadableSensitiveTypeEnum.PHONE) && !typeEnums.contains(type)){
                                f.set(o, DesensitizationUtils.desensitizedPhoneNumber(valueStr));
                            }
                            if(type.equals(ReadableSensitiveTypeEnum.BANK_CARD_NO) && !typeEnums.contains(type)){
                                f.set(o, DesensitizationUtils.desensitizedAddressBankCardNum(valueStr));
                            }
                            if(type.equals(ReadableSensitiveTypeEnum.EMAIL) && !typeEnums.contains(type)){
                                f.set(o, DesensitizationUtils.desensitizationEmail(valueStr));
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
