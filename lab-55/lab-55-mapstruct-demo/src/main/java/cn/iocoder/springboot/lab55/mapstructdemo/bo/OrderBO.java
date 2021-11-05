package cn.iocoder.springboot.lab55.mapstructdemo.bo;

import java.util.Date;
import java.util.List;

/**
 * 订单业务对象
 *
 * @author jaquez
 * @date 2021/11/04 17:35
 **/
public class OrderBO {

    private Long id;
    private String orderSn;
    private Date createTime;
    private String receiverAddress;
    //子对象映射BO
    private MemberBO memberBO;
    //子对象数组映射BO
    private List<ProductBO> productBOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public MemberBO getMemberBO() {
        return memberBO;
    }

    public void setMemberBO(MemberBO memberBO) {
        this.memberBO = memberBO;
    }

    public List<ProductBO> getProductBOList() {
        return productBOList;
    }

    public void setProductBOList(List<ProductBO> productBOList) {
        this.productBOList = productBOList;
    }

    @Override
    public String toString() {
        return "OrderBO{" +
                "id=" + id +
                ", orderSn='" + orderSn + '\'' +
                ", createTime=" + createTime +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", memberBO=" + memberBO +
                ", productBOList=" + productBOList +
                '}';
    }
}
