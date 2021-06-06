package zucc.dorm316.anzu.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import zucc.dorm316.anzu.entity.CommentStatisticEntity;
import zucc.dorm316.anzu.service.TblCommentStatisticService;


@Transactional
@RestController
@CrossOrigin
@RequestMapping(value="/comment_statistic")
public class TblCommentStatisticController {
    @Autowired
    TblCommentStatisticService tblCommentStatisticService;

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
//    @RequestMapping(value="/findByGoodsId",method= RequestMethod.GET)
//    public JSONObject findByGoodsId(@RequestParam(value = "order_id") int order_id){
//        CommentStatisticEntity commentStatisticEntity = tblCommentStatisticService.findByOrderId(order_id);
//        JSONObject result=new JSONObject();
//        if (commentStatisticEntity == null)
//        {
//            result.put("port","500");
//            result.put("msg","无该订单评价信息");
//            return result;
//        }
//        else{
//            result.put("port","200");
//            result.put("data",commentStatisticEntity);
//            return result;
//        }
//    }
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
    public JSONObject addGoodsStatistics(@RequestParam(value = "order_id")int order_id,
                                         @RequestParam(value = "content")String content,
                                         @RequestParam(value = "rate")int rate,
                                         @RequestParam(value = "des_star")int des_star,
                                         @RequestParam(value = "logistics_star")int logistics_star,
                                         @RequestParam(value = "attitude_star")int attitude_star)
    {

        JSONObject result=new JSONObject();
        try {
            tblCommentStatisticService.addCommentStatistic(order_id, content, rate, des_star, logistics_star, attitude_star);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","添加异常");//增加异常
        }
        return result;
    }

}
