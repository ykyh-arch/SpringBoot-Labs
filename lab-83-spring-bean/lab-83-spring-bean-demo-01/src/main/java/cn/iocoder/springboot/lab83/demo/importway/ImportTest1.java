package cn.iocoder.springboot.lab83.demo.importway;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * import 测试方式一：直接使用 @Import 导入一个类
 *
 * @author jaquez
 * @date 2022/01/07 14:05
 **/
@Import(Person3.class)
public class ImportTest1 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportTest1.class);
        Person3 person3 = applicationContext.getBean(Person3.class);
        System.out.println(person3);
    }
}
