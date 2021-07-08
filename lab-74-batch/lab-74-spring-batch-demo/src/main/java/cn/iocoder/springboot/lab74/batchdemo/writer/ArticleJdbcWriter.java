package cn.iocoder.springboot.lab74.batchdemo.writer;

import cn.iocoder.springboot.lab74.batchdemo.entity.ArticleDetail;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 写操作，数据封装到写对象里面，然后进行插入操作
 */
@Component
public class ArticleJdbcWriter {

    private final DataSource dataSource;

    public ArticleJdbcWriter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JdbcBatchItemWriter<ArticleDetail> writer() {
        return new JdbcBatchItemWriterBuilder<ArticleDetail>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO article_detail (title, content, event_occurred_time, source, description) VALUES (:title, :content, :eventOccurredTime, :source, :description)")
                .dataSource(dataSource)
                .build();
    }
}
