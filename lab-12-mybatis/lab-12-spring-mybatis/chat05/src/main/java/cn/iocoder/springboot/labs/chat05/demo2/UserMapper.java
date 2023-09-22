package cn.iocoder.springboot.labs.chat05.demo2;

public interface UserMapper {

    // 这里可以省略，xml中select关联查询是关联另一个xml里的select语句，即：namespace.id
    UserModel getById(int id);
}
