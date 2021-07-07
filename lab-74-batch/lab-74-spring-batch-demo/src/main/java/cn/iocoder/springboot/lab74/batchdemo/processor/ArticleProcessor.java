package cn.iocoder.springboot.lab74.batchdemo.processor;

import cn.iocoder.springboot.lab74.batchdemo.entity.Article;
import cn.iocoder.springboot.lab74.batchdemo.entity.ArticleDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * batch 处理器，数据的业务逻辑处理
 */
@Component
public class ArticleProcessor implements ItemProcessor<Article, ArticleDetail> {

    @Override
    public ArticleDetail process(Article data) throws Exception {
        ArticleDetail articleDetail = new ArticleDetail();
        BeanUtils.copyProperties(data, articleDetail);
        articleDetail.setSource("weibo");
        articleDetail.setDescription("这是一条来源于微博的新闻");
        return articleDetail;
    }
}
