package zucc.dorm316.anzu.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import zucc.dorm316.anzu.entity.CartEntity;
import zucc.dorm316.anzu.service.TblCartService;

import java.util.List;

@Transactional
@RestController
@CrossOrigin
@RequestMapping(value="/cart")
public class TblCartController {
    @Autowired
    TblCartService tblCartService;

    @RequestMapping(value="/findById",method= RequestMethod.GET)
    public JSONObject findById(@RequestParam(value = "id") int id){
        CartEntity cartEntity = tblCartService.findById(id);
        JSONObject result=new JSONObject();
        if (cartEntity == null)
        {
            result.put("port","500");
            result.put("msg","无此购物车记录");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",cartEntity);
            return result;
        }
    }

    @RequestMapping(value="/findAllByUserId",method= RequestMethod.GET)
    public JSONObject findByUserId(@RequestParam(value = "user_id") int user_id){
        List<CartEntity> cartEntityList = tblCartService.findByUserId(user_id);
        JSONObject result=new JSONObject();
        if (cartEntityList.size()==0)
        {
            result.put("port","500");
            result.put("msg","该用户尚未在购物车中添加商品");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",cartEntityList);
            return result;
        }
    }

    @RequestMapping(value="/deletebyid",method= RequestMethod.GET)
    public JSONObject deleteCartByid(@RequestParam(value = "id") int id)
    {
        JSONObject result=new JSONObject();
        try {
            tblCartService.deleteCardGoodsById(id);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","删除异常");
        }
        return result;
    }

    @RequestMapping(value="/add",method= RequestMethod.POST)
    public JSONObject addCart(@RequestParam(value = "user_id")int user_id,
                                     @RequestParam(value = "goods_id")int goods_id,
                                     @RequestParam(value = "goods_num")int goods_num)
    {
        JSONObject result=new JSONObject();
        try {
            tblCartService.addCartGoods(user_id, goods_id, goods_num);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","添加异常");//增加异常
        }
        return result;
    }

    @RequestMapping(value="/modify",method= RequestMethod.POST)
    public JSONObject modifyCart(@RequestParam(value = "goods_num")int goods_num,
                                 @RequestParam(value = "id")int id )
    {
        JSONObject result=new JSONObject();
        try {
            CartEntity cartEntity = tblCartService.findById(id);
            tblCartService.modifyGoodsNum(cartEntity.getUserId(),cartEntity.getGoodsId(),goods_num,id);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","修改异常");
        }
        return result;
    }
}
