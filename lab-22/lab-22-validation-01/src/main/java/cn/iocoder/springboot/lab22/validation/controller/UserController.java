package cn.iocoder.springboot.lab22.validation.controller;

import cn.iocoder.springboot.lab22.validation.dto.UserAddDTO;
import cn.iocoder.springboot.lab22.validation.dto.UserUpdateDTO;
import cn.iocoder.springboot.lab22.validation.dto.UserUpdateGenderDTO;
import cn.iocoder.springboot.lab22.validation.dto.UserUpdateStatusDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/users")
@Validated // 申明这个类的所有接口多需要参数校验，spring Validation 基于 aop 实现的，而该切面的拦截器，使用的是 MethodValidationInterceptor 。
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/get") // 在 MethodValidationInterceptor 拦截器中，校验到参数不正确，会抛出 ConstraintViolationException 异常。
    public void get(@RequestParam("id") @Min(value = 1L, message = "编号必须大于 0") Integer id) {
        logger.info("[get][id: {}]", id);
    }

    @PostMapping("/add")
    public void add(@Valid UserAddDTO addDTO) { // 对 POJO 对象进行参数校验，先通过 SpringMVC 的 DataBinder 机制，它会调用 DataBinder#validate(Object... validationHints) 方法，进行校验。在校验不通过时，会抛出 BindException 。在 SpringMVC 中，默认使用 DefaultHandlerExceptionResolver 处理异常。
        logger.info("[add][addDTO: {}]", addDTO);
    }

    @PostMapping("/update_gender")
    public void updateGender(@Valid UserUpdateGenderDTO updateGenderDTO) {
        logger.info("[updateGender][updateGenderDTO: {}]", updateGenderDTO);
    }

    @PostMapping("/update_status_true")
    public void updateStatusTrue(@Validated(UserUpdateStatusDTO.Group01.class) UserUpdateStatusDTO updateStatusDTO) {
        logger.info("[updateStatusTrue][updateStatusDTO: {}]", updateStatusDTO);
    }

    @PostMapping("/update_status_false")
    public void updateStatusFalse(@Validated(UserUpdateStatusDTO.Group02.class) UserUpdateStatusDTO updateStatusDTO) {
        logger.info("[updateStatusFalse][updateStatusDTO: {}]", updateStatusDTO);
    }

    @PostMapping("/update")
    public void update(@Valid UserUpdateDTO updateDTO) {
        logger.info("[update][updateDTO: {}]", updateDTO);
    }

}
