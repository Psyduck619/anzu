package zucc.dorm316.anzu.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import zucc.dorm316.anzu.entity.CommentStatisticEntity;
import zucc.dorm316.anzu.entity.GoodsOrderEntity;
import zucc.dorm316.anzu.entity.GoodsStatisticsEntity;
import zucc.dorm316.anzu.service.TblCommentStatisticService;
import zucc.dorm316.anzu.service.TblGoodsOrderService;
import zucc.dorm316.anzu.service.TblGoodsStatisticService;

import java.util.ArrayList;
import java.util.List;


@Transactional
@RestController
@CrossOrigin
@RequestMapping(value="/comment_statistic")
public class TblCommentStatisticController {
    @Autowired
    TblCommentStatisticService tblCommentStatisticService;
    @Autowired
    TblGoodsStatisticService tblGoodsStatisticService;
    @Autowired
    TblGoodsOrderService tblGoodsOrderService;
//    @RequestMapping(value="/findById",method= RequestMethod.GET)
//    public JSONObject findById(@RequestParam(value = "id") int id){
//        CommentStatisticEntity commentStatisticEntity = tblCommentStatisticService.findById(id);
//        JSONObject result=new JSONObject();
//        if (commentStatisticEntity == null)
//        {
//            result.put("port","500");
//            result.put("msg","无该评价信息");
//            return result;
//        }
//        else{
//            result.put("port","200");
//            result.put("data",commentStatisticEntity);
//            return result;
//        }
//    }
//
    @RequestMapping(value="/findByGoodsId",method= RequestMethod.GET)
    public JSONObject findByGoodsId(@RequestParam(value = "goods_id") int goods_id){
        List<GoodsOrderEntity> goodsOrderEntityList = tblGoodsOrderService.findByGoodsId(goods_id);
        List<CommentStatisticEntity> commentStatisticEntity = new ArrayList<>();
        for (int i=0;i<goodsOrderEntityList.size();i++){
            CommentStatisticEntity cse = tblCommentStatisticService.findByOrderId(goodsOrderEntityList.get(i).getId());
            if (cse != null)
                commentStatisticEntity.add(cse);
        }
        JSONObject result=new JSONObject();
        if (commentStatisticEntity.size() == 0)
        {
            result.put("port","500");
            result.put("msg","无该商品评价信息");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",commentStatisticEntity);
            return result;
        }
    }
//
//    @RequestMapping(value="/deletebyid",method= RequestMethod.GET)
//    public JSONObject deleteGoodsStatisticById(@RequestParam(value = "id") int id)
//    {
//        JSONObject result=new JSONObject();
//        try {
//            tblCommentStatisticService.deleteById(id);
//            result.put("port","200");
//        }
//        catch (Exception e){
//            result.put("port","500");
//            result.put("msg","删除异常");
//        }
//        return result;
//    }

