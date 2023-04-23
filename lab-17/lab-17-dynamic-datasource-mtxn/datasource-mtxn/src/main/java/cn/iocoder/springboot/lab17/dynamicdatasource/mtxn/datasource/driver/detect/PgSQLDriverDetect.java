package cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.datasource.driver.detect;

import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.datasource.driver.DriverFactory;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.entity.DataSource;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.entity.enums.DataSourceType;
import org.apache.commons.lang3.StringUtils;
import org.postgresql.Driver;
import org.springframework.stereotype.Component;

@Component
public class PgSQLDriverDetect implements DriverFactory {
    @Override
    public boolean usable(DataSource dataSourceConfig) {
        if (StringUtils.isNotEmpty(dataSourceConfig.getJdbcUrl())) {
            return dataSourceConfig.getJdbcUrl().startsWith("jdbc:postgresql://");
        } else if (DataSourceType.POSTGRESQL.equals(dataSourceConfig.getType())) {
            return true;
        }
        return false;
    }

    @Override
    public DataSource autoFillDriverAttribute(DataSource dataSourceConfig) {
        if (!Driver.class.getName().equals(dataSourceConfig.getDriverClass())) {
            dataSourceConfig.setDriverClass(Driver.class.getName());
        }
        if (StringUtils.isEmpty(dataSourceConfig.getTestSql())) {
            dataSourceConfig.setTestSql("select version();");
        }

        return dataSourceConfig;
    }

    @Override
    public int factoryOrder() {
        return Integer.valueOf(DataSourceType.MYSQL.getValue());
    }
}
