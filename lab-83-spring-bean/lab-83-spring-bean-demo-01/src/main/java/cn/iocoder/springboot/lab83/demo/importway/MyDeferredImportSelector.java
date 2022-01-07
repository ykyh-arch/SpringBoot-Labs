package cn.iocoder.springboot.lab83.demo.importway;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * MyDeferredImportSelector 导入自定义延期导入选择器
 *
 * @author jaquez
 * @date 2022/01/07 14:41
 **/
public class MyDeferredImportSelector implements DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        // 也是直接将 Person 的全限定名放进去
        return new String[]{Person3.class.getName()};
    }
}
