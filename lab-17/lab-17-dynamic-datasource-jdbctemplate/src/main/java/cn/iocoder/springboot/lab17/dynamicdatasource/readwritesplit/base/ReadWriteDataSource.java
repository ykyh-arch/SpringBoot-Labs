package cn.iocoder.springboot.lab17.dynamicdatasource.readwritesplit.base;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * ReadWriteDataSource
 *
 * @author fw001
 * @date 2023/09/12 15:26
 **/
public class ReadWriteDataSource extends AbstractRoutingDataSource {
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DsTypeHolder.getDsType();
    }
}
