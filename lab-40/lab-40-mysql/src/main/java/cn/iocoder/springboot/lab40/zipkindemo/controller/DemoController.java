package cn.iocoder.springboot.lab40.zipkindemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private JdbcTemplate template;

    @GetMapping("/mysql")
    public String echo() {
        this.selectById(4);
        return "mysql";
    }

    public Object selectById(Integer id) {
        return template.queryForObject("SELECT id, username, password FROM users WHERE id = ?",
                new BeanPropertyRowMapper<>(Object.class), // 结果转换成对应的对象。Object 理论来说是 UserDO.class ，这里偷懒了。
                id);
    }

}