    @RequestMapping(value="/add",method= RequestMethod.POST)
    public JSONObject addComment(@RequestParam(value = "order_id")int order_id,
                                         @RequestParam(value = "content")String content,
                                         @RequestParam(value = "rate")int rate,
                                         @RequestParam(value = "desc_star")int desc_star,
                                         @RequestParam(value = "logistics_star")int logistics_star,
                                         @RequestParam(value = "attitude_star")int attitude_star)
    {

        JSONObject result=new JSONObject();
        int goods_id = tblGoodsOrderService.findById(order_id).getGoodsId();
        GoodsStatisticsEntity goodsStatisticsEntity = tblGoodsStatisticService.findByGoodsId(goods_id);
        CommentStatisticEntity commentStatisticEntity = tblCommentStatisticService.findByOrderId(order_id);
        try {
            if (rate == 0)
            {
                tblGoodsStatisticService.modifyGoodsStatistic(goods_id,goodsStatisticsEntity.getRateNum()+1,goodsStatisticsEntity.getGoodRate()+1,goodsStatisticsEntity.getMidRate(),goodsStatisticsEntity.getBadRate(),goodsStatisticsEntity.getDescStar()+desc_star,goodsStatisticsEntity.getLogisticsStar()+logistics_star,goodsStatisticsEntity.getAttitudeStar()+attitude_star,goodsStatisticsEntity.getId());
                tblCommentStatisticService.addCommentStatistic(order_id, content, rate, desc_star, logistics_star, attitude_star);
                result.put("port","200");
            }
            else if(rate == 1)
            {
                tblGoodsStatisticService.modifyGoodsStatistic(goods_id,goodsStatisticsEntity.getRateNum()+1,goodsStatisticsEntity.getGoodRate(),goodsStatisticsEntity.getMidRate()+1,goodsStatisticsEntity.getBadRate(),goodsStatisticsEntity.getDescStar()+desc_star,goodsStatisticsEntity.getLogisticsStar()+logistics_star,goodsStatisticsEntity.getAttitudeStar()+attitude_star,goodsStatisticsEntity.getId());
                tblCommentStatisticService.addCommentStatistic(order_id, content, rate, desc_star, logistics_star, attitude_star);
                result.put("port","200");
            }
            else if(rate == 2)
            {
                tblGoodsStatisticService.modifyGoodsStatistic(goods_id,goodsStatisticsEntity.getRateNum()+1,goodsStatisticsEntity.getGoodRate(),goodsStatisticsEntity.getMidRate(),goodsStatisticsEntity.getBadRate()+1,goodsStatisticsEntity.getDescStar()+desc_star,goodsStatisticsEntity.getLogisticsStar()+logistics_star,goodsStatisticsEntity.getAttitudeStar()+attitude_star,goodsStatisticsEntity.getId());
                tblCommentStatisticService.addCommentStatistic(order_id, content, rate, desc_star, logistics_star, attitude_star);
                result.put("port","200");
            }
            else{
                result.put("port","400");
                result.put("msg","非法数据");
            }
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","添加异常");//增加异常
            tblCommentStatisticService.deleteByOrderId(order_id);
            tblGoodsStatisticService.modifyGoodsStatistic(goodsStatisticsEntity.getGoodsId(),goodsStatisticsEntity.getRateNum(),goodsStatisticsEntity.getGoodRate(),goodsStatisticsEntity.getMidRate(),goodsStatisticsEntity.getBadRate(),goodsStatisticsEntity.getDescStar(),goodsStatisticsEntity.getLogisticsStar(),goodsStatisticsEntity.getAttitudeStar(),goodsStatisticsEntity.getId());
        }
        return result;
    }
//    public JSONObject modifyGoodsStatistics(@RequestParam(value = "goods_id")int goods_id,
//                                            @RequestParam(value = "rate")int rate,
//                                            @RequestParam(value = "desc_star")int desc_star,
//                                            @RequestParam(value = "logistics_star")int logistics_star,
//                                            @RequestParam(value = "attitude_star")int attitude_star)
//    {
//        JSONObject result=new JSONObject();
//        try {
//            GoodsStatisticsEntity goodsStatisticsEntity = tblGoodsStatisticService.findByGoodsId(goods_id);
//            if (rate == 0)
//            {
//                tblGoodsStatisticService.modifyGoodsStatistic(goods_id,goodsStatisticsEntity.getRateNum()+1,goodsStatisticsEntity.getGoodRate()+1,goodsStatisticsEntity.getMidRate(),goodsStatisticsEntity.getBadRate(),goodsStatisticsEntity.getDescStar()+desc_star,goodsStatisticsEntity.getLogisticsStar()+logistics_star,goodsStatisticsEntity.getAttitudeStar()+attitude_star,goodsStatisticsEntity.getId());
//                result.put("port","200");
//            }
//            else if(rate == 1)
//            {
//                tblGoodsStatisticService.modifyGoodsStatistic(goods_id,goodsStatisticsEntity.getRateNum()+1,goodsStatisticsEntity.getGoodRate(),goodsStatisticsEntity.getMidRate()+1,goodsStatisticsEntity.getBadRate(),goodsStatisticsEntity.getDescStar()+desc_star,goodsStatisticsEntity.getLogisticsStar()+logistics_star,goodsStatisticsEntity.getAttitudeStar()+attitude_star,goodsStatisticsEntity.getId());
//                result.put("port","200");
//            }
//            else if(rate == 2)
//            {
//                tblGoodsStatisticService.modifyGoodsStatistic(goods_id,goodsStatisticsEntity.getRateNum()+1,goodsStatisticsEntity.getGoodRate(),goodsStatisticsEntity.getMidRate(),goodsStatisticsEntity.getBadRate()+1,goodsStatisticsEntity.getDescStar()+desc_star,goodsStatisticsEntity.getLogisticsStar()+logistics_star,goodsStatisticsEntity.getAttitudeStar()+attitude_star,goodsStatisticsEntity.getId());
//                result.put("port","200");
//            }
//            else{
//                result.put("port","400");
//                result.put("msg","非法数据");
//            }
//        }
//        catch (Exception e){
//            result.put("port","500");
//            result.put("msg","修改异常");//增加异常
//        }
//        return result;
//    }
}
