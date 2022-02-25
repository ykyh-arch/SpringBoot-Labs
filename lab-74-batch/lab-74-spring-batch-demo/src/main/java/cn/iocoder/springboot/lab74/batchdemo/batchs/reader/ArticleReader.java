package cn.iocoder.springboot.lab74.batchdemo.batchs.reader;

import cn.iocoder.springboot.lab74.batchdemo.entity.Article;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Iterator;

/**
 *
 * 读数据流，与 ArticleJdbcReader 二选一即可，参考自：https://blog.csdn.net/weixin_34160277/article/details/92237800
 *
 * @author jaquez
 * @date 2022/02/24 15:34
 **/
@Deprecated
// @Component
public class ArticleReader implements ItemReader<Article> {

    private final Iterator<Article> iterator;

    public ArticleReader(Iterator<Article> iterator) {
        this.iterator = iterator;
    }

    @Override
    public Article read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        // 将数据读取出来并返回到当前的 batch 中
        if (iterator.hasNext()) {
            return this.iterator.next();
        } else {
            return null;
        }
    }
}
