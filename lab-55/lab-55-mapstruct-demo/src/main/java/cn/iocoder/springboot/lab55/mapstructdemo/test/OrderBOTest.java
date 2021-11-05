package cn.iocoder.springboot.lab55.mapstructdemo.test;

import cn.iocoder.springboot.lab55.mapstructdemo.bo.OrderBO;
import cn.iocoder.springboot.lab55.mapstructdemo.convert.OrderConvert;
import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.MemberDO;
import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.OrderDO;
import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.ProductDO;

import java.util.Arrays;

/**
 * 订单数据对象测试类
 *
 * @author jaquez
 * @date 2021/11/04 17:52
 **/
public class OrderBOTest {

    /**
     * 子对象映射测试类
     * @param args
     */
    public static void main(String[] args) {
        // 创建相关对象
        MemberDO memberDO = new MemberDO().setId(1L).setUsername("nicai")
                .setPassword("123456").setNickname("nicai")
                .setPhone("1822556987").setIcon("").setGender(0);

        ProductDO productDO = new ProductDO().setName("buzhidao");

        OrderDO orderDO = new OrderDO().setId(1L).setOrderSn("20211104").setReceiverAddress("在火星上")
                .setMemberDO(memberDO).setProductDOList(Arrays.asList(productDO));
        // 进行转换
        OrderBO orderBO = OrderConvert.INSTANCE.toBO(orderDO);
        System.out.println(orderBO);
    }
}
