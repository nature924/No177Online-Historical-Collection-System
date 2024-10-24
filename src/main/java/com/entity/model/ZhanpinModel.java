package com.entity.model;

import com.entity.ZhanpinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 展品
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ZhanpinModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 博物馆
     */
    private Integer bowuguanId;


    /**
     * 展品名称
     */
    private String zhanpinName;


    /**
     * 展品编号
     */
    private String zhanpinUuidNumber;


    /**
     * 展品照片
     */
    private String zhanpinPhoto;


    /**
     * 展品出处
     */
    private String zhanpinChuchu;


    /**
     * 展品介绍
     */
    private String zhanpinContent;


    /**
     * 展品借入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date jieruTime;


    /**
     * 展品养护时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date yanghuTime;


    /**
     * 逻辑删除
     */
    private Integer zhanpinDelete;


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
