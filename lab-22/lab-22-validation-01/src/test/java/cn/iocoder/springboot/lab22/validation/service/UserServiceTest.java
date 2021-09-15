package cn.iocoder.springboot.lab22.validation.service;

import cn.iocoder.springboot.lab22.validation.Application;
import cn.iocoder.springboot.lab22.validation.dto.UserAddDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private Validator validator;

    @Test
    public void testGet() {
        // 报错：javax.validation.ConstraintViolationException: get.id: 编号必须大于 0
        userService.get(-1);
    }

    @Test
    public void testAdd() {
        // 报错：javax.validation.ConstraintViolationException: add.addDTO.username: 登陆账号不能为空, add.addDTO.password: 密码不能为空
        UserAddDTO addDTO = new UserAddDTO();
        userService.add(addDTO);
    }

    @Test
    public void testAdd01() {
        // 正常执行完成，因为没有走 AOP 代理
        UserAddDTO addDTO = new UserAddDTO();
        userService.add01(addDTO);
    }

    @Test
    public void testAdd02() {
        // 报错：java.lang.IllegalStateException: Cannot find current proxy: Set 'exposeProxy' property on Advised to 'true' to make it available.
        UserAddDTO addDTO = new UserAddDTO();
        userService.add02(addDTO);
    }

    @Test
    public void testValidator() {
        // 打印，查看 validator 的类型
        System.out.println(validator);

        // 创建 UserAddDTO 对象
        UserAddDTO addDTO = new UserAddDTO();
        // 校验
        Set<ConstraintViolation<UserAddDTO>> result = validator.validate(addDTO);
        // 打印校验结果
        for (ConstraintViolation<UserAddDTO> constraintViolation : result) {
            // 属性:消息
            System.out.println(constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage());
        }
    }

}
