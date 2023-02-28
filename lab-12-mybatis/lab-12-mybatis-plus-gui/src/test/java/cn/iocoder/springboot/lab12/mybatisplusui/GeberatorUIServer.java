package cn.iocoder.springboot.lab12.mybatisplusui;

import com.github.davidfantasy.mybatisplus.generatorui.GeneratorConfig;
import com.github.davidfantasy.mybatisplus.generatorui.MybatisPlusToolsApplication;
import com.github.davidfantasy.mybatisplus.generatorui.mbp.NameConverter;

/**
 * GeberatorUIServer 工具类启动类
 *
 * @author jaquez
 * @date 2023/02/27 11:14
 **/
public class GeberatorUIServer {

    public static void main(String[] args) {
        GeneratorConfig config = GeneratorConfig.builder().jdbcUrl("jdbc:postgresql://127.0.0.1:5432/lzgd")
                .userName("postgres")
                .password("123456")
                .driverClassName("org.postgresql.Driver")
                // 数据库 schema，POSTGRE_SQL,ORACLE,DB2 类型的数据库需要指定
                .schemaName("public")
                // 如果需要修改 entity 及其属性的命名规则，以及自定义各类生成文件的命名规则，可自定义一个 NameConverter 实例，覆盖相应的名称转换方法，详细可查看该接口的说明：
                .nameConverter(new NameConverter() {
                    /**
                     * 自定义 Service 类文件的名称规则
                     */
                    @Override
                    public String serviceNameConvert(String tableName) {
                        return this.entityNameConvert(tableName) + "Service";
                    }

                    /**
                     * 自定义 Controller 类文件的名称规则
                     */
                    @Override
                    public String controllerNameConvert(String tableName) {
                        return this.entityNameConvert(tableName) + "Controller";
                    }
                })
                // 所有生成的 java 文件的父包名，后续也可单独在界面上设置
                .basePackage("demo.iocoder.springboot.lab12.mybatisplusui")
                .port(8068)
                .build();
        MybatisPlusToolsApplication.run(config);
    }
}
