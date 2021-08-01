package cn.iocoder.springboot.lab16.springdatamongodb.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 存储类对应的主键信息
 * 参考：https://blog.csdn.net/try_try_try/article/details/80612666
 * @author jaquez
 * @date 2021/08/01 18:58
 **/
@Document(collection = "sequence_1")
public class SeqInfo {
    @Id
    private String id;//主键
    private Long seqId;//序列值
    private String collName;//集合名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    public String getCollName() {
        return collName;
    }

    public void setCollName(String collName) {
        this.collName = collName;
    }
}
