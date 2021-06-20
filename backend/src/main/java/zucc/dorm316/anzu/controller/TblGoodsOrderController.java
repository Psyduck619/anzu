package zucc.dorm316.anzu.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import zucc.dorm316.anzu.entity.GoodsEntity;
import zucc.dorm316.anzu.entity.GoodsOrderEntity;
import zucc.dorm316.anzu.entity.MerchantEntity;
import zucc.dorm316.anzu.entity.UserEntity;
import zucc.dorm316.anzu.service.*;

import java.util.Date;
import java.util.List;

@Transactional
@RestController
@CrossOrigin
@RequestMapping(value="/goods_order")
public class TblGoodsOrderController {
    @Autowired
    TblGoodsOrderService tblGoodsOrderService;

    @Autowired
    TblGoodsService tblGoodsService;

    @Autowired
    TblGoodsStatisticService tblGoodsStatisticService;

    @Autowired
    TblUserService tblUserService;

    @Autowired
    TblMerchantService tblMerchantService;

    @RequestMapping(value="/findById",method= RequestMethod.GET)
    public JSONObject findById(@RequestParam(value = "id") int id){
        GoodsOrderEntity goodsOrderEntity = tblGoodsOrderService.findById(id);
        JSONObject result=new JSONObject();
        if (goodsOrderEntity == null)
        {
            result.put("port","500");
            result.put("msg","无此订单");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",goodsOrderEntity);
            return result;
        }
    }

    @RequestMapping(value="/findAllByUserId",method= RequestMethod.GET)
    public JSONObject findByUserId(@RequestParam(value = "user_id") int user_id){
        List<GoodsOrderEntity> goodsOrderEntityList = tblGoodsOrderService.findByUserId(user_id);
        JSONObject result=new JSONObject();
        if (goodsOrderEntityList.size()==0)
        {
            result.put("port","500");
            result.put("msg","该用户尚无订单");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",goodsOrderEntityList);
            return result;
        }
    }

    @RequestMapping(value="/findAllByGoodsId",method= RequestMethod.GET)
    public JSONObject findByGoodsId(@RequestParam(value = "goods_id") int goods_id){
        List<GoodsOrderEntity> goodsOrderEntityList = tblGoodsOrderService.findByGoodsId(goods_id);
        JSONObject result=new JSONObject();
        if (goodsOrderEntityList.size()==0)
        {
            result.put("port","500");
            result.put("msg","尚无此类商品订单");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",goodsOrderEntityList);
            return result;
        }
    }

    @RequestMapping(value="/findAllByMerchantId",method= RequestMethod.GET)
    public JSONObject findByMerchantId(@RequestParam(value = "merchant_id") int merchant_id){
        List<GoodsOrderEntity> goodsOrderEntityList = tblGoodsOrderService.findByMerchantId(merchant_id);
        JSONObject result=new JSONObject();
        if (goodsOrderEntityList.size()==0)
        {
            result.put("port","500");
            result.put("msg","该商家尚无订单");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",goodsOrderEntityList);
            return result;
        }
    }



    @RequestMapping(value="/add",method= RequestMethod.POST)
    public JSONObject addGoodsOrder(@RequestParam(value = "user_id")int user_id,
                               @RequestParam(value = "goods_id")int goods_id,
                               @RequestParam(value = "user_address")int user_address,
                               @RequestParam(value = "goods_num")int goods_num)
    {
        JSONObject result=new JSONObject();
        try {
            GoodsEntity goodsEntity = tblGoodsService.findById(goods_id);
            double deposit = goodsEntity.getDeposit()*goods_num;
            int merchant_id = goodsEntity.getMerchantId();
            double goods_total_price = goodsEntity.getPrice() * goods_num;
            if (goodsEntity.getStock() < goods_num){
                result.put("port","400");
                result.put("msg","库存不足");
            }
            else{
                tblGoodsOrderService.addGoodsOrder(user_id,goods_id,merchant_id,user_address,-1,1,
                        goods_num,goods_total_price,null,goodsEntity.getLeaseTime(),deposit);
                tblGoodsService.modifyGoods(goodsEntity.getGoodsName(),goodsEntity.getPrice(),goodsEntity.getCategoryId(),
                        goodsEntity.getMerchantId(),goodsEntity.getIntro(),goodsEntity.getStatus(), goodsEntity.getPicUrl(), goodsEntity.getMode()
                        ,goodsEntity.getDeposit(), goodsEntity.getStock()-goods_num,goodsEntity.getSales()+goods_num,
                        goodsEntity.getLeaseTime(),goods_id);
                result.put("port","200");
            }
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","添加异常");//增加异常
        }
        return result;
    }

