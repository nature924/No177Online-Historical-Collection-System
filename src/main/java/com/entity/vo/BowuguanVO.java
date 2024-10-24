package com.entity.vo;

import com.entity.BowuguanEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 博物馆
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("bowuguan")
public class BowuguanVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 博物馆名称
     */

    @TableField(value = "bowuguan_name")
    private String bowuguanName;


    /**
     * 博物馆编号
     */

    @TableField(value = "bowuguan_uuid_number")
    private String bowuguanUuidNumber;


    /**
     * 博物馆照片
     */

    @TableField(value = "bowuguan_photo")
    private String bowuguanPhoto;


    /**
     * 博物馆地点
     */

    @TableField(value = "bowuguan_address")
    private String bowuguanAddress;


    /**
     * 博物馆分类
     */

    @TableField(value = "bowuguan_types")
    private Integer bowuguanTypes;


    /**
     * 博物馆介绍
     */

    @TableField(value = "bowuguan_content")
    private String bowuguanContent;


    /**
     * 每天最大人数上限
     */

    @TableField(value = "bowuguan_kucun_number")
    private Integer bowuguanKucunNumber;


    /**
     * 逻辑删除
     */

    @TableField(value = "bowuguan_delete")
    private Integer bowuguanDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：博物馆名称
	 */
    public String getBowuguanName() {
        return bowuguanName;
    }


    /**
	 * 获取：博物馆名称
	 */

    public void setBowuguanName(String bowuguanName) {
        this.bowuguanName = bowuguanName;
    }
    /**
	 * 设置：博物馆编号
	 */
    public String getBowuguanUuidNumber() {
        return bowuguanUuidNumber;
    }


    /**
	 * 获取：博物馆编号
	 */

    public void setBowuguanUuidNumber(String bowuguanUuidNumber) {
        this.bowuguanUuidNumber = bowuguanUuidNumber;
    }
    /**
	 * 设置：博物馆照片
	 */
    public String getBowuguanPhoto() {
        return bowuguanPhoto;
    }


    /**
	 * 获取：博物馆照片
	 */

    public void setBowuguanPhoto(String bowuguanPhoto) {
        this.bowuguanPhoto = bowuguanPhoto;
    }
    /**
	 * 设置：博物馆地点
	 */
    public String getBowuguanAddress() {
        return bowuguanAddress;
    }


    /**
	 * 获取：博物馆地点
	 */

    public void setBowuguanAddress(String bowuguanAddress) {
        this.bowuguanAddress = bowuguanAddress;
    }
    /**
	 * 设置：博物馆分类
	 */
    public Integer getBowuguanTypes() {
        return bowuguanTypes;
    }


    /**
	 * 获取：博物馆分类
	 */

    public void setBowuguanTypes(Integer bowuguanTypes) {
        this.bowuguanTypes = bowuguanTypes;
    }
    /**
	 * 设置：博物馆介绍
	 */
    public String getBowuguanContent() {
        return bowuguanContent;
    }


    /**
	 * 获取：博物馆介绍
	 */

    public void setBowuguanContent(String bowuguanContent) {
        this.bowuguanContent = bowuguanContent;
    }
    /**
	 * 设置：每天最大人数上限
	 */
    public Integer getBowuguanKucunNumber() {
        return bowuguanKucunNumber;
    }


    /**
	 * 获取：每天最大人数上限
	 */

    public void setBowuguanKucunNumber(Integer bowuguanKucunNumber) {
        this.bowuguanKucunNumber = bowuguanKucunNumber;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getBowuguanDelete() {
        return bowuguanDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setBowuguanDelete(Integer bowuguanDelete) {
        this.bowuguanDelete = bowuguanDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
