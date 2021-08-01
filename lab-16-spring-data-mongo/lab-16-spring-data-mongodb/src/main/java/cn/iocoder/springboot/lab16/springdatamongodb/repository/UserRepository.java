package cn.iocoder.springboot.lab16.springdatamongodb.repository;

import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.UserDO;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 使用MongoRepository操作Mongo
 * @author Jaquez
 * @date 2021/07/31 19:49
 */
public interface UserRepository extends MongoRepository<UserDO, Integer> {
}
