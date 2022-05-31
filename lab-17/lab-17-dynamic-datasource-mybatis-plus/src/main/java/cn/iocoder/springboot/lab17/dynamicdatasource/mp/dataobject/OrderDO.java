package cn.iocoder.springboot.lab17.dynamicdatasource.mp.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 订单 DO
 */
@TableName(value="order",schema="public")
public class OrderDO {

    /**
     * 订单编号
     */
    private Integer id;
    /**
     * 订单名称
     */
    private String name;

    /**
     * 库存id
     */
    private Integer stockId;

    public Integer getId() {
        return id;
    }

    public OrderDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderDO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getStockId() {
        return stockId;
    }

    public OrderDO setStockId(Integer stockId) {
        this.stockId = stockId;
        return this;
    }

    @Override
    public String toString() {
        return "OrderDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stockId=" + stockId +
                '}';
    }
}
