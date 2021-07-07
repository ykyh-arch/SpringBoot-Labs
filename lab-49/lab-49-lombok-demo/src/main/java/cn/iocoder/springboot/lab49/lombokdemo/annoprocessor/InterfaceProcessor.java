package cn.iocoder.springboot.lab49.lombokdemo.annoprocessor;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * 注解处理器
 *
 * @author jaquez
 * @date 2021/07/07 16:41
 **/
@SupportedAnnotationTypes(value = {"cn.iocoder.springboot.lab49.lombokdemo.annoprocessor.InterfaceAnnotation"})//表示这个processor类要对什么注解生效
@SupportedSourceVersion(value = SourceVersion.RELEASE_8)//表示支持的java版本
public class InterfaceProcessor extends AbstractProcessor {

    /**
     *
     * @author Jaquez
     * @date 2021/07/07 16:49
     * @param annotations 被要求的注解,就是@SupportedAnnotationTypes对应的注解
     * @param roundEnv 存放着当前和上一轮processing的环境信息
     * @return boolean
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "进入到InterfaceProcessor中了~~~");
        // 将带有InterfaceProcessor的类给找出来
        Set<? extends Element> clazz = roundEnv.getElementsAnnotatedWith(InterfaceAnnotation.class);
        clazz.forEach(item -> {
            // 生成一个 I + 类名的接口类
            String className = item.getSimpleName().toString();
            className = "I" + className.substring(0, 1) + className.substring(1);
            //JavaPoet是java用于生成java文件的一款第三方插件
            //类
            TypeSpec typeSpec = TypeSpec.interfaceBuilder(className).addModifiers(Modifier.PUBLIC).build();

            try {
                // 生成java文件
                JavaFile.builder("cn.iocoder.springboot.lab49.lombokdemo.annoprocessor.processor", typeSpec).build().writeTo(new File("./src/main/java/"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return true;
    }
}
