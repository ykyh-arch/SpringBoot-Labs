package cn.iocoder.springboot.lab16.springdatamongodb.repository;

import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.UserDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository02 extends MongoRepository<UserDO, Integer> {

    UserDO findByUsername(String username);

    // 对于分页操作，需要使用到 Pageable 参数，需要作为方法的最后一个参数。
    Page<UserDO> findByUsernameLike(String username, Pageable pageable);

}
