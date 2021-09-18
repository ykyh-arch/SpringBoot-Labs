package cn.iocoder.springboot.lab45.apollodemo.repository;

import cn.iocoder.springboot.lab45.apollodemo.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
}
