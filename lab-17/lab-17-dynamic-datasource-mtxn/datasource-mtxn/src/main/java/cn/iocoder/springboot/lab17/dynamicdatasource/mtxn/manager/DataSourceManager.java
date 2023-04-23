package cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.manager;

import javax.sql.DataSource;
import java.util.Collection;

/**
 *  DataSourceManager 提供对 DataSource 维护的基本操作，方便动态控制数据源
 */
public interface DataSourceManager {
    /**
     * 新增或更新数据源
     *
     * @param id         主键
     * @param dataSource 数据源对象
     */
    void put(Integer id, DataSource dataSource);

    /**
     * 根据主键获取数据源
     *
     * @param id 主键
     * @return 数据源对象
     */
    DataSource get(Integer id);

    /**
     * 是否存在数据源
     *
     * @param id 主键
     * @return boolean
     */
    Boolean hasDataSource(Integer id);

    /**
     * 移除数据源
     *
     * @param id 主键
     */
    void remove(Integer id);


    /**
     * 关闭数据源
     *
     * @param id 主键
     * @return boolean
     */
    void closeDataSource(Integer id);

    Collection<DataSource> all();

}
