package cn.iocoder.springboot.lab12.mybatis.mapper;

import cn.iocoder.springboot.lab12.mybatis.dataobject.UserDO;
import cn.iocoder.springboot.lab12.mybatis.mapper.provider.UserProvider;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.insert.render.BatchInsert;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper2 extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<UserDO>, CommonUpdateMapper {

//    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
//    @Options(useGeneratedKeys=true, keyProperty="records.id")
//    int insert(String insertStatementSQL,@Param("records") List<UserDO> records);
//
//    default int insert(BatchInsert<UserDO> insertStatement) {
//        return insert(insertStatement.getInsertStatementSQL(), insertStatement.getRecords());
//    }

}
