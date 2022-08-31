package cn.iocoder.springboot.lab85.demo.response;

import lombok.Data;

import java.util.List;

/**
 * @author jaquez
 * @date 2022/08/31 18:17
 **/
@Data
public class Page<T> {

    private List<T> records;

    private Integer size;

    public Page<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    public Page<T> setSize(Integer size) {
        this.size = size;
        return this;
    }
}
