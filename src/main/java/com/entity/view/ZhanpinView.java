package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ZhanpinEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 展品
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("zhanpin")
public class ZhanpinView extends ZhanpinEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表

	//级联表 博物馆
		/**
		* 博物馆名称
		*/

		@ColumnInfo(comment="博物馆名称",type="varchar(200)")
		private String bowuguanName;
		/**
		* 博物馆编号
		*/

		@ColumnInfo(comment="博物馆编号",type="varchar(200)")
		private String bowuguanUuidNumber;
		/**
		* 博物馆照片
		*/

		@ColumnInfo(comment="博物馆照片",type="varchar(200)")
		private String bowuguanPhoto;
		/**
		* 博物馆地点
		*/

		@ColumnInfo(comment="博物馆地点",type="varchar(200)")
		private String bowuguanAddress;
		/**
		* 博物馆分类
		*/
		@ColumnInfo(comment="博物馆分类",type="int(11)")
		private Integer bowuguanTypes;
			/**
			* 博物馆分类的值
			*/
			@ColumnInfo(comment="博物馆分类的字典表值",type="varchar(200)")
			private String bowuguanValue;
		/**
		* 博物馆介绍
		*/

		@ColumnInfo(comment="博物馆介绍",type="text")
		private String bowuguanContent;
		/**
		* 每天最大人数上限
		*/

		@ColumnInfo(comment="每天最大人数上限",type="int(11)")
		private Integer bowuguanKucunNumber;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer bowuguanDelete;



	public ZhanpinView() {

	}

	public ZhanpinView(ZhanpinEntity zhanpinEntity) {
		try {
			BeanUtils.copyProperties(this, zhanpinEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	//级联表的get和set 博物馆

		/**
		* 获取： 博物馆名称
		*/
		public String getBowuguanName() {
			return bowuguanName;
		}
		/**
		* 设置： 博物馆名称
		*/
		public void setBowuguanName(String bowuguanName) {
			this.bowuguanName = bowuguanName;
		}

		/**
		* 获取： 博物馆编号
		*/
		public String getBowuguanUuidNumber() {
			return bowuguanUuidNumber;
		}
		/**
		* 设置： 博物馆编号
		*/
		public void setBowuguanUuidNumber(String bowuguanUuidNumber) {
			this.bowuguanUuidNumber = bowuguanUuidNumber;
		}

		/**
		* 获取： 博物馆照片
		*/
		public String getBowuguanPhoto() {
			return bowuguanPhoto;
		}
		/**
		* 设置： 博物馆照片
		*/
		public void setBowuguanPhoto(String bowuguanPhoto) {
			this.bowuguanPhoto = bowuguanPhoto;
		}

		/**
		* 获取： 博物馆地点
		*/
		public String getBowuguanAddress() {
			return bowuguanAddress;
		}
		/**
		* 设置： 博物馆地点
		*/
		public void setBowuguanAddress(String bowuguanAddress) {
			this.bowuguanAddress = bowuguanAddress;
		}
		/**
		* 获取： 博物馆分类
		*/
		public Integer getBowuguanTypes() {
			return bowuguanTypes;
		}
		/**
		* 设置： 博物馆分类
		*/
		public void setBowuguanTypes(Integer bowuguanTypes) {
			this.bowuguanTypes = bowuguanTypes;
		}


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

		/**
		* 获取： 博物馆介绍
		*/
		public String getBowuguanContent() {
			return bowuguanContent;
		}
		/**
		* 设置： 博物馆介绍
		*/
		public void setBowuguanContent(String bowuguanContent) {
			this.bowuguanContent = bowuguanContent;
		}

		/**
		* 获取： 每天最大人数上限
		*/
		public Integer getBowuguanKucunNumber() {
			return bowuguanKucunNumber;
		}
		/**
		* 设置： 每天最大人数上限
		*/
		public void setBowuguanKucunNumber(Integer bowuguanKucunNumber) {
			this.bowuguanKucunNumber = bowuguanKucunNumber;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getBowuguanDelete() {
			return bowuguanDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setBowuguanDelete(Integer bowuguanDelete) {
			this.bowuguanDelete = bowuguanDelete;
		}


	@Override
	public String toString() {
		return "ZhanpinView{" +
			", bowuguanName=" + bowuguanName +
			", bowuguanUuidNumber=" + bowuguanUuidNumber +
			", bowuguanPhoto=" + bowuguanPhoto +
			", bowuguanAddress=" + bowuguanAddress +
			", bowuguanContent=" + bowuguanContent +
			", bowuguanKucunNumber=" + bowuguanKucunNumber +
			", bowuguanDelete=" + bowuguanDelete +
			"} " + super.toString();
	}
}
