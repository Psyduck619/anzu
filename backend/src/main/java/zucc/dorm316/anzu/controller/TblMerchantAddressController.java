package zucc.dorm316.anzu.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import zucc.dorm316.anzu.entity.MerchantAddressEntity;
import zucc.dorm316.anzu.service.TblMerchantAddressService;
import java.util.List;

@Transactional
@RestController
@CrossOrigin
@RequestMapping(value="/merchant_address")
public class TblMerchantAddressController {
    @Autowired
    TblMerchantAddressService tblMerchantAddressService;

    @RequestMapping(value="/findById",method= RequestMethod.GET)
    public JSONObject findById(@RequestParam(value = "id") int id){
        MerchantAddressEntity merchantAddressEntity = tblMerchantAddressService.findById(id);
        JSONObject result=new JSONObject();
        if (merchantAddressEntity == null)
        {
            result.put("port","500");
            result.put("msg","无此地址");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",merchantAddressEntity);
            return result;
        }
    }

    @RequestMapping(value="/findAllByMerchantId",method= RequestMethod.GET)
    public JSONObject findByMerchantId(@RequestParam(value = "merchant_id") int merchant_id){
        List<MerchantAddressEntity> merchantAddressEntityList = tblMerchantAddressService.findAllByMerchantId(merchant_id);
        JSONObject result=new JSONObject();
        if (merchantAddressEntityList.size()==0)
        {
            result.put("port","500");
            result.put("msg","该用户尚未添加地址");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",merchantAddressEntityList);
            return result;
        }
    }

    @RequestMapping(value="/deletebyid",method= RequestMethod.GET)
    public JSONObject deleteMerchantAddressById(@RequestParam(value = "id") int id)
    {
        JSONObject result=new JSONObject();
        try {
            tblMerchantAddressService.deleteMerchantAddressById(id);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","删除异常");
        }
        return result;
    }

    @RequestMapping(value="/add",method= RequestMethod.POST)
    public JSONObject addMerchantAddress(@RequestParam(value = "merchant_id")int merchant_id,
                                     @RequestParam(value = "prov")String prov,
                                     @RequestParam(value = "city")String city,
                                     @RequestParam(value = "area")String area,
                                     @RequestParam(value = "detail")String detail,
                                     @RequestParam(value = "name")String name,
                                     @RequestParam(value = "tel")String tel)
    {
        JSONObject result=new JSONObject();
        try {
            tblMerchantAddressService.addMerchantAddress(merchant_id, prov, city, area,detail, name, tel);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","添加异常");//增加异常
        }
        return result;
    }

    @RequestMapping(value="/modify",method= RequestMethod.POST)
    public JSONObject modifyMerchantAddress(@RequestParam(value = "merchant_id")int merchant_id,
                                        @RequestParam(value = "prov")String prov,
                                        @RequestParam(value = "city")String city,
                                        @RequestParam(value = "area")String area,
                                        @RequestParam(value = "detail")String detail,
                                        @RequestParam(value = "name")String name,
                                        @RequestParam(value = "tel")String tel,
                                        @RequestParam(value = "id")int id )
    {
        JSONObject result=new JSONObject();
        try {
            tblMerchantAddressService.modifyMerchantAddress(merchant_id, prov, city, area,detail, name, tel,id);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","修改异常");
        }
        return result;
    }

}