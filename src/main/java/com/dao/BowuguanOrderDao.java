package com.dao;

import com.entity.BowuguanOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.BowuguanOrderView;

/**
 * 博物馆预约 Dao 接口
 *
 * @author 
 */
public interface BowuguanOrderDao extends BaseMapper<BowuguanOrderEntity> {

   List<BowuguanOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
