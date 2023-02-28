package demo.iocoder.springboot.lab12.mybatisplusui.service.impl;

import demo.iocoder.springboot.lab12.mybatisplusui.entity.TaskExecute;
import demo.iocoder.springboot.lab12.mybatisplusui.mapper.TaskExecuteMapper;
import demo.iocoder.springboot.lab12.mybatisplusui.service.TaskexecuteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 计划任务执行表 服务实现类
 * </p>
 *
 * @author jaquez
 * @since 2023-02-28
 */
@Service
public class TaskExecuteServiceImpl extends ServiceImpl<TaskExecuteMapper, TaskExecute> implements TaskexecuteService {

}
