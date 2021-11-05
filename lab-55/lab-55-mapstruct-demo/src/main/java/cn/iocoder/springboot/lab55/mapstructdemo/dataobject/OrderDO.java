package cn.iocoder.springboot.lab55.mapstructdemo.dataobject;

import java.util.Date;
import java.util.List;

/**
 * 订单数据对象，订单嵌套有会员和商品对象
 *
 * @author jaquez
 * @date 2021/11/04 17:22
 **/
public class OrderDO {

    private Long id;
    private String orderSn;
    private Date createTime;
    private String receiverAddress;
    private MemberDO memberDO;
    private List<ProductDO> productDOList;

    public Long getId() {
        return id;
    }

    public OrderDO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public OrderDO setOrderSn(String orderSn) {
        this.orderSn = orderSn;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public OrderDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public OrderDO setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
        return this;
    }

    public MemberDO getMemberDO() {
        return memberDO;
    }

    public OrderDO setMemberDO(MemberDO memberDO) {
        this.memberDO = memberDO;
        return this;
    }

    public List<ProductDO> getProductDOList() {
        return productDOList;
    }

    public OrderDO setProductDOList(List<ProductDO> productDOList) {
        this.productDOList = productDOList;
        return this;
    }

    @Override
    public String toString() {
        return "OrderDO{" +
                "id=" + id +
                ", orderSn='" + orderSn + '\'' +
                ", createTime=" + createTime +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", memberDO=" + memberDO +
                ", productDOList=" + productDOList +
                '}';
    }
}
