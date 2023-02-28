package demo.iocoder.springboot.lab12.mybatisplusui.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import demo.iocoder.springboot.lab12.mybatisplusui.service.TaskexecuteService;
import demo.iocoder.springboot.lab12.mybatisplusui.entity.TaskExecute;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 计划任务执行表 前端控制器
 * </p>
 *
 * @author jaquez
 * @since 2023-02-28
 */
@Controller
@RequestMapping("/task-execute")
public class TaskexecuteController {


    @Autowired
    private TaskexecuteService taskexecuteService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<TaskExecute>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<TaskExecute> aPage = taskexecuteService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskExecute> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(taskexecuteService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody TaskExecute params) {
        taskexecuteService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        taskexecuteService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> delete(@RequestBody TaskExecute params) {
        taskexecuteService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
