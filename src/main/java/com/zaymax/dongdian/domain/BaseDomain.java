package com.zaymax.dongdian.domain;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by huiquan on 2017/2/27.
 */
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public class BaseDomain implements Serializable {
    /**
     * 主键，采用UUID
     */
    @Id
    @Column(name = "ID")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    /**
     * 记录增加时间
     */
    @Column(name = "CREATED_DATE")
    @CreatedDate
    private Date createdDate;

    /**
     * 创建用户
     */
    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "CREATED_BY_USER_ID")
    private SysUser createdBy;

    /**
     * 最后修改用户
     */
    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "LAST_MODIFIED_BY_USER_ID")
    private SysUser lastModifiedBy;

    /**
     * 记录最后修改时间
     */
    @Column(name = "LAST_MODIFIED_DATE")
    @LastModifiedDate
    private Date lastModifiedDate;

    @Column(name = "DELETED")
    private Boolean deleted = Boolean.FALSE;

    @Column(name = "DESCRIPTION")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SysUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(SysUser createdBy) {
        this.createdBy = createdBy;
    }

    public SysUser getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(SysUser lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
