package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 博物馆
 *
 * @author 
 * @email
 */
@TableName("bowuguan")
public class BowuguanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public BowuguanEntity() {

	}

	public BowuguanEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 博物馆名称
     */
    @ColumnInfo(comment="博物馆名称",type="varchar(200)")
    @TableField(value = "bowuguan_name")

    private String bowuguanName;


    /**
     * 博物馆编号
     */
    @ColumnInfo(comment="博物馆编号",type="varchar(200)")
    @TableField(value = "bowuguan_uuid_number")

    private String bowuguanUuidNumber;


    /**
     * 博物馆照片
     */
    @ColumnInfo(comment="博物馆照片",type="varchar(200)")
    @TableField(value = "bowuguan_photo")

    private String bowuguanPhoto;


    /**
     * 博物馆地点
     */
    @ColumnInfo(comment="博物馆地点",type="varchar(200)")
    @TableField(value = "bowuguan_address")

    private String bowuguanAddress;


    /**
     * 博物馆分类
     */
    @ColumnInfo(comment="博物馆分类",type="int(11)")
    @TableField(value = "bowuguan_types")

    private Integer bowuguanTypes;


    /**
     * 博物馆介绍
     */
    @ColumnInfo(comment="博物馆介绍",type="text")
    @TableField(value = "bowuguan_content")

    private String bowuguanContent;


    /**
     * 每天最大人数上限
     */
    @ColumnInfo(comment="每天最大人数上限",type="int(11)")
    @TableField(value = "bowuguan_kucun_number")

    private Integer bowuguanKucunNumber;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "bowuguan_delete")

    private Integer bowuguanDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：博物馆名称
	 */
    public String getBowuguanName() {
        return bowuguanName;
    }
    /**
	 * 设置：博物馆名称
	 */

    public void setBowuguanName(String bowuguanName) {
        this.bowuguanName = bowuguanName;
    }
    /**
	 * 获取：博物馆编号
	 */
    public String getBowuguanUuidNumber() {
        return bowuguanUuidNumber;
    }
    /**
	 * 设置：博物馆编号
	 */

    public void setBowuguanUuidNumber(String bowuguanUuidNumber) {
        this.bowuguanUuidNumber = bowuguanUuidNumber;
    }
    /**
	 * 获取：博物馆照片
	 */
    public String getBowuguanPhoto() {
        return bowuguanPhoto;
    }
    /**
	 * 设置：博物馆照片
	 */

    public void setBowuguanPhoto(String bowuguanPhoto) {
        this.bowuguanPhoto = bowuguanPhoto;
    }
    /**
	 * 获取：博物馆地点
	 */
    public String getBowuguanAddress() {
        return bowuguanAddress;
    }
    /**
	 * 设置：博物馆地点
	 */

    public void setBowuguanAddress(String bowuguanAddress) {
        this.bowuguanAddress = bowuguanAddress;
    }
    /**
	 * 获取：博物馆分类
	 */
    public Integer getBowuguanTypes() {
        return bowuguanTypes;
    }
    /**
	 * 设置：博物馆分类
	 */

    public void setBowuguanTypes(Integer bowuguanTypes) {
        this.bowuguanTypes = bowuguanTypes;
    }
    /**
	 * 获取：博物馆介绍
	 */
    public String getBowuguanContent() {
        return bowuguanContent;
    }
    /**
	 * 设置：博物馆介绍
	 */

    public void setBowuguanContent(String bowuguanContent) {
        this.bowuguanContent = bowuguanContent;
    }
    /**
	 * 获取：每天最大人数上限
	 */
    public Integer getBowuguanKucunNumber() {
        return bowuguanKucunNumber;
    }
    /**
	 * 设置：每天最大人数上限
	 */

    public void setBowuguanKucunNumber(Integer bowuguanKucunNumber) {
        this.bowuguanKucunNumber = bowuguanKucunNumber;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getBowuguanDelete() {
        return bowuguanDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setBowuguanDelete(Integer bowuguanDelete) {
        this.bowuguanDelete = bowuguanDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Bowuguan{" +
            ", id=" + id +
            ", bowuguanName=" + bowuguanName +
            ", bowuguanUuidNumber=" + bowuguanUuidNumber +
            ", bowuguanPhoto=" + bowuguanPhoto +
            ", bowuguanAddress=" + bowuguanAddress +
            ", bowuguanTypes=" + bowuguanTypes +
            ", bowuguanContent=" + bowuguanContent +
            ", bowuguanKucunNumber=" + bowuguanKucunNumber +
            ", bowuguanDelete=" + bowuguanDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
