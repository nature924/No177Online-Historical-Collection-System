
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 展品
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zhanpin")
public class ZhanpinController {
    private static final Logger logger = LoggerFactory.getLogger(ZhanpinController.class);

    private static final String TABLE_NAME = "zhanpin";

    @Autowired
    private ZhanpinService zhanpinService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表非注册的service
    @Autowired
    private BowuguanService bowuguanService;
    //注册表service
    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("zhanpinDeleteStart",1);params.put("zhanpinDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = zhanpinService.queryPage(params);

        //字典表数据转换
        List<ZhanpinView> list =(List<ZhanpinView>)page.getList();
        for(ZhanpinView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZhanpinEntity zhanpin = zhanpinService.selectById(id);
        if(zhanpin !=null){
            //entity转view
            ZhanpinView view = new ZhanpinView();
            BeanUtils.copyProperties( zhanpin , view );//把实体数据重构到view中
            //级联表 博物馆
            //级联表
            BowuguanEntity bowuguan = bowuguanService.selectById(zhanpin.getBowuguanId());
            if(bowuguan != null){
            BeanUtils.copyProperties( bowuguan , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setBowuguanId(bowuguan.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ZhanpinEntity zhanpin, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zhanpin:{}",this.getClass().getName(),zhanpin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<ZhanpinEntity> queryWrapper = new EntityWrapper<ZhanpinEntity>()
            .eq("bowuguan_id", zhanpin.getBowuguanId())
            .eq("zhanpin_name", zhanpin.getZhanpinName())
            .eq("zhanpin_chuchu", zhanpin.getZhanpinChuchu())
            .eq("zhanpin_delete", zhanpin.getZhanpinDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhanpinEntity zhanpinEntity = zhanpinService.selectOne(queryWrapper);
        if(zhanpinEntity==null){
            zhanpin.setZhanpinDelete(1);
            zhanpin.setInsertTime(new Date());
            zhanpin.setCreateTime(new Date());
            zhanpinService.insert(zhanpin);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZhanpinEntity zhanpin, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,zhanpin:{}",this.getClass().getName(),zhanpin.toString());
        ZhanpinEntity oldZhanpinEntity = zhanpinService.selectById(zhanpin.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<ZhanpinEntity> queryWrapper = new EntityWrapper<ZhanpinEntity>()
            .notIn("id",zhanpin.getId())
            .andNew()
            .eq("bowuguan_id", zhanpin.getBowuguanId())
            .eq("zhanpin_name", zhanpin.getZhanpinName())
            .eq("zhanpin_chuchu", zhanpin.getZhanpinChuchu())
            .eq("zhanpin_delete", zhanpin.getZhanpinDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhanpinEntity zhanpinEntity = zhanpinService.selectOne(queryWrapper);
        if("".equals(zhanpin.getZhanpinPhoto()) || "null".equals(zhanpin.getZhanpinPhoto())){
                zhanpin.setZhanpinPhoto(null);
        }
        if(zhanpinEntity==null){
            zhanpinService.updateById(zhanpin);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ZhanpinEntity> oldZhanpinList =zhanpinService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<ZhanpinEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ZhanpinEntity zhanpinEntity = new ZhanpinEntity();
            zhanpinEntity.setId(id);
            zhanpinEntity.setZhanpinDelete(2);
            list.add(zhanpinEntity);
        }
        if(list != null && list.size() >0){
            zhanpinService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<ZhanpinEntity> zhanpinList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ZhanpinEntity zhanpinEntity = new ZhanpinEntity();
//                            zhanpinEntity.setBowuguanId(Integer.valueOf(data.get(0)));   //博物馆 要改的
//                            zhanpinEntity.setZhanpinName(data.get(0));                    //展品名称 要改的
//                            zhanpinEntity.setZhanpinUuidNumber(data.get(0));                    //展品编号 要改的
//                            zhanpinEntity.setZhanpinPhoto("");//详情和图片
//                            zhanpinEntity.setZhanpinChuchu(data.get(0));                    //展品出处 要改的
//                            zhanpinEntity.setZhanpinContent("");//详情和图片
//                            zhanpinEntity.setJieruTime(sdf.parse(data.get(0)));          //展品借入时间 要改的
//                            zhanpinEntity.setYanghuTime(sdf.parse(data.get(0)));          //展品养护时间 要改的
//                            zhanpinEntity.setZhanpinDelete(1);//逻辑删除字段
//                            zhanpinEntity.setInsertTime(date);//时间
//                            zhanpinEntity.setCreateTime(date);//时间
                            zhanpinList.add(zhanpinEntity);


                            //把要查询是否重复的字段放入map中
                                //展品编号
                                if(seachFields.containsKey("zhanpinUuidNumber")){
                                    List<String> zhanpinUuidNumber = seachFields.get("zhanpinUuidNumber");
                                    zhanpinUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> zhanpinUuidNumber = new ArrayList<>();
                                    zhanpinUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("zhanpinUuidNumber",zhanpinUuidNumber);
                                }
                        }

                        //查询是否重复
                         //展品编号
                        List<ZhanpinEntity> zhanpinEntities_zhanpinUuidNumber = zhanpinService.selectList(new EntityWrapper<ZhanpinEntity>().in("zhanpin_uuid_number", seachFields.get("zhanpinUuidNumber")).eq("zhanpin_delete", 1));
                        if(zhanpinEntities_zhanpinUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZhanpinEntity s:zhanpinEntities_zhanpinUuidNumber){
                                repeatFields.add(s.getZhanpinUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [展品编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        zhanpinService.insertBatch(zhanpinList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = zhanpinService.queryPage(params);

        //字典表数据转换
        List<ZhanpinView> list =(List<ZhanpinView>)page.getList();
        for(ZhanpinView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZhanpinEntity zhanpin = zhanpinService.selectById(id);
            if(zhanpin !=null){


                //entity转view
                ZhanpinView view = new ZhanpinView();
                BeanUtils.copyProperties( zhanpin , view );//把实体数据重构到view中

                //级联表
                    BowuguanEntity bowuguan = bowuguanService.selectById(zhanpin.getBowuguanId());
                if(bowuguan != null){
                    BeanUtils.copyProperties( bowuguan , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setBowuguanId(bowuguan.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody ZhanpinEntity zhanpin, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,zhanpin:{}",this.getClass().getName(),zhanpin.toString());
        Wrapper<ZhanpinEntity> queryWrapper = new EntityWrapper<ZhanpinEntity>()
            .eq("bowuguan_id", zhanpin.getBowuguanId())
            .eq("zhanpin_name", zhanpin.getZhanpinName())
            .eq("zhanpin_uuid_number", zhanpin.getZhanpinUuidNumber())
            .eq("zhanpin_chuchu", zhanpin.getZhanpinChuchu())
            .eq("zhanpin_delete", zhanpin.getZhanpinDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhanpinEntity zhanpinEntity = zhanpinService.selectOne(queryWrapper);
        if(zhanpinEntity==null){
            zhanpin.setZhanpinDelete(1);
            zhanpin.setInsertTime(new Date());
            zhanpin.setCreateTime(new Date());
        zhanpinService.insert(zhanpin);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}
