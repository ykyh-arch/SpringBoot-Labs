package cn.iocoder.springboot.lab74.batchdemo.batchs.writer;

import cn.iocoder.springboot.lab74.batchdemo.entity.ArticleDetail;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


/**
 * 写操作，数据封装到写对象里面，然后进行插入操作，参考：https://blog.csdn.net/masson32/article/details/91347849
 *
 * @author Jaquez
 * @date 2022/02/24 16:05
 */
@Component
public class ArticleJdbcWriter {

    // 注入数据源
    private final DataSource dataSource;

    @Value("${mybatis.mapper-locations}")
    String mapperLocations;

    private static SqlSessionFactoryBean sessionFactory;

    public ArticleJdbcWriter(@Qualifier("dmDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JdbcBatchItemWriter<ArticleDetail> writer() {

        return new JdbcBatchItemWriterBuilder<ArticleDetail>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO article_detail (article_id, title, content, event_occurred_time, source, description) VALUES (:articleId, :title, :content, :eventOccurredTime, :source, :description)")
                .dataSource(dataSource)
                .build();
    }

    // Mybatis 整合方式，参考自：https://blog.csdn.net/D_19901719576/article/details/100559209;
    public MyBatisBatchItemWriter<ArticleDetail> writerByMybatis() throws Exception {

        return new MyBatisBatchItemWriterBuilder<ArticleDetail>()
                .sqlSessionTemplate(new SqlSessionTemplate(sqlSessionFactory(), ExecutorType.BATCH))
                .statementId("cn.iocoder.springboot.lab74.batchdemo.mapper.MybatisArticleDetailMapper.insert")
                .build();
    }

    /**
     * Mybatis SqlSessionFactory 工厂配置
     *
     * @author Jaquez
     * @date 2022/03/01 12:34
     * @return org.apache.ibatis.session.SqlSessionFactory
     */
    private SqlSessionFactory sqlSessionFactory() throws Exception {

        if(sessionFactory == null){
            sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            sessionFactory.setMapperLocations(resolver.getResources(mapperLocations));// 参考自：https://blog.csdn.net/universsky2015/article/details/83593318
            return sessionFactory.getObject();
        }
        return sessionFactory.getObject();

    }

}
