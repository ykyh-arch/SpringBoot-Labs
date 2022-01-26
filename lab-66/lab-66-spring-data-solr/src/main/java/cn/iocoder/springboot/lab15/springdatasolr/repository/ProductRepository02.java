package cn.iocoder.springboot.lab15.springdatasolr.repository;

import cn.iocoder.springboot.lab15.springdatasolr.dataobject.SolrProductDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 * 基于方法名查询
 *
 * @author Jaquez
 * @date 2022/01/26 14:54
 */
public interface ProductRepository02 extends SolrCrudRepository<SolrProductDO, Integer> {

    SolrProductDO findByName(String name);

    Page<SolrProductDO> findByNameLike(String name, Pageable pageable);

}
