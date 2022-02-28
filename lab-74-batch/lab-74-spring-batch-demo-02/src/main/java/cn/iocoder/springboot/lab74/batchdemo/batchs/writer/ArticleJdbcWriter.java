package cn.iocoder.springboot.lab74.batchdemo.batchs.writer;

import cn.iocoder.springboot.lab74.batchdemo.entity.ArticleDetail;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
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
}
