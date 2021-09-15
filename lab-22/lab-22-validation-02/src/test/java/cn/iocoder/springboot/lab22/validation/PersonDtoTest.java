package cn.iocoder.springboot.lab22.validation;

import cn.iocoder.springboot.lab22.validation.dto.PersonDto;
import cn.iocoder.springboot.lab22.validation.validator.PersonDtoValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PersonDtoTest 测试类
 *
 * @author jaquez
 * @date 2021/09/15 15:31
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PersonDtoTest {

    @Test
    public void testPersonDto() {
        // 创建一个校验器
        PersonDtoValidator personValidator = new PersonDtoValidator();
        // 创建对象
        PersonDto person = new PersonDto();
        person.setAge(1000);
        Map<String, Object> map = new HashMap<>();
        // 创建结果对象，可以理解成 <spring:bind/> 标签，可以使用<spring:bind/>标签来检查错误信息，当然你也可以自行处理错误。
        MapBindingResult result = new MapBindingResult(map, "person");
        // 进行校验并将错误信息封装到result
        personValidator.validate(person, result);
        // 打印错误信息
        List<FieldError> fieldErrors = result.getFieldErrors();
        fieldErrors.forEach(e -> {
            System.out.println(e.getField() +"："+ e.getCode());
        });
    }

}
