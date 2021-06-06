package zucc.dorm316.anzu.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import zucc.dorm316.anzu.entity.AftersalesEntity;
import zucc.dorm316.anzu.service.TblAftersalesService;

import java.util.List;

@Transactional
@RestController
@CrossOrigin
@RequestMapping(value="/aftersale")
public class TblAftersalesController {
    @Autowired
    TblAftersalesService tblAftersalesService;

    @RequestMapping(value="/findByMerchantId",method= RequestMethod.GET)
    public JSONObject findByMerchantId(@RequestParam(value = "merchant_id") int merchant_id){
        List<AftersalesEntity> aftersalesEntityList = tblAftersalesService.findByMerchantId(merchant_id);
        JSONObject result=new JSONObject();
        if (aftersalesEntityList.size() == 0)
        {
            result.put("port","500");
            result.put("msg","无此商家记录");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",aftersalesEntityList);
            return result;
        }
    }

    @RequestMapping(value="/findByUserId",method= RequestMethod.GET)
    public JSONObject findByUserId(@RequestParam(value = "user_id") int user_id){
        List<AftersalesEntity> aftersalesEntityList = tblAftersalesService.findByUserId(user_id);
        JSONObject result=new JSONObject();
        if (aftersalesEntityList.size() == 0)
        {
            result.put("port","500");
            result.put("msg","无此用户记录");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",aftersalesEntityList);
            return result;
        }
    }

    @RequestMapping(value="/findByOrderId",method= RequestMethod.GET)
    public JSONObject findByOrderId(@RequestParam(value = "order_id") int order_id){
        AftersalesEntity aftersalesEntity = tblAftersalesService.findByOrderId(order_id);
        JSONObject result=new JSONObject();
        if(aftersalesEntity == null)
        {
            result.put("port", "500");
            result.put("msg", "无此订单售后记录");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",aftersalesEntity);
            return result;
        }
    }

    @RequestMapping(value="/deletebyid",method= RequestMethod.GET)
    public JSONObject deleteByid(@RequestParam(value = "id") int id)
    {
        JSONObject result=new JSONObject();
        try {
            tblAftersalesService.deleteById(id);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","删除异常");
        }
        return result;
    }

    @RequestMapping(value="/add",method= RequestMethod.POST)
    public JSONObject addAftersales(@RequestParam(value = "order_id") int user_id,
                                     @RequestParam(value = "goods_status")int goods_status,
                                     @RequestParam(value = "reason",required = false)String reason)
    {
        JSONObject result=new JSONObject();
        try {
            tblAftersalesService.addAftersales(user_id,  goods_status, reason, 0, null);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","添加异常");//增加异常
        }
        return result;
    }


    @RequestMapping(value="/modifyAftersales",method= RequestMethod.POST)
    public JSONObject modifyAftersales(@RequestParam(value = "id") int id,
                                    @RequestParam(value = "processing_status")int processing_status,
                                       @RequestParam(value = "feedback",required = false)String feedback)
    {
        JSONObject result=new JSONObject();
        try {
            AftersalesEntity aftersalesEntity = tblAftersalesService.findById(id);
            tblAftersalesService.modifyAftersales(aftersalesEntity.getOrderId(),aftersalesEntity.getGoodsStatus(),aftersalesEntity.getReason(),processing_status,feedback,id);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","修改异常");//增加异常
        }
        return result;
    }
}
