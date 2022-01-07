package cn.iocoder.springboot.lab83.demo.importway;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * import 测试方式二 ：使用 @Import + ImportSelector 组合方式
 *
 * @author jaquez
 * @date 2022/01/07 14:13
 **/
@Import(MyImportSelector.class)
public class ImportTest2 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportTest2.class);
        Person3 bean = applicationContext.getBean(Person3.class);
        System.out.println(bean);
    }
}