    @RequestMapping(value="/modifyMerchantAddress",method= RequestMethod.POST)
    public JSONObject modifyGoodsOrderMerchantAddress(@RequestParam(value = "id")int id,
                                                      @RequestParam(value = "merchant_address_id")int merchant_address_id)
    {
        JSONObject result=new JSONObject();
        try {
            GoodsOrderEntity goodsOrderEntity = tblGoodsOrderService.findById(id);
            tblGoodsOrderService.modifyGoodsOrder(goodsOrderEntity.getUserId(),goodsOrderEntity.getGoodsId(),goodsOrderEntity.getMerchantId(),goodsOrderEntity.getUserAddressId(),merchant_address_id,goodsOrderEntity.getOrderStatus(),goodsOrderEntity.getGoodsNum(),goodsOrderEntity.getGoodsTotalPrice(),goodsOrderEntity.getReceivingTime(),goodsOrderEntity.getLeaseTime(),goodsOrderEntity.getDeposit(),id);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","修改异常");
        }
        return result;
    }

    @RequestMapping(value="/modifyOrderStatus",method= RequestMethod.POST)
    public JSONObject modifyGoodsOrderStatus(@RequestParam(value = "id")int id,
                                                      @RequestParam(value = "order_status")int order_status)
    {
        JSONObject result=new JSONObject();
        GoodsOrderEntity goodsOrderEntity = tblGoodsOrderService.findById(id);
        GoodsEntity goodsEntity = tblGoodsService.findById(goodsOrderEntity.getGoodsId());
        UserEntity userEntity = tblUserService.findByUserId(goodsOrderEntity.getUserId());
        MerchantEntity merchantEntity = tblMerchantService.findByMerchantId(goodsOrderEntity.getMerchantId());
        try {
            tblGoodsOrderService.modifyGoodsOrder(goodsOrderEntity.getUserId(),goodsOrderEntity.getGoodsId(),goodsOrderEntity.getMerchantId(),goodsOrderEntity.getUserAddressId(),goodsOrderEntity.getMerchantAddressId(),order_status,goodsOrderEntity.getGoodsNum(),goodsOrderEntity.getGoodsTotalPrice(),goodsOrderEntity.getReceivingTime(),goodsOrderEntity.getLeaseTime(),goodsOrderEntity.getDeposit(),id);
            if(order_status == 4)
            {
                double merchant_money = merchantEntity.getBalance() + goodsEntity.getDeposit() * goodsOrderEntity.getGoodsNum() - goodsOrderEntity.getDeposit() + goodsOrderEntity.getGoodsTotalPrice();
                double user_money = userEntity.getBalance()+goodsOrderEntity.getDeposit();
                tblMerchantService.modifyMerchant(merchantEntity.getId(),merchantEntity.getAccount(),merchantEntity.getPassword(),merchantEntity.getMerchantName(),merchant_money,merchantEntity.getAdminFlag());
                tblUserService.modifyUser(userEntity.getId(),userEntity.getAccount(),userEntity.getPassword(),userEntity.getUsername(),user_money);
            }
            else if(order_status == 7)
            {
                double merchant_money = merchantEntity.getBalance() + goodsEntity.getDeposit() * goodsOrderEntity.getGoodsNum() - goodsOrderEntity.getDeposit() + goodsOrderEntity.getGoodsTotalPrice()+ goodsOrderEntity.getDeposit()*0.9;
                double user_money = userEntity.getBalance()+ goodsOrderEntity.getDeposit()*0.1 ;
                tblGoodsOrderService.modifyGoodsOrder(goodsOrderEntity.getUserId(),goodsOrderEntity.getGoodsId(),goodsOrderEntity.getMerchantId(),goodsOrderEntity.getUserAddressId(),goodsOrderEntity.getMerchantAddressId(),4,goodsOrderEntity.getGoodsNum(),goodsOrderEntity.getGoodsTotalPrice(),goodsOrderEntity.getReceivingTime(),goodsOrderEntity.getLeaseTime(),goodsOrderEntity.getDeposit()*0.1,id);
                tblMerchantService.modifyMerchant(merchantEntity.getId(),merchantEntity.getAccount(),merchantEntity.getPassword(),merchantEntity.getMerchantName(),merchant_money,merchantEntity.getAdminFlag());
                tblUserService.modifyUser(userEntity.getId(),userEntity.getAccount(),userEntity.getPassword(),userEntity.getUsername(),user_money);
            }
            else if (order_status == -4)
            {
                double user_money = userEntity.getBalance() + goodsOrderEntity.getDeposit() + goodsOrderEntity.getGoodsTotalPrice();
                tblUserService.modifyUser(userEntity.getId(),userEntity.getAccount(),userEntity.getPassword(),userEntity.getUsername(),user_money);
                tblGoodsService.modifyGoods(goodsEntity.getGoodsName(),goodsEntity.getPrice(),goodsEntity.getCategoryId(),goodsEntity.getMerchantId(),goodsEntity.getIntro(),goodsEntity.getStatus(),goodsEntity.getPicUrl(),goodsEntity.getMode(),goodsEntity.getDeposit(),goodsEntity.getStock()+goodsOrderEntity.getGoodsNum(),goodsEntity.getSales()-goodsOrderEntity.getGoodsNum(),goodsEntity.getLeaseTime(),goodsEntity.getId());
            }
            else if (order_status == 5){
                Date now = new Date();
                now.setTime(System.currentTimeMillis()+13*60*60*1000);
                tblGoodsOrderService.modifyGoodsOrder(goodsOrderEntity.getUserId(),goodsOrderEntity.getGoodsId(),goodsOrderEntity.getMerchantId(),goodsOrderEntity.getUserAddressId(),goodsOrderEntity.getMerchantAddressId(),order_status,goodsOrderEntity.getGoodsNum(),goodsOrderEntity.getGoodsTotalPrice(),now,goodsOrderEntity.getLeaseTime(),goodsOrderEntity.getDeposit(),id);
            }
            result.put("port","200");
        }
        catch (Exception e){
            tblGoodsOrderService.modifyGoodsOrder(goodsOrderEntity.getUserId(),goodsOrderEntity.getGoodsId(),goodsOrderEntity.getMerchantId(),goodsOrderEntity.getUserAddressId(),goodsOrderEntity.getMerchantAddressId(),goodsOrderEntity.getOrderStatus(),goodsOrderEntity.getGoodsNum(),goodsOrderEntity.getGoodsTotalPrice(),goodsOrderEntity.getReceivingTime(),goodsOrderEntity.getLeaseTime(),goodsOrderEntity.getDeposit(),id);
            tblGoodsService.modifyGoods(goodsEntity.getGoodsName(),goodsEntity.getPrice(),goodsEntity.getCategoryId(),goodsEntity.getMerchantId(),goodsEntity.getIntro(),goodsEntity.getStatus(),goodsEntity.getPicUrl(),goodsEntity.getMode(),goodsEntity.getDeposit(),goodsEntity.getStock(),goodsEntity.getSales(),goodsEntity.getLeaseTime(),goodsEntity.getId());
            tblMerchantService.modifyMerchant(merchantEntity.getId(),merchantEntity.getAccount(),merchantEntity.getPassword(),merchantEntity.getMerchantName(),merchantEntity.getBalance(),merchantEntity.getAdminFlag());
            tblUserService.modifyUser(userEntity.getId(),userEntity.getAccount(),userEntity.getPassword(),userEntity.getUsername(),userEntity.getBalance());
            result.put("port","500");
            result.put("msg","修改异常");
        }
        return result;
    }

