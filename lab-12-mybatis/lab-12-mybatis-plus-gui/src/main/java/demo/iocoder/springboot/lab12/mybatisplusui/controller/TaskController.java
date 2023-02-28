package demo.iocoder.springboot.lab12.mybatisplusui.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import demo.iocoder.springboot.lab12.mybatisplusui.service.TaskService;
import demo.iocoder.springboot.lab12.mybatisplusui.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 计划任务表 前端控制器
 * </p>
 *
 * @author jaquez
 * @since 2023-02-28
 */
@Controller
@RequestMapping("/task")
public class TaskController {


    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<Task>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Task> aPage = taskService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Task> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(taskService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Task params) {
        taskService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        taskService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> delete(@RequestBody Task params) {
        taskService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
