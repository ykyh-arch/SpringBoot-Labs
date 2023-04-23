package cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.cache;


import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.entity.DataSource;

import java.util.concurrent.ConcurrentMap;

/**
 * 数据源缓存，由数据源定时任务去维护
 */
public interface DataSourceCacheOperator {

    DataSource getCacheData(Integer id);

    DataSource getCacheDataIfPresent(Integer id);

    void clearCacheData();

    ConcurrentMap<String, DataSource> getCacheDataMap();
}
