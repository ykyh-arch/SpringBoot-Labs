package demo.iocoder.springboot.lab12.mybatisplusui.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 计划任务执行表
 * </p>
 *
 * @author jaquez
 * @since 2023-02-28
 */
@TableName("plan_task_execute")
public class TaskExecute implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 试点县编号
     */
    private Long areaId;

    /**
     * 试点任务编号
     */
    private Long planTaskId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 上报时间
     */
    private Date submitTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 已提交
     */
    private Boolean submitted;

    /**
     * 已删除
     */
    private Boolean deleted;

    /**
     * 填写人
     */
    private String submitter;

    /**
     * 填写人手机
     */
    private String submitterPhone;

    /**
     * 填写人电话
     */
    private String submitterTelPhone;

    /**
     * 计划任务执行说明
     */
    private String description;

    /**
     * 计划任务执行节点
     */
    private String node;

    /**
     * 计划任务执行上个节点
     */
    private String preNode;

    /**
     * 流程编号
     */
    private String defId;

    /**
     * 流程实例编号
     */
    private String instId;

    /**
     * 流程任务编号
     */
    private String taskId;

    /**
     * 流程主键
     */
    private String defKey;

    /**
     * 省级行政区划代码-省提交报告专用
     */
    private String r3ProvinceCode;

    /**
     * 省级行政区划名称-省提交报告专用
     */
    private String r3ProvinceName;

    /**
     * 创建者用户Id
     */
    private String creatorId;

    /**
     * 创建者角色
     */
    private String creatorRole;

    /**
     * 创建者组织
     */
    private String creatorGroup;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 历史code
     */
    private String historyCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getPlanTaskId() {
        return planTaskId;
    }

    public void setPlanTaskId(Long planTaskId) {
        this.planTaskId = planTaskId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getSubmitterPhone() {
        return submitterPhone;
    }

    public void setSubmitterPhone(String submitterPhone) {
        this.submitterPhone = submitterPhone;
    }

    public String getSubmitterTelPhone() {
        return submitterTelPhone;
    }

    public void setSubmitterTelPhone(String submitterTelPhone) {
        this.submitterTelPhone = submitterTelPhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getPreNode() {
        return preNode;
    }

    public void setPreNode(String preNode) {
        this.preNode = preNode;
    }

    public String getDefId() {
        return defId;
    }

    public void setDefId(String defId) {
        this.defId = defId;
    }

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDefKey() {
        return defKey;
    }

    public void setDefKey(String defKey) {
        this.defKey = defKey;
    }

    public String getr3ProvinceCode() {
        return r3ProvinceCode;
    }

    public void setr3ProvinceCode(String r3ProvinceCode) {
        this.r3ProvinceCode = r3ProvinceCode;
    }

    public String getr3ProvinceName() {
        return r3ProvinceName;
    }

    public void setr3ProvinceName(String r3ProvinceName) {
        this.r3ProvinceName = r3ProvinceName;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorRole() {
        return creatorRole;
    }

    public void setCreatorRole(String creatorRole) {
        this.creatorRole = creatorRole;
    }

    public String getCreatorGroup() {
        return creatorGroup;
    }

    public void setCreatorGroup(String creatorGroup) {
        this.creatorGroup = creatorGroup;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getHistoryCode() {
        return historyCode;
    }

    public void setHistoryCode(String historyCode) {
        this.historyCode = historyCode;
    }

    @Override
    public String toString() {
        return "TaskExecute{" +
        ", id = " + id +
        ", areaId = " + areaId +
        ", planTaskId = " + planTaskId +
        ", createTime = " + createTime +
        ", submitTime = " + submitTime +
        ", updateTime = " + updateTime +
        ", submitted = " + submitted +
        ", deleted = " + deleted +
        ", submitter = " + submitter +
        ", submitterPhone = " + submitterPhone +
        ", submitterTelPhone = " + submitterTelPhone +
        ", description = " + description +
        ", node = " + node +
        ", preNode = " + preNode +
        ", defId = " + defId +
        ", instId = " + instId +
        ", taskId = " + taskId +
        ", defKey = " + defKey +
        ", r3ProvinceCode = " + r3ProvinceCode +
        ", r3ProvinceName = " + r3ProvinceName +
        ", creatorId = " + creatorId +
        ", creatorRole = " + creatorRole +
        ", creatorGroup = " + creatorGroup +
        ", areaName = " + areaName +
        ", historyCode = " + historyCode +
        "}";
    }
}
