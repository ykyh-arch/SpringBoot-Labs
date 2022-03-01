package cn.iocoder.springboot.lab74.batchdemo.mapper;

import cn.iocoder.springboot.lab74.batchdemo.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MybatisArticleMapper {
    int insert(Article record);

    int insertSelective(Article record);

    List<Article> find(Map<String,Object> parameters);

    List<Article> selectList(Map<String,Object> parameters);
}