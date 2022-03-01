package cn.iocoder.springboot.lab74.batchdemo.mapper;

import cn.iocoder.springboot.lab74.batchdemo.entity.ArticleDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MybatisArticleDetailMapper {
    int insert(ArticleDetail record);

    int insertSelective(ArticleDetail record);
}