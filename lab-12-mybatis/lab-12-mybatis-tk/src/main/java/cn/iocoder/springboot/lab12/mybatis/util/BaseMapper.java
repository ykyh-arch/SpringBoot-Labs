package cn.iocoder.springboot.lab12.mybatis.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

// 基础映射接口
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
