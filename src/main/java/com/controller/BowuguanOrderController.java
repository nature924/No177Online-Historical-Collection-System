
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
 * 博物馆预约
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/bowuguanOrder")
public class BowuguanOrderController {
    private static final Logger logger = LoggerFactory.getLogger(BowuguanOrderController.class);

    private static final String TABLE_NAME = "bowuguanOrder";

    @Autowired
    private BowuguanOrderService bowuguanOrderService;


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
        CommonUtil.checkMap(params);
        PageUtils page = bowuguanOrderService.queryPage(params);

        //字典表数据转换
        List<BowuguanOrderView> list =(List<BowuguanOrderView>)page.getList();
        for(BowuguanOrderView c:list){
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
        BowuguanOrderEntity bowuguanOrder = bowuguanOrderService.selectById(id);
        if(bowuguanOrder !=null){
            //entity转view
            BowuguanOrderView view = new BowuguanOrderView();
            BeanUtils.copyProperties( bowuguanOrder , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(bowuguanOrder.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //级联表 博物馆
            //级联表
            BowuguanEntity bowuguan = bowuguanService.selectById(bowuguanOrder.getBowuguanId());
            if(bowuguan != null){
            BeanUtils.copyProperties( bowuguan , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
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
    public R save(@RequestBody BowuguanOrderEntity bowuguanOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,bowuguanOrder:{}",this.getClass().getName(),bowuguanOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            bowuguanOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        bowuguanOrder.setCreateTime(new Date());
        bowuguanOrder.setInsertTime(new Date());
        bowuguanOrderService.insert(bowuguanOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody BowuguanOrderEntity bowuguanOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,bowuguanOrder:{}",this.getClass().getName(),bowuguanOrder.toString());
        BowuguanOrderEntity oldBowuguanOrderEntity = bowuguanOrderService.selectById(bowuguanOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            bowuguanOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<BowuguanOrderEntity> queryWrapper = new EntityWrapper<BowuguanOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BowuguanOrderEntity bowuguanOrderEntity = bowuguanOrderService.selectOne(queryWrapper);
        if(bowuguanOrderEntity==null){
            bowuguanOrderService.updateById(bowuguanOrder);//根据id更新
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
        List<BowuguanOrderEntity> oldBowuguanOrderList =bowuguanOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        bowuguanOrderService.deleteBatchIds(Arrays.asList(ids));

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
            List<BowuguanOrderEntity> bowuguanOrderList = new ArrayList<>();//上传的东西
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
                            BowuguanOrderEntity bowuguanOrderEntity = new BowuguanOrderEntity();
//                            bowuguanOrderEntity.setBowuguanId(Integer.valueOf(data.get(0)));   //博物馆 要改的
//                            bowuguanOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            bowuguanOrderEntity.setBowuguanOrderTime(sdf.parse(data.get(0)));          //预约时间 要改的
//                            bowuguanOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //预约人数 要改的
//                            bowuguanOrderEntity.setBowuguanOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            bowuguanOrderEntity.setInsertTime(date);//时间
//                            bowuguanOrderEntity.setCreateTime(date);//时间
                            bowuguanOrderList.add(bowuguanOrderEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        bowuguanOrderService.insertBatch(bowuguanOrderList);
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
        PageUtils page = bowuguanOrderService.queryPage(params);

        //字典表数据转换
        List<BowuguanOrderView> list =(List<BowuguanOrderView>)page.getList();
        for(BowuguanOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        BowuguanOrderEntity bowuguanOrder = bowuguanOrderService.selectById(id);
            if(bowuguanOrder !=null){


                //entity转view
                BowuguanOrderView view = new BowuguanOrderView();
                BeanUtils.copyProperties( bowuguanOrder , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(bowuguanOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                    BowuguanEntity bowuguan = bowuguanService.selectById(bowuguanOrder.getBowuguanId());
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
    public R add(@RequestBody BowuguanOrderEntity bowuguanOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,bowuguanOrder:{}",this.getClass().getName(),bowuguanOrder.toString());
            BowuguanEntity bowuguanEntity = bowuguanService.selectById(bowuguanOrder.getBowuguanId());
            if(bowuguanEntity == null){
                return R.error(511,"查不到该博物馆");
            }
            // Double bowuguanNewMoney = bowuguanEntity.getBowuguanNewMoney();

//            if(false){
//            }
//            else if((bowuguanEntity.getBowuguanKucunNumber() -bowuguanOrder.getBuyNumber())<0){
//                return R.error(511,"预约人数不能超过每天最大人数上限");
//            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
//            if(bowuguanEntity.getBowuguanKucunNumber() == null)
//                return R.error(511,"超过每天最大预约人数");
//            double balance = bowuguanEntity.getBowuguanKucunNumber() - bowuguanOrder.getBuyNumber();//余额
//            if(balance<0)
//                return R.error(511,"余额不够支付");
            bowuguanOrder.setBowuguanOrderTypes(101); //设置订单状态为已预约
            bowuguanOrder.setYonghuId(userId); //设置订单支付人id
            bowuguanOrder.setInsertTime(new Date());
            bowuguanOrder.setCreateTime(new Date());
//                bowuguanEntity.setBowuguanKucunNumber( bowuguanEntity.getBowuguanKucunNumber() -bowuguanOrder.getBuyNumber());
                bowuguanService.updateById(bowuguanEntity);
                bowuguanOrderService.insert(bowuguanOrder);//新增订单


            return R.ok();
    }


    /**
    * 取消
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            BowuguanOrderEntity bowuguanOrder = bowuguanOrderService.selectById(id);
            Integer buyNumber = bowuguanOrder.getBuyNumber();
            Integer bowuguanId = bowuguanOrder.getBowuguanId();
            if(bowuguanId == null)
                return R.error(511,"查不到该博物馆");
            BowuguanEntity bowuguanEntity = bowuguanService.selectById(bowuguanId);

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            Double zhekou = 1.0;


            bowuguanEntity.setBowuguanKucunNumber(bowuguanEntity.getBowuguanKucunNumber() + buyNumber);



            bowuguanOrder.setBowuguanOrderTypes(102);//设置订单状态为已取消
            bowuguanOrderService.updateById(bowuguanOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            bowuguanService.updateById(bowuguanEntity);//更新订单中博物馆的信息

            return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        BowuguanOrderEntity  bowuguanOrderEntity = bowuguanOrderService.selectById(id);
        BowuguanEntity bowuguanEntity = bowuguanService.selectById(bowuguanOrderEntity.getBowuguanId());
        Integer bowuguanKucunNumber = bowuguanEntity.getBowuguanKucunNumber();
        Integer buyNumber = bowuguanOrderEntity.getBuyNumber();
        Map<String, Object> params=new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bowuguanOrderEntity.getBowuguanOrderTime());
        calendar.add(Calendar.DAY_OF_MONTH, +1);  // 今天的时间加一天
        Date time = calendar.getTime();

//        params.put("page","1");
//        params.put("limit","10000");
//        params.put("sort","id");
//        params.put("order","desc");
//        params.put("bowuguanOrderTimeStart",sdf.format(bowuguanOrderEntity.getBowuguanOrderTime()));
//        params.put("bowuguanOrderTimeEnd ",sdf.format(time));
//        params.put("bowuguanId",bowuguanOrderEntity.getBowuguanId());
//        params.put("bowuguanOrderTypes","103");
//        PageUtils page = bowuguanOrderService.queryPage(params);
//        List<BowuguanOrderView> list =(List<BowuguanOrderView>)page.getList();
//        if (list!=null){
//
//        }

        Integer sumNum = 0;

        Wrapper<BowuguanOrderEntity> queryWrapper = new EntityWrapper<BowuguanOrderEntity>()
                .ge("bowuguan_order_time", sdf.format(bowuguanOrderEntity.getBowuguanOrderTime()))
                .le("bowuguan_order_time", sdf.format(time))
                .eq("bowuguan_id", bowuguanOrderEntity.getBowuguanId())
                .eq("bowuguan_order_types", 103)
                ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        List<BowuguanOrderEntity> bowuguanOrdrEntities = bowuguanOrderService.selectList(queryWrapper);
        if(bowuguanOrdrEntities!=null){
            for (BowuguanOrderEntity aaa:bowuguanOrdrEntities){
                sumNum = sumNum + aaa.getBuyNumber();
            }
        }
        if(sumNum>=bowuguanKucunNumber){
            return R.error(511,"当天预约人数已达上限");
        }
        if(bowuguanKucunNumber-sumNum-buyNumber<0){
            return R.error(511,"你的预约人超出可预约上限");
        }
        bowuguanOrderEntity.setBowuguanOrderTypes(103);//设置订单状态为已审核
        boolean b =  bowuguanOrderService.updateById( bowuguanOrderEntity);
        return R.ok();
    }


    /**
     * 拒绝预约
     */
    @RequestMapping("/jujue")
    public R jujue(Integer id ){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        BowuguanOrderEntity  bowuguanOrderEntity = bowuguanOrderService.selectById(id);
        BowuguanEntity bowuguanEntity = bowuguanService.selectById(bowuguanOrderEntity.getBowuguanId());
        Integer bowuguanKucunNumber = bowuguanEntity.getBowuguanKucunNumber();
        Integer buyNumber = bowuguanOrderEntity.getBuyNumber();
        Map<String, Object> params=new HashMap<>();

        bowuguanOrderEntity.setBowuguanOrderTypes(107);//设置订单状态为已审核
        boolean b =  bowuguanOrderService.updateById( bowuguanOrderEntity);
        return R.ok();
    }

}
