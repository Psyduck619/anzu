package zucc.dorm316.anzu.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import zucc.dorm316.anzu.entity.MerchantAddressEntity;
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
                                     @RequestParam(value = "tel")String tel,
                                     @RequestParam(value = "address_code")String address_code
                                         )

    {
        JSONObject result=new JSONObject();
        try {
            if (tblMerchantAddressService.findAllByMerchantId(merchant_id).size() == 0){
                tblMerchantAddressService.addMerchantAddress(merchant_id, prov, city, area,detail, name, tel,1,address_code);
            }
            else{
                tblMerchantAddressService.addMerchantAddress(merchant_id, prov, city, area,detail, name, tel,0,address_code);
            }
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
                                        @RequestParam(value = "address_code")String address_code,
                                        @RequestParam(value = "id")int id )
    {
        JSONObject result=new JSONObject();
        try {
            tblMerchantAddressService.modifyMerchantAddress(merchant_id, prov, city, area,detail, name, tel,tblMerchantAddressService.findById(id).getIsDefault(),address_code,id);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","修改异常");
        }
        return result;
    }
    @RequestMapping(value="/modifyDefault",method= RequestMethod.POST)
    public JSONObject modifyMerchantDefaultAddress(@RequestParam(value = "merchant_id")int merchant_id,
                                        @RequestParam(value = "id")int id )
    {
        JSONObject result=new JSONObject();
        try {
            MerchantAddressEntity old_default = tblMerchantAddressService.findDefaultByMerchantId(merchant_id);
            tblMerchantAddressService.modifyMerchantAddress(old_default.getMerchantId(), old_default.getProv(), old_default.getCity(),old_default.getArea(),old_default.getDetail(),old_default.getName(),old_default.getTel(),0,old_default.getAddressCode(),old_default.getId());
            MerchantAddressEntity new_default = tblMerchantAddressService.findById(id);
            tblMerchantAddressService.modifyMerchantAddress(new_default.getMerchantId(),new_default.getProv(),new_default.getCity(),new_default.getArea(),new_default.getDetail(),new_default.getName(),new_default.getTel(),1,new_default.getAddressCode(),new_default.getId());
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","修改异常");
        }
        return result;
    }
}