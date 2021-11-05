package cn.iocoder.springboot.lab55.mapstructdemo.bo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品业务对象
 *
 * @author jaquez
 * @date 2021/11/04 17:39
 **/
public class ProductBO {

    //使用常量
    private Long id;
    //使用表达式生成属性
    private String productSn;
    private String name;
    private String subTitle;
    private String brandName;
    private BigDecimal price;
    //使用默认值
    private Integer count;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ProductBO{" +
                "id=" + id +
                ", productSn='" + productSn + '\'' +
                ", name='" + name + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", brandName='" + brandName + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", createTime=" + createTime +
                '}';
    }
}
