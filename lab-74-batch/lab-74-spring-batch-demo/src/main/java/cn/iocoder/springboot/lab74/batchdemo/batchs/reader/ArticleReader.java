package cn.iocoder.springboot.lab74.batchdemo.batchs.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * 读数据流，与 ArticleJdbcReader 二选一即可
 *
 * @author jaquez
 * @date 2022/02/24 15:34
 **/
@Deprecated
// @Component
public class ArticleReader implements ItemReader<Object> {

    @Override
    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        // 将数据读取出来并返回到当前的 batch 中

        return null;
    }
}
