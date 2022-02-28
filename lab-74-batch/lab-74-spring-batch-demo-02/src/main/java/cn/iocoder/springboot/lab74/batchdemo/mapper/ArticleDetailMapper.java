package cn.iocoder.springboot.lab74.batchdemo.mapper;

import cn.iocoder.springboot.lab74.batchdemo.datasource.DataSource;
import cn.iocoder.springboot.lab74.batchdemo.entity.ScheduleJob;
import cn.iocoder.springboot.lab74.batchdemo.enums.DataSourceType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ArticleDetailMapper
 *
 * @author jaquez
 * @date 2022/02/27 16:40
 **/
@Mapper
@Repository
public interface ArticleDetailMapper {

    @Delete("delete from article_detail")
    @DataSource(DataSourceType.DEMO)
    public void delete();
}
