package cn.iocoder.springboot.lab55.mapstructdemo.dataobject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品数据对象
 *
 * @author jaquez
 * @date 2021/11/04 17:38
 **/
public class ProductDO {

    private Long id;
    private String productSn;
    private String name;
    private String subTitle;
    private String brandName;
    private BigDecimal price;
    private Integer count;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public ProductDO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProductSn() {
        return productSn;
    }

    public ProductDO setProductSn(String productSn) {
        this.productSn = productSn;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public ProductDO setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public String getBrandName() {
        return brandName;
    }

    public ProductDO setBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductDO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public ProductDO setCount(Integer count) {
        this.count = count;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ProductDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "ProductDO{" +
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
