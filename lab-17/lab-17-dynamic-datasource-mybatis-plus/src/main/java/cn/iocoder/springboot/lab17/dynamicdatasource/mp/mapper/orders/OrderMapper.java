package cn.iocoder.springboot.lab17.dynamicdatasource.mp.mapper.orders;

import cn.iocoder.springboot.lab17.dynamicdatasource.mp.dataobject.OrderDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper extends BaseMapper<OrderDO> {

    // OrderDO selectById(@Param("id") Integer id);

}