    @RequestMapping(value="/modifyReceivingTime",method= RequestMethod.POST)
    public JSONObject modifyGoodsOrderReceivingTime(@RequestParam(value = "id")int id,
                                             @RequestParam(value = "receiving_time")Date receiving_time)
    {
        JSONObject result=new JSONObject();
        try {
            GoodsOrderEntity goodsOrderEntity = tblGoodsOrderService.findById(id);
            tblGoodsOrderService.modifyGoodsOrder(goodsOrderEntity.getUserId(),goodsOrderEntity.getGoodsId(),goodsOrderEntity.getMerchantId(),goodsOrderEntity.getUserAddressId(),goodsOrderEntity.getMerchantAddressId(), 5, goodsOrderEntity.getGoodsNum(),goodsOrderEntity.getGoodsTotalPrice(),receiving_time,goodsOrderEntity.getLeaseTime(),goodsOrderEntity.getDeposit(),id);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","修改异常");
        }
        return result;
    }

//    @RequestMapping(value="/modifyDeposit",method= RequestMethod.POST)
//    public JSONObject modifyGoodsOrderDeposit(@RequestParam(value = "id")int id,
//                                             @RequestParam(value = "deposit")double deposit)
//    {
//        JSONObject result=new JSONObject();
//        try {
//            GoodsOrderEntity goodsOrderEntity = tblGoodsOrderService.findById(id);
//            tblGoodsOrderService.modifyGoodsOrder(goodsOrderEntity.getUserId(),goodsOrderEntity.getGoodsId(),goodsOrderEntity.getMerchantId(),goodsOrderEntity.getUserAddressId(),goodsOrderEntity.getMerchantAddressId(), goodsOrderEntity.getOrderStatus(), goodsOrderEntity.getGoodsNum(),goodsOrderEntity.getGoodsTotalPrice(),goodsOrderEntity.getReceivingTime(),goodsOrderEntity.getLeaseTime(),deposit,id);
//            result.put("port","200");
//        }
//        catch (Exception e){
//            result.put("port","500");
//            result.put("msg","修改异常");
//        }
//        return result;
//    }


//    @RequestMapping(value="/deletebyid",method= RequestMethod.GET)
//    public JSONObject deleteGoodsOrderById(@RequestParam(value = "id") int id)
//    {
//        JSONObject result=new JSONObject();
//        try {
//            tblGoodsOrderService.deleteGoodsOrder(id);
//            result.put("port","200");
//        }
//        catch (Exception e){
//            result.put("port","500");
//            result.put("msg","删除异常");
//        }
//        return result;
//    }
}
