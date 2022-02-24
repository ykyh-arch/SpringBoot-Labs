package cn.iocoder.springboot.lab74.batchdemo.batchs.writer;

import cn.iocoder.springboot.lab74.batchdemo.entity.ArticleDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;


/**
 * 自定义 writer 格式
 *
 * @author Jaquez
 * @date 2022/02/24 16:10
 */
@Slf4j
public class ArticleWriter implements ItemWriter<ArticleDetail> {

    @Override
    public void write(List<? extends ArticleDetail> list) throws Exception {
        log.info("list的大小等于job中设置的chunkSize， size = {}", list.size());
        // TODO 此处可输出数据，比如输出到消息队列
        list.forEach(article -> log.info("输出测试，title:{}", article.getTitle()));
    }
}