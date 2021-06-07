package zucc.dorm316.anzu.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import zucc.dorm316.anzu.entity.GoodsEntity;
import zucc.dorm316.anzu.service.TblGoodsService;
import java.util.*;

import java.util.List;

@Transactional
@RestController
@CrossOrigin
@RequestMapping(value="/goods")
public class TblGoodsController {
    @Autowired
    TblGoodsService tblGoodsService;

    @RequestMapping(value="/findById",method= RequestMethod.GET)
    public JSONObject findById(@RequestParam(value = "id") int id){
        GoodsEntity goodsEntity = tblGoodsService.findById(id);
        JSONObject result=new JSONObject();
        if (goodsEntity == null)
        {
            result.put("port","500");
            result.put("msg","无此商品");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",goodsEntity);
            return result;
        }
    }

    @RequestMapping(value="/findAllByCategoryId",method= RequestMethod.GET)
    public JSONObject findByCategoryId(@RequestParam(value = "category_id") int category_id){
        List<GoodsEntity> goodsEntityList = tblGoodsService.findAllByCategory(category_id);
        JSONObject result=new JSONObject();
        if (goodsEntityList.size()==0)
        {
            result.put("port","500");
            result.put("msg","该类别尚无商品");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",goodsEntityList);
            return result;
        }
    }

    @RequestMapping(value="/findAllByMerchantId",method= RequestMethod.GET)
    public JSONObject findByMerchantId(@RequestParam(value = "merchant_id") int merchant_id){
        List<GoodsEntity> goodsEntityList = tblGoodsService.findAllByMerchant(merchant_id);
        JSONObject result=new JSONObject();
        if (goodsEntityList.size()==0)
        {
            result.put("port","500");
            result.put("msg","该商家尚未添加商品");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",goodsEntityList);
            return result;
        }
    }

    @RequestMapping(value="/findAllByMode",method= RequestMethod.GET)
    public JSONObject findByMode(@RequestParam(value = "mode") int mode){
        List<GoodsEntity> goodsEntityList = tblGoodsService.findAllByMode(mode);
        JSONObject result=new JSONObject();
        if (goodsEntityList.size()==0)
        {
            result.put("port","500");
            result.put("msg","该模式尚无商品");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",goodsEntityList);
            return result;
        }
    }

    @RequestMapping(value="/findAllShuffle",method= RequestMethod.GET)
    public JSONObject findAllShuffle(){
        List<GoodsEntity> goodsEntityList = tblGoodsService.findAll();
        JSONObject result=new JSONObject();
        if (goodsEntityList.size()==0)
        {
            result.put("port","500");
            result.put("msg","尚无商品");
            return result;
        }
        else{
            result.put("port","200");
            Collections.shuffle(goodsEntityList);
            result.put("data",goodsEntityList);
            return result;
        }
    }

    @RequestMapping(value="/deletebyid",method= RequestMethod.GET)
    public JSONObject deleteGoodsById(@RequestParam(value = "id") int id)
    {
        JSONObject result=new JSONObject();
        try {
            tblGoodsService.deleteById(id);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","删除异常");
        }
        return result;
    }

    @RequestMapping(value="/add",method= RequestMethod.POST)
    public JSONObject addGoods(@RequestParam(value = "goods_name")String goods_name,
                               @RequestParam(value = "price")double price,
                                     @RequestParam(value = "category_id")int category_id,
                                     @RequestParam(value = "merchant_id")int merchant_id,
                                     @RequestParam(value = "intro")String intro,
                                     @RequestParam(value = "stock")int stock,
                                     @RequestParam(value = "pic_url")String pic_url,
                                     @RequestParam(value = "mode")int mode,
                                     @RequestParam(value = "deposit")double deposit,
                                     @RequestParam(value = "lease_time")int lease_time)
    {
        JSONObject result=new JSONObject();
        try {
            tblGoodsService.addGoods(goods_name,price,category_id,merchant_id,intro,-1,pic_url,mode,deposit,stock,0,lease_time);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","添加异常");//增加异常
        }
        return result;
    }

    @RequestMapping(value="/modify",method= RequestMethod.POST)
    public JSONObject modifyGoods(@RequestParam(value = "goods_name")String goods_name,
                                  @RequestParam(value = "price")double price,
                                    @RequestParam(value = "category_id")int category_id,
                                    @RequestParam(value = "merchant_id")int merchant_id,
                                    @RequestParam(value = "intro")String intro,
                                    @RequestParam(value = "status")int status,
                                    @RequestParam(value = "pic_url")String pic_url,
                                    @RequestParam(value = "mode")int mode,
                                    @RequestParam(value = "stock")int stock,
                                    @RequestParam(value = "deposit")double deposit,
                                    @RequestParam(value = "lease_time")int lease_time,
                                    @RequestParam(value = "id")int id)
    {
        JSONObject result=new JSONObject();
        try {
            GoodsEntity goodsEntity = tblGoodsService.findById(id);
            tblGoodsService.modifyGoods(goods_name,price,category_id,merchant_id,intro,status,pic_url,mode,deposit,stock,goodsEntity.getSales(),lease_time,id);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","修改异常");
        }
        return result;
    }
}
