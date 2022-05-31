package cn.iocoder.springboot.lab17.dynamicdatasource.mp.mapper.stocks;

import cn.iocoder.springboot.lab17.dynamicdatasource.mp.dataobject.StockDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMapper extends BaseMapper<StockDO> {

    // StockDO selectById(@Param("id") Integer id);

}
