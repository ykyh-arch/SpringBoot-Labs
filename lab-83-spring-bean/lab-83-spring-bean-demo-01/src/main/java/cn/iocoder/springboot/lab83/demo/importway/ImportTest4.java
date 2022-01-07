package cn.iocoder.springboot.lab83.demo.importway;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * import 测试方式四：使用 @Import + DeferredImportSelector 组合方式
 *
 * @author jaquez
 * @date 2022/01/07 14:46
 **/
@Import(MyDeferredImportSelector.class)
public class ImportTest4 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportTest4.class);
        Person3 bean = applicationContext.getBean(Person3.class);
        System.out.println(bean);
    }

}
