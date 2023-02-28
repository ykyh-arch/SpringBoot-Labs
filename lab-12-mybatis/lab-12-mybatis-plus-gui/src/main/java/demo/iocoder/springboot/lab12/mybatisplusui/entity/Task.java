package demo.iocoder.springboot.lab12.mybatisplusui.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 计划任务表
 * </p>
 *
 * @author jaquez
 * @since 2023-02-28
 */
@TableName("plan_task")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 计划任务类型（字典表：plan_task_type）
     */
    private String taskType;

    /**
     * 任务主题
     */
    private String subject;

    /**
     * 在线填报模板，默认为0，为0代表没有在线填报模板
     */
    private Long templateId;

    /**
     * 计划任务开始时间
     */
    private Date startDate;

    /**
     * 计划任务结束时间
     */
    private Date endDate;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 是否开放，默认true
     */
    private Boolean open;

    /**
     * 已发布，默认false
     */
    private Boolean published;

    /**
     * 发布时间
     */
    private Date publishedTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人Id
     */
    private String creatorId;

    /**
     * 创建人组织名称
     */
    private String creatorGroupName;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 已删除，默认false
     */
    private Boolean deleted;

    /**
     * 删除理由
     */
    private String deletedReason;

    /**
     * 取消发布理由
     */
    private String unpublishedReason;

    /**
     * 创建人组织代码
     */
    private String creatorGroupCode;

    /**
     * 已结束，默认false
     */
    private Boolean finished;

    /**
     * 调度类型
     */
    private String schedulingType;

    /**
     * 结束时间
     */
    private Date finishedTime;

    /**
     * 所属年份
     */
    private String ptYear;

    /**
     * 文件格式规范【pdf、word、png，多种以逗号区分】
     */
    private String fileNameSpecification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Date getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(Date publishedTime) {
        this.publishedTime = publishedTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorGroupName() {
        return creatorGroupName;
    }

    public void setCreatorGroupName(String creatorGroupName) {
        this.creatorGroupName = creatorGroupName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getDeletedReason() {
        return deletedReason;
    }

    public void setDeletedReason(String deletedReason) {
        this.deletedReason = deletedReason;
    }

    public String getUnpublishedReason() {
        return unpublishedReason;
    }

    public void setUnpublishedReason(String unpublishedReason) {
        this.unpublishedReason = unpublishedReason;
    }

    public String getCreatorGroupCode() {
        return creatorGroupCode;
    }

    public void setCreatorGroupCode(String creatorGroupCode) {
        this.creatorGroupCode = creatorGroupCode;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public String getSchedulingType() {
        return schedulingType;
    }

    public void setSchedulingType(String schedulingType) {
        this.schedulingType = schedulingType;
    }

    public Date getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(Date finishedTime) {
        this.finishedTime = finishedTime;
    }

    public String getPtYear() {
        return ptYear;
    }

    public void setPtYear(String ptYear) {
        this.ptYear = ptYear;
    }

    public String getFileNameSpecification() {
        return fileNameSpecification;
    }

    public void setFileNameSpecification(String fileNameSpecification) {
        this.fileNameSpecification = fileNameSpecification;
    }

    @Override
    public String toString() {
        return "Task{" +
        ", id = " + id +
        ", taskType = " + taskType +
        ", subject = " + subject +
        ", templateId = " + templateId +
        ", startDate = " + startDate +
        ", endDate = " + endDate +
        ", description = " + description +
        ", open = " + open +
        ", published = " + published +
        ", publishedTime = " + publishedTime +
        ", createTime = " + createTime +
        ", creator = " + creator +
        ", creatorId = " + creatorId +
        ", creatorGroupName = " + creatorGroupName +
        ", updateTime = " + updateTime +
        ", deleted = " + deleted +
        ", deletedReason = " + deletedReason +
        ", unpublishedReason = " + unpublishedReason +
        ", creatorGroupCode = " + creatorGroupCode +
        ", finished = " + finished +
        ", schedulingType = " + schedulingType +
        ", finishedTime = " + finishedTime +
        ", ptYear = " + ptYear +
        ", fileNameSpecification = " + fileNameSpecification +
        "}";
    }
}
