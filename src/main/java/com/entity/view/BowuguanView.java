package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.BowuguanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 博物馆
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("bowuguan")
public class BowuguanView extends BowuguanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 博物馆分类的值
	*/
	@ColumnInfo(comment="博物馆分类的字典表值",type="varchar(200)")
	private String bowuguanValue;




	public BowuguanView() {

	}

	public BowuguanView(BowuguanEntity bowuguanEntity) {
		try {
			BeanUtils.copyProperties(this, bowuguanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 博物馆分类的值
	*/
	public String getBowuguanValue() {
		return bowuguanValue;
	}
	/**
	* 设置： 博物馆分类的值
	*/
	public void setBowuguanValue(String bowuguanValue) {
		this.bowuguanValue = bowuguanValue;
	}




	@Override
	public String toString() {
		return "BowuguanView{" +
			", bowuguanValue=" + bowuguanValue +
			"} " + super.toString();
	}
}
