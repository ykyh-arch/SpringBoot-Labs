package cn.iocoder.springboot.lab22.validation.controller;

import cn.iocoder.springboot.lab22.validation.dto.PersonDto;
import cn.iocoder.springboot.lab22.validation.validator.PersonDtoValidator;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制层
 *
 * @author jaquez
 * @date 2021/09/15 15:45
 **/
@RestController
@RequestMapping
public class PersonDtoController {

    // 为当前控制器注册一个属性编辑器或者其他，当前是添加了一个数据校验器
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new PersonDtoValidator());
    }

    @PostMapping("/person")
    @ResponseBody
    public Object person(@Validated PersonDto person) {
        return person;
    }

    @PostMapping("/person1")
    @ResponseBody
    public Object person1(@Validated PersonDto person,BindingResult result) {
        // 参数校验
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(e -> {
                System.out.println(e.getField() +"："+ e.getCode());
            });
            throw new IllegalArgumentException("参数输入错误");
        }
        return person;
    }

}
