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
 * 博物馆预约
 *
 * @author 
 * @email
 */
@TableName("bowuguan_order")
public class BowuguanOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public BowuguanOrderEntity() {

	}

	public BowuguanOrderEntity(T t) {
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
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @ColumnInfo(comment="预约时间",type="date")
    @TableField(value = "bowuguan_order_time")

    private Date bowuguanOrderTime;


    /**
     * 预约人数
     */
    @ColumnInfo(comment="预约人数",type="int(11)")
    @TableField(value = "buy_number")

    private Integer buyNumber;


    /**
     * 订单类型
     */
    @ColumnInfo(comment="订单类型",type="int(11)")
    @TableField(value = "bowuguan_order_types")

    private Integer bowuguanOrderTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="订单创建时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间  listShow
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
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：预约时间
	 */
    public Date getBowuguanOrderTime() {
        return bowuguanOrderTime;
    }
    /**
	 * 设置：预约时间
	 */

    public void setBowuguanOrderTime(Date bowuguanOrderTime) {
        this.bowuguanOrderTime = bowuguanOrderTime;
    }
    /**
	 * 获取：预约人数
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }
    /**
	 * 设置：预约人数
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getBowuguanOrderTypes() {
        return bowuguanOrderTypes;
    }
    /**
	 * 设置：订单类型
	 */

    public void setBowuguanOrderTypes(Integer bowuguanOrderTypes) {
        this.bowuguanOrderTypes = bowuguanOrderTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "BowuguanOrder{" +
            ", id=" + id +
            ", bowuguanId=" + bowuguanId +
            ", yonghuId=" + yonghuId +
            ", bowuguanOrderTime=" + DateUtil.convertString(bowuguanOrderTime,"yyyy-MM-dd") +
            ", buyNumber=" + buyNumber +
            ", bowuguanOrderTypes=" + bowuguanOrderTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
