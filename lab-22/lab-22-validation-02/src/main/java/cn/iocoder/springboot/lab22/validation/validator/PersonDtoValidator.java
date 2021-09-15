package cn.iocoder.springboot.lab22.validation.validator;

import cn.iocoder.springboot.lab22.validation.dto.PersonDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * PersonDtoValidator 校验
 *
 * @author jaquez
 * @date 2021/09/15 15:27
 **/
public class PersonDtoValidator implements Validator {

    /**
     * This Validator validates just Person instances
     */
    @Override
    public boolean supports(Class clazz) {
        return PersonDto.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "name", "名称不能为空");
        PersonDto p = (PersonDto) obj;
        if (p.getAge() < 0) {
            e.rejectValue("age", "年龄不能小于1");
        } else if (p.getAge() > 110) {
            e.rejectValue("age", "年龄不能大于110");
        }
    }

}
