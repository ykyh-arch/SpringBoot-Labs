package cn.iocoder.springboot.lab04.rabbitmqdemo.message;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * CanalDemoMessage
 */
public class CanalDemoMessage implements Serializable {

    public static final String QUEUE = "QUEUE_CANAL_DEMO";

    public static final String EXCHANGE = "EXCHANGE_CANAL_DEMO";

    public static final String ROUTING_KEY = "ROUTING_KEY_CANAL_EXAMPLE";

    /**
     * TiCDC 生成对应的编号
     */
    private Integer id;

    /**
     * 数据项
     */
    private Collection data;

    /**
     * 数据库
     */
    private String database;

    /**
     * 是否为 DDL
     */
    private Boolean isDdl;

    /**
     * TiCDC 生成对应的时间戳，长度 13
     */
    private Long es;

    /**
     * pkNames
     */
    private String pkNames;

    /**
     * mysqlType
     */
    private String mysqlType;

    /**
     * 仅当 type 为 UPDATE 时才有值，记录每一列的名字和 UPDATE 之前的数据值
     */
    private Collection old;

    /**
     * sql
     */
    private String sql;

    /**
     * sqlType
     */
    private String sqlType;

    /**
     * table
     */
    private String table;

    /**
     * ts
     */
    private Long ts;

    /**
     * type
     */
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collection getData() {
        return data;
    }

    public void setData(Collection data) {
        this.data = data;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Boolean getDdl() {
        return isDdl;
    }

    public void setDdl(Boolean ddl) {
        isDdl = ddl;
    }

    public Long getEs() {
        return es;
    }

    public void setEs(Long es) {
        this.es = es;
    }

    public String getPkNames() {
        return pkNames;
    }

    public void setPkNames(String pkNames) {
        this.pkNames = pkNames;
    }

    public String getMysqlType() {
        return mysqlType;
    }

    public void setMysqlType(String mysqlType) {
        this.mysqlType = mysqlType;
    }

    public Collection getOld() {
        return old;
    }

    public void setOld(Collection old) {
        this.old = old;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CanalDemoMessage{" +
                "id=" + id +
                ", data=" + data +
                ", database='" + database + '\'' +
                ", isDdl=" + isDdl +
                ", es=" + es +
                ", pkNames='" + pkNames + '\'' +
                ", mysqlType='" + mysqlType + '\'' +
                ", old=" + old +
                ", sql='" + sql + '\'' +
                ", sqlType='" + sqlType + '\'' +
                ", table='" + table + '\'' +
                ", ts=" + ts +
                ", type=" + type +
                '}';
    }
}
