package cn.iocoder.springboot.lab17.dynamicdatasource.mp.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "cn.iocoder.springboot.lab17.dynamicdatasource.mp.mapper.stocks", sqlSessionTemplateRef = "stocksSqlSessionTemplate")
public class MyBatisStocksConfig {

    /**
     * 创建 orders 数据源
     */
    @Bean(name = "stocksDataSource")
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.stocks")
    public DataSource dataSource() {
        return new AtomikosDataSourceBean();
    }

    /**
     * 创建 MyBatis SqlSessionFactory
     */
    @Bean(name = "stocksSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        // 这里如果使用 SqlSessionFactoryBean 将不能使用 mybatis-plus 自带的 baseMapper 中的方法
        // SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        // 设置 orders 数据源
        bean.setDataSource(this.dataSource());
        // 设置 entity 所在包
        bean.setTypeAliasesPackage("cn.iocoder.springboot.lab17.dynamicdatasource.mp.dataobject.stocks");
        // 设置驼峰
        MybatisConfiguration mc = new MybatisConfiguration();
        mc.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(mc);
        // 设置 config 路径
        // bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
        // 设置 mapper 路径
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/stocks/*.xml"));

        return bean.getObject();
    }

    /**
     * 创建 MyBatis SqlSessionTemplate
     */
    @Bean(name = "stocksSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(this.sqlSessionFactory());
    }

}
