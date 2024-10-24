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
 * 展品
 *
 * @author 
 * @email
 */
@TableName("zhanpin")
public class ZhanpinEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ZhanpinEntity() {

	}

	public ZhanpinEntity(T t) {
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
     * 博物馆
     */
    @ColumnInfo(comment="博物馆",type="int(11)")
    @TableField(value = "bowuguan_id")

    private Integer bowuguanId;


    /**
     * 展品名称
     */
    @ColumnInfo(comment="展品名称",type="varchar(200)")
    @TableField(value = "zhanpin_name")

    private String zhanpinName;


    /**
     * 展品编号
     */
    @ColumnInfo(comment="展品编号",type="varchar(200)")
    @TableField(value = "zhanpin_uuid_number")

    private String zhanpinUuidNumber;


    /**
     * 展品照片
     */
    @ColumnInfo(comment="展品照片",type="varchar(200)")
    @TableField(value = "zhanpin_photo")

    private String zhanpinPhoto;


    /**
     * 展品出处
     */
    @ColumnInfo(comment="展品出处",type="varchar(200)")
    @TableField(value = "zhanpin_chuchu")

    private String zhanpinChuchu;


    /**
     * 展品介绍
     */
    @ColumnInfo(comment="展品介绍",type="text")
    @TableField(value = "zhanpin_content")

    private String zhanpinContent;


    /**
     * 展品借入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="展品借入时间",type="timestamp")
    @TableField(value = "jieru_time")

    private Date jieruTime;


    /**
     * 展品养护时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="展品养护时间",type="timestamp")
    @TableField(value = "yanghu_time")

    private Date yanghuTime;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "zhanpin_delete")

    private Integer zhanpinDelete;


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
	 * 获取：博物馆
	 */
    public Integer getBowuguanId() {
        return bowuguanId;
    }
    /**
	 * 设置：博物馆
	 */

    public void setBowuguanId(Integer bowuguanId) {
        this.bowuguanId = bowuguanId;
    }
    /**
	 * 获取：展品名称
	 */
    public String getZhanpinName() {
        return zhanpinName;
    }
    /**
	 * 设置：展品名称
	 */

    public void setZhanpinName(String zhanpinName) {
        this.zhanpinName = zhanpinName;
    }
    /**
	 * 获取：展品编号
	 */
    public String getZhanpinUuidNumber() {
        return zhanpinUuidNumber;
    }
    /**
	 * 设置：展品编号
	 */

    public void setZhanpinUuidNumber(String zhanpinUuidNumber) {
        this.zhanpinUuidNumber = zhanpinUuidNumber;
    }
    /**
	 * 获取：展品照片
	 */
    public String getZhanpinPhoto() {
        return zhanpinPhoto;
    }
    /**
	 * 设置：展品照片
	 */

    public void setZhanpinPhoto(String zhanpinPhoto) {
        this.zhanpinPhoto = zhanpinPhoto;
    }
    /**
	 * 获取：展品出处
	 */
    public String getZhanpinChuchu() {
        return zhanpinChuchu;
    }
    /**
	 * 设置：展品出处
	 */

    public void setZhanpinChuchu(String zhanpinChuchu) {
        this.zhanpinChuchu = zhanpinChuchu;
    }
    /**
	 * 获取：展品介绍
	 */
    public String getZhanpinContent() {
        return zhanpinContent;
    }
    /**
	 * 设置：展品介绍
	 */

    public void setZhanpinContent(String zhanpinContent) {
        this.zhanpinContent = zhanpinContent;
    }
    /**
	 * 获取：展品借入时间
	 */
    public Date getJieruTime() {
        return jieruTime;
    }
    /**
	 * 设置：展品借入时间
	 */

    public void setJieruTime(Date jieruTime) {
        this.jieruTime = jieruTime;
    }
    /**
	 * 获取：展品养护时间
	 */
    public Date getYanghuTime() {
        return yanghuTime;
    }
    /**
	 * 设置：展品养护时间
	 */

    public void setYanghuTime(Date yanghuTime) {
        this.yanghuTime = yanghuTime;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getZhanpinDelete() {
        return zhanpinDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setZhanpinDelete(Integer zhanpinDelete) {
        this.zhanpinDelete = zhanpinDelete;
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
        return "Zhanpin{" +
            ", id=" + id +
            ", bowuguanId=" + bowuguanId +
            ", zhanpinName=" + zhanpinName +
            ", zhanpinUuidNumber=" + zhanpinUuidNumber +
            ", zhanpinPhoto=" + zhanpinPhoto +
            ", zhanpinChuchu=" + zhanpinChuchu +
            ", zhanpinContent=" + zhanpinContent +
            ", jieruTime=" + DateUtil.convertString(jieruTime,"yyyy-MM-dd") +
            ", yanghuTime=" + DateUtil.convertString(yanghuTime,"yyyy-MM-dd") +
            ", zhanpinDelete=" + zhanpinDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
