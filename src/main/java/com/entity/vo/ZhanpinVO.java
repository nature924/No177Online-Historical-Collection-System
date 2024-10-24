package com.entity.vo;

import com.entity.ZhanpinEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 展品
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("zhanpin")
public class ZhanpinVO implements Serializable {
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
     * 展品名称
     */

    @TableField(value = "zhanpin_name")
    private String zhanpinName;


    /**
     * 展品编号
     */

    @TableField(value = "zhanpin_uuid_number")
    private String zhanpinUuidNumber;


    /**
     * 展品照片
     */

    @TableField(value = "zhanpin_photo")
    private String zhanpinPhoto;


    /**
     * 展品出处
     */

    @TableField(value = "zhanpin_chuchu")
    private String zhanpinChuchu;


    /**
     * 展品介绍
     */

    @TableField(value = "zhanpin_content")
    private String zhanpinContent;


    /**
     * 展品借入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "jieru_time")
    private Date jieruTime;


    /**
     * 展品养护时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "yanghu_time")
    private Date yanghuTime;


    /**
     * 逻辑删除
     */

    @TableField(value = "zhanpin_delete")
    private Integer zhanpinDelete;


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
	 * 设置：展品名称
	 */
    public String getZhanpinName() {
        return zhanpinName;
    }


    /**
	 * 获取：展品名称
	 */

    public void setZhanpinName(String zhanpinName) {
        this.zhanpinName = zhanpinName;
    }
    /**
	 * 设置：展品编号
	 */
    public String getZhanpinUuidNumber() {
        return zhanpinUuidNumber;
    }


    /**
	 * 获取：展品编号
	 */

    public void setZhanpinUuidNumber(String zhanpinUuidNumber) {
        this.zhanpinUuidNumber = zhanpinUuidNumber;
    }
    /**
	 * 设置：展品照片
	 */
    public String getZhanpinPhoto() {
        return zhanpinPhoto;
    }


    /**
	 * 获取：展品照片
	 */

    public void setZhanpinPhoto(String zhanpinPhoto) {
        this.zhanpinPhoto = zhanpinPhoto;
    }
    /**
	 * 设置：展品出处
	 */
    public String getZhanpinChuchu() {
        return zhanpinChuchu;
    }


    /**
	 * 获取：展品出处
	 */

    public void setZhanpinChuchu(String zhanpinChuchu) {
        this.zhanpinChuchu = zhanpinChuchu;
    }
    /**
	 * 设置：展品介绍
	 */
    public String getZhanpinContent() {
        return zhanpinContent;
    }


    /**
	 * 获取：展品介绍
	 */

    public void setZhanpinContent(String zhanpinContent) {
        this.zhanpinContent = zhanpinContent;
    }
    /**
	 * 设置：展品借入时间
	 */
    public Date getJieruTime() {
        return jieruTime;
    }


    /**
	 * 获取：展品借入时间
	 */

    public void setJieruTime(Date jieruTime) {
        this.jieruTime = jieruTime;
    }
    /**
	 * 设置：展品养护时间
	 */
    public Date getYanghuTime() {
        return yanghuTime;
    }


    /**
	 * 获取：展品养护时间
	 */

    public void setYanghuTime(Date yanghuTime) {
        this.yanghuTime = yanghuTime;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getZhanpinDelete() {
        return zhanpinDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setZhanpinDelete(Integer zhanpinDelete) {
        this.zhanpinDelete = zhanpinDelete;
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
