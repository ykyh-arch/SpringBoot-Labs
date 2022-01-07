package cn.iocoder.springboot.lab83.demo.importway;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义导入选择器
 *
 * @author jaquez
 * @date 2022/01/07 14:10
 **/
public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        // 类的全限定名
        return new String[]{"cn.iocoder.springboot.lab83.demo.importway.Person3"};
    }
}
