package cn.iocoder.springboot.lab74.batchdemo.mapper;

import cn.iocoder.springboot.lab74.batchdemo.entity.ScheduleJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ScheduleJobMapper
 *
 * @author jaquez
 * @date 2022/02/27 16:40
 **/
@Mapper
@Repository
public interface ScheduleJobMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ScheduleJob record);

    int insertSelective(ScheduleJob record);

    ScheduleJob selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ScheduleJob record);

    int updateByPrimaryKey(ScheduleJob record);

    @Select("SELECT * FROM schedule_job WHERE job_status = #{jobStatus}")
    List<ScheduleJob> getRunningJob(@Param("jobStatus") String jobStatus);
}
