package cn.iocoder.springboot.lab47.demo.spi.contextclassloader;

import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * 参考：https://www.iocoder.cn/Fight/SPI-mechanism-in-Spring-Boot/?self
 * 线程上下文类加载器，通常我们可以通过Thread.currentThread().getClassLoader()和Thread.currentThread().getContextClassLoader()获取线程上下文类加载器。
 * 使用类加载器加载资源文件，比如jar包下资源
 * 常见的类加载器获取方式：
 * 一、Thread.currentThread().getClassLoader()
 * 二、Thread.currentThread().getContextClassLoader()
 * 三、this.getClass().getClassLoader()
 * 四、ServiceLoader.load(Class clazz) JDK加载固定类路径下文件的一个加载器
 * 获取资源
 * 一、通过类加载器#getResources(String name)
 * 二、通过类加载器#getSystemResources(String name)
 * 三、通过类加载器#getResourceAsStream(String name)
 * @author jaquez
 * @date 2021/07/17 17:33
 **/
public class ContextClassLoaderTest {
    /**
     * SPI约定
     * 当服务的提供者，提供了服务接口的一种实现之后，在JAR包的META-INF/services/目录里同时创建一个以服务接口命名的文件。该文件里就是实现该服务接口的具体实现类。而当外部程序装配这个模块的时候，就能通过该jar包META-INF/services/里的配置文件找到具体的实现类名，并装载实例化，完成模块的注入。通过这个约定，就不需要把服务放在代码中了，通过模块被装配的时候就可以发现服务类了。
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Array.class的完整路径
        String name = "java/sql/Array.class";
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(name);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            // 获取资源路径
            System.out.println(url.toString());
        }

        // 测试 spring spi 机制
        Class clazz = Class.forName("org.springframework.boot.autoconfigure.EnableAutoConfiguration");
        ContextClassLoaderTest.loadFactoryNames(clazz,Thread.currentThread().getContextClassLoader());
    }

    // 参考：spring spi 机制
    public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring-spi.factories";
    // spring.factories文件的格式为：key=value1,value2,value3
    // 从所有的jar包中找到META-INF/spring.factories文件
    // 然后从文件中解析出key=factoryClass类名称的所有value值
    public static List<String> loadFactoryNames(Class<?> factoryClass, ClassLoader classLoader) throws IOException {
        String factoryClassName = factoryClass.getName();
        // 取得资源文件的URL
        Enumeration<URL> urls = (classLoader != null ? classLoader.getResources(FACTORIES_RESOURCE_LOCATION) : ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION));
        List<String> result = new ArrayList<String>();
        // 遍历所有的URL
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            // 根据资源文件URL解析properties文件，得到对应的一组@Configuration类
            Properties properties = PropertiesLoaderUtils.loadProperties(new UrlResource(url));
            String factoryClassNames = properties.getProperty(factoryClassName);
            // 组装数据，并返回
            result.addAll(Arrays.asList(StringUtils.commaDelimitedListToStringArray(factoryClassNames)));
        }
        return result; // cn.iocoder.springboot.lab47.demo.config.SwaggerAutoConfiguration  正确拿到数据
    }
}
