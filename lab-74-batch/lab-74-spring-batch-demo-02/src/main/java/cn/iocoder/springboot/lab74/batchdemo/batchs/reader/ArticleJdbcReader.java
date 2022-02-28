package cn.iocoder.springboot.lab74.batchdemo.batchs.reader;

import cn.hutool.core.date.DateUtil;
import cn.iocoder.springboot.lab74.batchdemo.entity.Article;
import cn.iocoder.springboot.lab74.batchdemo.mapper.ArticleMapper;
import org.quartz.JobExecutionContext;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.repository.dao.JobExecutionDao;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 读数据流，或自定义一个类 implements ItemReader<Object> 接口，重写 read() 方法，读的来源可以是数据库 DB，也可以是文件或队列 csv，最终要封装成读的对象。
 *
 * @author Jaquez
 * @date 2022/02/24 15:07
 */
@Component
@StepScope
public class ArticleJdbcReader {

    @Resource(name = "dmDataSource")
    private final DataSource dataSource;

    @Value("#{jobParameters['executedTime']}")
    String executedTime;

    public ArticleJdbcReader(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 普通读取模式
     * - 基于游标的方式，MySQL会将所有的纪录读到内存中，数据量大的话内存占用会很高
     */
    public JdbcCursorItemReader<Article> getArticle() {

        String sql = "SELECT * FROM article WHERE event_occurred_time >= '' AND event_occurred_time < ? ;";

        System.out.println("SQL:"+sql);
        return new JdbcCursorItemReaderBuilder<Article>()
                .dataSource(dataSource)
                .sql(sql)
                .queryArguments(new Object[]{executedTime}) // 传参方式
                .fetchSize(50)
                .name("getArticle")
                .beanRowMapper(Article.class)
                .build();

    }

    /**
     * 分页读取模式
     * - 只要分页合理配置，内存占用可控
     */
    public JdbcPagingItemReader<Article> getArticlePaging() {
        String lastExecutedTime = "";
        Map<String, Object> parameterValues = new HashMap<>(2);
        parameterValues.put("startTime", lastExecutedTime);
        parameterValues.put("stopTime", executedTime);
        return new JdbcPagingItemReaderBuilder<Article>()
                .dataSource(dataSource)
                .name("getArticlePaging")
                .fetchSize(50)
                .parameterValues(parameterValues)
                .pageSize(10)
                .rowMapper(new ArticleMapper())
                .queryProvider(articleProvider())
                .build();
    }

    private PagingQueryProvider articleProvider() {
        Map<String, Order> sortKeys = new HashMap<>(1);
        // sortKeys.put("id", Order.ASCENDING); // 排序键会影响分页，参考：http://cn.voidcc.com/question/p-ktiommrr-n.html
        sortKeys.put("event_occurred_time", Order.ASCENDING);
        MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
        provider.setSelectClause("id, title, content, event_occurred_time");
        provider.setFromClause("article");
        provider.setWhereClause("event_occurred_time >= :startTime AND event_occurred_time < :stopTime");
        provider.setSortKeys(sortKeys);
        return provider;
    }

}
