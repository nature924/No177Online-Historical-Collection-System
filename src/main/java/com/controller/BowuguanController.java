
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
 * 博物馆
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/bowuguan")
public class BowuguanController {
    private static final Logger logger = LoggerFactory.getLogger(BowuguanController.class);

    private static final String TABLE_NAME = "bowuguan";

    @Autowired
    private BowuguanService bowuguanService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private BowuguanOrderService bowuguanOrderService;
    //级联表非注册的service
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
        params.put("bowuguanDeleteStart",1);params.put("bowuguanDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = bowuguanService.queryPage(params);

        //字典表数据转换
        List<BowuguanView> list =(List<BowuguanView>)page.getList();
        for(BowuguanView c:list){
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
        BowuguanEntity bowuguan = bowuguanService.selectById(id);
        if(bowuguan !=null){
            //entity转view
            BowuguanView view = new BowuguanView();
            BeanUtils.copyProperties( bowuguan , view );//把实体数据重构到view中
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
    public R save(@RequestBody BowuguanEntity bowuguan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,bowuguan:{}",this.getClass().getName(),bowuguan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<BowuguanEntity> queryWrapper = new EntityWrapper<BowuguanEntity>()
            .eq("bowuguan_name", bowuguan.getBowuguanName())
            .eq("bowuguan_address", bowuguan.getBowuguanAddress())
            .eq("bowuguan_types", bowuguan.getBowuguanTypes())
            .eq("bowuguan_kucun_number", bowuguan.getBowuguanKucunNumber())
            .eq("bowuguan_delete", bowuguan.getBowuguanDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BowuguanEntity bowuguanEntity = bowuguanService.selectOne(queryWrapper);
        if(bowuguanEntity==null){
            bowuguan.setBowuguanDelete(1);
            bowuguan.setInsertTime(new Date());
            bowuguan.setCreateTime(new Date());
            bowuguanService.insert(bowuguan);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody BowuguanEntity bowuguan, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,bowuguan:{}",this.getClass().getName(),bowuguan.toString());
        BowuguanEntity oldBowuguanEntity = bowuguanService.selectById(bowuguan.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<BowuguanEntity> queryWrapper = new EntityWrapper<BowuguanEntity>()
            .notIn("id",bowuguan.getId())
            .andNew()
            .eq("bowuguan_name", bowuguan.getBowuguanName())
            .eq("bowuguan_address", bowuguan.getBowuguanAddress())
            .eq("bowuguan_types", bowuguan.getBowuguanTypes())
            .eq("bowuguan_kucun_number", bowuguan.getBowuguanKucunNumber())
            .eq("bowuguan_delete", bowuguan.getBowuguanDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BowuguanEntity bowuguanEntity = bowuguanService.selectOne(queryWrapper);
        if("".equals(bowuguan.getBowuguanPhoto()) || "null".equals(bowuguan.getBowuguanPhoto())){
                bowuguan.setBowuguanPhoto(null);
        }
        if(bowuguanEntity==null){
            bowuguanService.updateById(bowuguan);//根据id更新
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
        List<BowuguanEntity> oldBowuguanList =bowuguanService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<BowuguanEntity> list = new ArrayList<>();
        for(Integer id:ids){
            BowuguanEntity bowuguanEntity = new BowuguanEntity();
            bowuguanEntity.setId(id);
            bowuguanEntity.setBowuguanDelete(2);
            list.add(bowuguanEntity);
        }
        if(list != null && list.size() >0){
            bowuguanService.updateBatchById(list);
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
            List<BowuguanEntity> bowuguanList = new ArrayList<>();//上传的东西
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
                            BowuguanEntity bowuguanEntity = new BowuguanEntity();
//                            bowuguanEntity.setBowuguanName(data.get(0));                    //博物馆名称 要改的
//                            bowuguanEntity.setBowuguanUuidNumber(data.get(0));                    //博物馆编号 要改的
//                            bowuguanEntity.setBowuguanPhoto("");//详情和图片
//                            bowuguanEntity.setBowuguanAddress(data.get(0));                    //博物馆地点 要改的
//                            bowuguanEntity.setBowuguanTypes(Integer.valueOf(data.get(0)));   //博物馆分类 要改的
//                            bowuguanEntity.setBowuguanContent("");//详情和图片
//                            bowuguanEntity.setBowuguanKucunNumber(Integer.valueOf(data.get(0)));   //每天最大人数上限 要改的
//                            bowuguanEntity.setBowuguanDelete(1);//逻辑删除字段
//                            bowuguanEntity.setInsertTime(date);//时间
//                            bowuguanEntity.setCreateTime(date);//时间
                            bowuguanList.add(bowuguanEntity);


                            //把要查询是否重复的字段放入map中
                                //博物馆编号
                                if(seachFields.containsKey("bowuguanUuidNumber")){
                                    List<String> bowuguanUuidNumber = seachFields.get("bowuguanUuidNumber");
                                    bowuguanUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> bowuguanUuidNumber = new ArrayList<>();
                                    bowuguanUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("bowuguanUuidNumber",bowuguanUuidNumber);
                                }
                        }

                        //查询是否重复
                         //博物馆编号
                        List<BowuguanEntity> bowuguanEntities_bowuguanUuidNumber = bowuguanService.selectList(new EntityWrapper<BowuguanEntity>().in("bowuguan_uuid_number", seachFields.get("bowuguanUuidNumber")).eq("bowuguan_delete", 1));
                        if(bowuguanEntities_bowuguanUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(BowuguanEntity s:bowuguanEntities_bowuguanUuidNumber){
                                repeatFields.add(s.getBowuguanUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [博物馆编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        bowuguanService.insertBatch(bowuguanList);
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
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<BowuguanView> returnBowuguanViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        PageUtils pageUtils = bowuguanOrderService.queryPage(params1);
        List<BowuguanOrderView> orderViewsList =(List<BowuguanOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(BowuguanOrderView orderView:orderViewsList){
            Integer bowuguanTypes = orderView.getBowuguanTypes();
            if(typeMap.containsKey(bowuguanTypes)){
                typeMap.put(bowuguanTypes,typeMap.get(bowuguanTypes)+1);
            }else{
                typeMap.put(bowuguanTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("bowuguanTypes",type);
            PageUtils pageUtils1 = bowuguanService.queryPage(params2);
            List<BowuguanView> bowuguanViewList =(List<BowuguanView>)pageUtils1.getList();
            returnBowuguanViewList.addAll(bowuguanViewList);
            if(returnBowuguanViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = bowuguanService.queryPage(params);
        if(returnBowuguanViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnBowuguanViewList.size();//要添加的数量
            List<BowuguanView> bowuguanViewList =(List<BowuguanView>)page.getList();
            for(BowuguanView bowuguanView:bowuguanViewList){
                Boolean addFlag = true;
                for(BowuguanView returnBowuguanView:returnBowuguanViewList){
                    if(returnBowuguanView.getId().intValue() ==bowuguanView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnBowuguanViewList.add(bowuguanView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnBowuguanViewList = returnBowuguanViewList.subList(0, limit);
        }

        for(BowuguanView c:returnBowuguanViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnBowuguanViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = bowuguanService.queryPage(params);

        //字典表数据转换
        List<BowuguanView> list =(List<BowuguanView>)page.getList();
        for(BowuguanView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        BowuguanEntity bowuguan = bowuguanService.selectById(id);
            if(bowuguan !=null){


                //entity转view
                BowuguanView view = new BowuguanView();
                BeanUtils.copyProperties( bowuguan , view );//把实体数据重构到view中

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
    public R add(@RequestBody BowuguanEntity bowuguan, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,bowuguan:{}",this.getClass().getName(),bowuguan.toString());
        Wrapper<BowuguanEntity> queryWrapper = new EntityWrapper<BowuguanEntity>()
            .eq("bowuguan_name", bowuguan.getBowuguanName())
            .eq("bowuguan_uuid_number", bowuguan.getBowuguanUuidNumber())
            .eq("bowuguan_address", bowuguan.getBowuguanAddress())
            .eq("bowuguan_types", bowuguan.getBowuguanTypes())
            .eq("bowuguan_kucun_number", bowuguan.getBowuguanKucunNumber())
            .eq("bowuguan_delete", bowuguan.getBowuguanDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BowuguanEntity bowuguanEntity = bowuguanService.selectOne(queryWrapper);
        if(bowuguanEntity==null){
            bowuguan.setBowuguanDelete(1);
            bowuguan.setInsertTime(new Date());
            bowuguan.setCreateTime(new Date());
        bowuguanService.insert(bowuguan);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}
