package zucc.dorm316.anzu.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import zucc.dorm316.anzu.entity.GoodsStatisticsEntity;
import zucc.dorm316.anzu.service.TblGoodsStatisticService;

@Transactional
@RestController
@CrossOrigin
@RequestMapping(value="/goods_statistic")
public class TblGoodsStatisticController {
    @Autowired
    TblGoodsStatisticService tblGoodsStatisticService;

//    @RequestMapping(value="/findById",method= RequestMethod.GET)
//    public JSONObject findById(@RequestParam(value = "id") int id){
//        GoodsStatisticsEntity goodsStatisticsEntity = tblGoodsStatisticService.findById(id);
//        JSONObject result=new JSONObject();
//        if (goodsStatisticsEntity == null)
//        {
//            result.put("port","500");
//            result.put("msg","无该统计信息");
//            return result;
//        }
//        else{
//            result.put("port","200");
//            result.put("data",goodsStatisticsEntity);
//            return result;
//        }
//    }

    @RequestMapping(value="/findByGoodsId",method= RequestMethod.GET)
    public JSONObject findByGoodsId(@RequestParam(value = "goods_id") int goods_id){
        GoodsStatisticsEntity goodsStatisticsEntity = tblGoodsStatisticService.findByGoodsId(goods_id);
        JSONObject result=new JSONObject();
        if (goodsStatisticsEntity == null)
        {
            result.put("port","500");
            result.put("msg","无该商品统计信息");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",goodsStatisticsEntity);
            return result;
        }
    }

//    @RequestMapping(value="/deletebyid",method= RequestMethod.GET)
//    public JSONObject deleteGoodsStatisticById(@RequestParam(value = "id") int id)
//    {
//        JSONObject result=new JSONObject();
//        try {
//            tblGoodsStatisticService.deleteGoodsStatistic(id);
//            result.put("port","200");
//        }
//        catch (Exception e){
//            result.put("port","500");
//            result.put("msg","删除异常");
//        }
//        return result;
//    }

    @RequestMapping(value="/add",method= RequestMethod.POST)
    public JSONObject addGoodsStatistics(@RequestParam(value = "goods_id")int goods_id)
    {

        JSONObject result=new JSONObject();
        try {
            tblGoodsStatisticService.addGoodsStatistic(goods_id,0,0,0,0,0,0,0);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","添加异常");//增加异常
        }
        return result;
    }

    @RequestMapping(value="/modify",method= RequestMethod.POST)
    public JSONObject modifyGoodsStatistics(@RequestParam(value = "goods_id")int goods_id,
                                            @RequestParam(value = "rate")int rate,
                                            @RequestParam(value = "desc_star")int desc_star,
                                            @RequestParam(value = "logistics_star")int logistics_star,
                                            @RequestParam(value = "attitude_star")int attitude_star)
    {
        JSONObject result=new JSONObject();
        try {
            GoodsStatisticsEntity goodsStatisticsEntity = tblGoodsStatisticService.findByGoodsId(goods_id);
            if (rate == 0)
            {
                tblGoodsStatisticService.modifyGoodsStatistic(goods_id,goodsStatisticsEntity.getRateNum()+1,goodsStatisticsEntity.getGoodRate()+1,goodsStatisticsEntity.getMidRate(),goodsStatisticsEntity.getBadRate(),goodsStatisticsEntity.getDescStar()+desc_star,goodsStatisticsEntity.getLogisticsStar()+logistics_star,goodsStatisticsEntity.getAttitudeStar()+attitude_star,goodsStatisticsEntity.getId());
                result.put("port","200");
            }
            else if(rate == 1)
            {
                tblGoodsStatisticService.modifyGoodsStatistic(goods_id,goodsStatisticsEntity.getRateNum()+1,goodsStatisticsEntity.getGoodRate(),goodsStatisticsEntity.getMidRate()+1,goodsStatisticsEntity.getBadRate(),goodsStatisticsEntity.getDescStar()+desc_star,goodsStatisticsEntity.getLogisticsStar()+logistics_star,goodsStatisticsEntity.getAttitudeStar()+attitude_star,goodsStatisticsEntity.getId());
                result.put("port","200");
            }
            else if(rate == 2)
            {
                tblGoodsStatisticService.modifyGoodsStatistic(goods_id,goodsStatisticsEntity.getRateNum()+1,goodsStatisticsEntity.getGoodRate(),goodsStatisticsEntity.getMidRate(),goodsStatisticsEntity.getBadRate()+1,goodsStatisticsEntity.getDescStar()+desc_star,goodsStatisticsEntity.getLogisticsStar()+logistics_star,goodsStatisticsEntity.getAttitudeStar()+attitude_star,goodsStatisticsEntity.getId());
                result.put("port","200");
            }
            else{
                result.put("port","400");
                result.put("msg","非法数据");
            }
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","修改异常");//增加异常
        }
        return result;
    }
}
