package cn.iocoder.springboot.lab28.task.dao;

import cn.iocoder.springboot.lab28.task.entity.SysTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 数据持久层操作
 *
 * @author jaquez
 * @date 2021/07/08 15:59
 **/
@Repository
public class SysTaskDao {

    @Autowired
    private JdbcTemplate template;

    public List<SysTask> list() {
        List <Map<String, Object>> rows = template.queryForList("SELECT * FROM sys_task");
        List<SysTask> sysTaskList = new ArrayList<>();
        for (Map<String, Object> map:
            rows) {
            SysTask sysTask = new SysTask();
            sysTask.setId((Long)map.get("id"));
            sysTask.setJobName((String)map.get("job_name"));
            sysTask.setDescription((String)map.get("description"));
            sysTask.setCronExpression((String)map.get("cron_expression"));
            sysTask.setBeanClass((String)map.get("bean_class"));
            sysTask.setJobStatus((String)map.get("job_status"));
            sysTask.setJobGroup((String)map.get("job_group"));
            sysTask.setCreateUser((String)map.get("create_user"));
            sysTask.setCreateTime((Date) map.get("create_time"));
            sysTask.setUpdateUser((String)map.get("update_user"));
            sysTask.setUpdateTime((Date)map.get("update_time"));
            sysTaskList.add(sysTask);
        }
        return sysTaskList;

    }

}
