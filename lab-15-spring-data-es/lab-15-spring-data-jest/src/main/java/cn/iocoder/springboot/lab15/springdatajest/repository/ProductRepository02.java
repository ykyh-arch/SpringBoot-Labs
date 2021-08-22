package cn.iocoder.springboot.lab15.springdatajest.repository;

import cn.iocoder.springboot.lab15.springdatajest.dataobject.ESProductDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

// xxxRepository 已经封装了基本的CRUD方法
public interface ProductRepository02 extends ElasticsearchRepository<ESProductDO, Integer> {

    ESProductDO findByName(String name);

    // 对于分页操作，需要使用到 Pageable 参数，需要作为方法的最后一个参数。
    Page<ESProductDO> findByNameLike(String name, Pageable pageable);

}
