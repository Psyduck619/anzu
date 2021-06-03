package zucc.dorm316.anzu.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import zucc.dorm316.anzu.entity.AftersalesEntity;
import zucc.dorm316.anzu.service.TblAftersalesService;

@Transactional
@RestController
@CrossOrigin
@RequestMapping(value="/aftersale")
public class TblAftersalesController {
    @Autowired
    TblAftersalesService tblAftersalesService;

    @RequestMapping(value="/findById",method= RequestMethod.GET)
    public JSONObject findById(@RequestParam(value = "id") int id){
         AftersalesEntity aftersalesEntity = tblAftersalesService.findById(id);
        JSONObject result=new JSONObject();
        if (aftersalesEntity == null)
        {
            result.put("port","500");
            result.put("msg","无此售后记录");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",aftersalesEntity);
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
    public JSONObject deleteCartByid(@RequestParam(value = "id") int id)
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
                                     @RequestParam(value = "refund_type") int refund_type,
                                     @RequestParam(value = "goods_status")int goods_status,
                                     @RequestParam(value = "reason",required = false)String reason,
                                     @RequestParam(value = "processing_status")int processing_status,
                                     @RequestParam(value = "feedback",required = false)String feedback)
    {
        JSONObject result=new JSONObject();
        try {
            tblAftersalesService.addAftersales(user_id, refund_type, goods_status, reason, processing_status, feedback);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","添加异常");//增加异常
        }
        return result;
    }

    @RequestMapping(value="/modifyProcessingStatus",method= RequestMethod.POST)
    public JSONObject addAftersales(@RequestParam(value = "id") int id,
                                    @RequestParam(value = "processing_status")int processing_status)
    {
        JSONObject result=new JSONObject();
        try {
            AftersalesEntity aftersalesEntity = tblAftersalesService.findById(id);
            tblAftersalesService.modifyAftersales(aftersalesEntity.getOrderId(),aftersalesEntity.getRefundType(),aftersalesEntity.getGoodsStatus(),aftersalesEntity.getReason(),processing_status,aftersalesEntity.getFeedback(),id);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","添加异常");//增加异常
        }
        return result;
    }
}
