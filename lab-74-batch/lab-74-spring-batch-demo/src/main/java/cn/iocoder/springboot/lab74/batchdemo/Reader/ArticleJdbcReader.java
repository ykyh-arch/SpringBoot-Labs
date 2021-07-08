package cn.iocoder.springboot.lab74.batchdemo.Reader;

import cn.iocoder.springboot.lab74.batchdemo.entity.Article;
import cn.iocoder.springboot.lab74.batchdemo.jdbc.ArticleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 读数据流，或自定义一个类 implements ItemReader<Object> 接口，重写read() 方法
 * 读的来源可以是数据库，也可以是文件或队列，最终要封装成读的对象
 */
@Component
public class ArticleJdbcReader {

    //这里可以指定对应库的数据源，关于数据源设置参考lab-19
//    @Resource(name = "articlesDataSource")
    private final DataSource dataSource;

    public ArticleJdbcReader(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 普通读取模式
     * - MySQL会将所有的纪录读到内存中
     * - 数据量大的话内存占用会很高
     */
    public JdbcCursorItemReader<Article> getArticle(String executedTime) {
        String lastExecutedTime = "";
        String sql = StringUtils.join("SELECT * FROM article WHERE event_occurred_time >= '",
                lastExecutedTime, "' AND event_occurred_time < '", executedTime, "'");
        return new JdbcCursorItemReaderBuilder<Article>()
                .dataSource(dataSource)
                .sql(sql)
                .fetchSize(10)
                .name("getArticle")
                .beanRowMapper(Article.class)
                .build();
    }

    /**
     * 分页读取模式
     * - 只要分页合理配置，内存占用可控
     */
    public JdbcPagingItemReader<Article> getArticlePaging(String executedTime) {
        String lastExecutedTime = "";
        Map<String, Object> parameterValues = new HashMap<>(2);
        parameterValues.put("startTime", lastExecutedTime);
        parameterValues.put("stopTime", executedTime);
        return new JdbcPagingItemReaderBuilder<Article>()
                .dataSource(dataSource)
                .name("getArticlePaging")
                .fetchSize(10)
                .parameterValues(parameterValues)
                .pageSize(10)
                .rowMapper(new ArticleMapper())
                .queryProvider(articleProvider())
                .build();
    }

    private PagingQueryProvider articleProvider() {
        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("event_occurred_time", Order.ASCENDING);
        MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
        provider.setSelectClause("title, content, event_occurred_time");
        provider.setFromClause("article");
        provider.setWhereClause("event_occurred_time >= :startTime AND event_occurred_time < :stopTime");
        provider.setSortKeys(sortKeys);
        return provider;
    }

    /**
     * 读取csv示例，参考：https://blog.csdn.net/masson32/article/details/91347849
     */
    public FlatFileItemReader<Article> readCsv() {
        return new FlatFileItemReaderBuilder<Article>()
                .name("articleCsvReader")
                .resource(new ClassPathResource("article.csv"))
                // 默认分割符，
                .delimited()
                .names(new String[] { "title", "content", "eventOccurredTime" })
                //.linesToSkip(1) //是否跳过几行
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Article>() {
                    {
                        setTargetType(Article.class);
                    }
                }).build();
    }
}
