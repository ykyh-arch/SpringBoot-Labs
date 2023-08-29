package cn.iocoder.springboot.lab73.eldemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * LessonModel
 *
 * @author fw001
 * @date 2023/08/29 18:12
 **/
@Component
public class LessonModel {

    @Value("你好,%{@name},%{@msg}")
    private String desc;

    @Override
    public String toString() {
        return "LessonModel{" +
                "desc='" + desc + '\'' +
                '}';
    }

}
