package cn.iocoder.springboot.lab29.asynctask.task;

import cn.iocoder.springboot.lab29.asynctask.threads.CommonThread;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 库存线程类
 *
 * @author jaquez
 * @date 2022/05/31 16:10
 **/
@Component
// @Scope("prototype")
public class StockTask extends CommonThread  {

    private Integer stockNumber; // 库存数

    public StockTask() {
    }

    public StockTask(Integer stockNumber) {
        this.stockNumber = stockNumber;
    }

    public Integer getOrderNumber() {
        return stockNumber;
    }

    public void setOrderNumber(Integer stockNumber) {
        this.stockNumber = stockNumber;
    }

    @Override
    public void run() {

        // 业务操作
        System.out.println("多线程已经处理库存业务系统，库存数：" + stockNumber);

    }

    @Override
    public String getTaskParam() {
        return JSON.toJSONString(this.stockNumber);
    }
}
