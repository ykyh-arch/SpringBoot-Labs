package cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.datasource.driver;


import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.entity.DataSource;

public interface DriverFactory {
    boolean usable(DataSource var1);

    DataSource autoFillDriverAttribute(DataSource var1);

    default int factoryOrder() {
        return 100;
    }
}
