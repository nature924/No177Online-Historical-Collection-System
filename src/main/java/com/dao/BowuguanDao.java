package com.dao;

import com.entity.BowuguanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.BowuguanView;

/**
 * 博物馆 Dao 接口
 *
 * @author 
 */
public interface BowuguanDao extends BaseMapper<BowuguanEntity> {

   List<BowuguanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
