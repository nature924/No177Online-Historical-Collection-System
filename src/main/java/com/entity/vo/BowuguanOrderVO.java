package com.entity.vo;

import com.entity.BowuguanOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 博物馆预约
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("bowuguan_order")
public class BowuguanOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 博物馆
     */

    @TableField(value = "bowuguan_id")
    private Integer bowuguanId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "bowuguan_order_time")
    private Date bowuguanOrderTime;


    /**
     * 预约人数
     */

    @TableField(value = "buy_number")
    private Integer buyNumber;


    /**
     * 订单类型
     */

    @TableField(value = "bowuguan_order_types")
    private Integer bowuguanOrderTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：博物馆
	 */
    public Integer getBowuguanId() {
        return bowuguanId;
    }


    /**
	 * 获取：博物馆
	 */

    public void setBowuguanId(Integer bowuguanId) {
        this.bowuguanId = bowuguanId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：预约时间
	 */
    public Date getBowuguanOrderTime() {
        return bowuguanOrderTime;
    }


    /**
	 * 获取：预约时间
	 */

    public void setBowuguanOrderTime(Date bowuguanOrderTime) {
        this.bowuguanOrderTime = bowuguanOrderTime;
    }
    /**
	 * 设置：预约人数
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 获取：预约人数
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getBowuguanOrderTypes() {
        return bowuguanOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setBowuguanOrderTypes(Integer bowuguanOrderTypes) {
        this.bowuguanOrderTypes = bowuguanOrderTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
