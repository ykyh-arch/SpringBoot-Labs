package cn.iocoder.springboot.lab17.dynamicdatasource.mp.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 库存 DO
 */
@TableName(value="stock",schema="public")
public class StockDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 库存数量
     */
    private Integer number;


    public Integer getId() {
        return id;
    }

    public StockDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getNumber() {
        return number;
    }

    public StockDO setNumber(Integer number) {
        this.number = number;
        return this;
    }

    @Override
    public String toString() {
        return "StuckDO{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }

}
