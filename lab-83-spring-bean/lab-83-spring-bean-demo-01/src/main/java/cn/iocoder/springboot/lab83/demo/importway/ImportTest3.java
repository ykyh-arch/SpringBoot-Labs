package cn.iocoder.springboot.lab83.demo.importway;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * import 测试方式三：使用 @Import + ImportBeanDefinitionRegistrar 组合方式
 *
 * @author jaquez
 * @date 2022/01/07 14:31
 **/
@Import(MyImportBeanDefinitionRegistrar.class)
public class ImportTest3 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportTest3.class);
        Person3 person31 = applicationContext.getBean(Person3.class);
        Person3 person32 = applicationContext.getBean("person", Person3.class);
        System.out.println(person31.hashCode()+":"+ person32.hashCode());
        System.out.println(person31);
    }

}
