package com.entity.model;

import com.entity.BowuguanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 博物馆
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class BowuguanModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 博物馆名称
     */
    private String bowuguanName;


    /**
     * 博物馆编号
     */
    private String bowuguanUuidNumber;


    /**
     * 博物馆照片
     */
    private String bowuguanPhoto;


    /**
     * 博物馆地点
     */
    private String bowuguanAddress;


    /**
     * 博物馆分类
     */
    private Integer bowuguanTypes;


    /**
     * 博物馆介绍
     */
    private String bowuguanContent;


    /**
     * 每天最大人数上限
     */
    private Integer bowuguanKucunNumber;


    /**
     * 逻辑删除
     */
    private Integer bowuguanDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
