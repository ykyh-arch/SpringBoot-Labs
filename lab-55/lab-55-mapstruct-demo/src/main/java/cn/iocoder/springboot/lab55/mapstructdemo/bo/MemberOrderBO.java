package cn.iocoder.springboot.lab55.mapstructdemo.bo;

/**
 * 订单会员业务对象，合并映射
 *
 * @author jaquez
 * @date 2021/11/05 09:48
 **/
public class MemberOrderBO extends MemberBO{

    private String orderSn;
    private String receiverAddress;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    @Override
    public String toString() {
        return "MemberOrderBO{" +
                "orderSn='" + orderSn + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                "} " + super.toString();
    }
}
