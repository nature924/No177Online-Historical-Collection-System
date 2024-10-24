package com.dao;

import com.entity.ZhanpinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhanpinView;

/**
 * 展品 Dao 接口
 *
 * @author 
 */
public interface ZhanpinDao extends BaseMapper<ZhanpinEntity> {

   List<ZhanpinView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
