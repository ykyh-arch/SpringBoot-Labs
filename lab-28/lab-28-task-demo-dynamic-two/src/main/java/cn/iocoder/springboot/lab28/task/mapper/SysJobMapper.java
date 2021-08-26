package cn.iocoder.springboot.lab28.task.mapper;

import cn.iocoder.springboot.lab28.task.entity.SysJob;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SysJobMapper {

    int deleteByPrimaryKey(Integer jobId);

    int insert(SysJob record);

    int insertSelective(SysJob record);

    SysJob selectByPrimaryKey(Integer jobId);

    int updateByPrimaryKeySelective(SysJob record);

    int updateByPrimaryKey(SysJob record);

    List<SysJob> getSysJobListByStatus(Integer jobStatus);
}