package cn.iocoder.springboot.lab74.batchdemo.batchs.processor;

import cn.iocoder.springboot.lab74.batchdemo.entity.Article;
import cn.iocoder.springboot.lab74.batchdemo.entity.ArticleDetail;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * batch 处理器，数据的业务逻辑处理（数据 ETL 操作）
 *
 * @author Jaquez
 * @date 2022/02/24 16:02
 */
@Component
public class ArticleProcessor implements ItemProcessor<Article, ArticleDetail> {

    // 防止重复插入到数据库
    private Set<Article> articleSets = new HashSet<Article>();

    @Override
    public ArticleDetail process(Article data) throws Exception {
        ArticleDetail articleDetail = new ArticleDetail();
        BeanUtils.copyProperties(data, articleDetail);
        articleDetail.setArticleId(data.getId());
        articleDetail.setSource("weibo");
        articleDetail.setDescription("这是一条来源于微博的新闻");
        if(articleSets.contains(data)){
            return null;
        }
        articleSets.add(data);
        return articleDetail;
    }
}
