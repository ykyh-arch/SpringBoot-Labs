package cn.iocoder.springboot.lab74.batchdemo.batchs.processor;

import cn.iocoder.springboot.lab74.batchdemo.entity.Article;
import cn.iocoder.springboot.lab74.batchdemo.entity.ArticleDetail;
import cn.iocoder.springboot.lab74.batchdemo.mapper.ArticleDetailMapper;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * batch 处理器，数据的业务逻辑处理（数据 ETL 操作）
 *
 * @author Jaquez
 * @date 2022/02/24 16:02
 */
@Component
public class ArticleProcessor implements ItemProcessor<Article, ArticleDetail> {

    @Autowired
    private ArticleDetailMapper articleDetailMapper;

    // 防止重复插入到数据库
    private Set<Article> articleSets = new HashSet<Article>();

    private AtomicInteger index = new AtomicInteger();

    @Override
    public ArticleDetail process(Article data) throws Exception {
        ArticleDetail articleDetail = new ArticleDetail();
        BeanUtils.copyProperties(data, articleDetail);
        articleDetail.setArticleId(data.getId());
        articleDetail.setSource("weibo"+index.incrementAndGet());
        articleDetail.setDescription("这是一条来源于微博的新闻");
        if(articleSets.contains(data)){
            return null;
        }
        if(articleSets.isEmpty()){
            articleDetailMapper.delete(); // 启动时清空历史数据
        }
        articleSets.add(data);
        return articleDetail;
    }
}
