package cn.iocoder.springboot.lab28.task.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Spring工具类
 * @author Jaquez
 * @date 2021/07/11 12:49
 */
@Component
public class SpringUtils implements ApplicationContextAware, DisposableBean {

    /** 容器对象持有 */
    private static ApplicationContext context;

    /**
     * 获取容器对象
     * @author yangfan
     * @createTime 2019-08-02 11:46:06
     * @return org.springframework.context.ApplicationContext
     */
    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * 通过类型获取
     * @author yangfan
     * @createTime 2019-08-02 11:51:35
     * @param requiredType bean类型
     * @return T
     */
    public static <T> T getBean(Class<T> requiredType) {
        check();
        return context.getBean(requiredType);
    }

    /**
     * 通过名称获取
     * @author yangfan
     * @createTime 2019-08-02 11:52:00
     * @param nameName 名称
     * @return T
     */
    public static <T> T getBean(String nameName) {
        check();
        return (T) context.getBean(nameName);
    }

    /**
     * 通过类型尝试获取bean，若没有则返回null
     * @author yangfan
     * @createTime 2019-08-02 11:52:26
     * @param requiredType 尝试获取的类型
     * @return T
     */
    public static <T> T tryBean(Class<T> requiredType) {
        return containsBean(requiredType) ? context.getBean(requiredType) : null;
    }

    /**
     * 判断容器中是否有指定类型的bean
     * @author yangfan
     * @createTime 2019-08-02 11:50:52
     * @param requiredType 对象类型
     * @return boolean
     */
    public static boolean containsBean(Class<?> requiredType) {
        check();
        String[] exists = context.getBeanNamesForType(requiredType);
        return exists != null && exists.length > 0;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        context = null;
    }

    private static void check() {
        Assert.notNull(context, "ApplicationContext is null");
    }
    
    public static String[] getNullPropertyNames(Object source,Set<String> defaultIgnorePropertys) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        if (null != defaultIgnorePropertys && defaultIgnorePropertys.size() > 0){
            emptyNames.addAll(defaultIgnorePropertys);
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src,null));
    }

    public static void copyPropertiesIgnoreNull(Object src, Object target,Set<String> defaultIgnorePropertys) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src,defaultIgnorePropertys));
    }
}
