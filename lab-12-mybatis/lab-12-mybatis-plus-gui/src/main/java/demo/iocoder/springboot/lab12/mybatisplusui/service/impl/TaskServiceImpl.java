package demo.iocoder.springboot.lab12.mybatisplusui.service.impl;

import demo.iocoder.springboot.lab12.mybatisplusui.entity.Task;
import demo.iocoder.springboot.lab12.mybatisplusui.mapper.TaskMapper;
import demo.iocoder.springboot.lab12.mybatisplusui.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 计划任务表 服务实现类
 * </p>
 *
 * @author jaquez
 * @since 2023-02-28
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

}
