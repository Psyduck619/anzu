package zucc.dorm316.anzu.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import zucc.dorm316.anzu.entity.UserAddressEntity;
import zucc.dorm316.anzu.service.TblUserAddressService;
import zucc.dorm316.anzu.service.TblUserService;

import java.util.List;

@Transactional
@RestController
@CrossOrigin
@RequestMapping(value="/user_address")
public class TblUserAddressController {
    @Autowired
    TblUserAddressService tblUserAddressService;

    @Autowired
    TblUserService tblUserService;

    @RequestMapping(value="/findById",method= RequestMethod.GET)
    public JSONObject findById(@RequestParam(value = "id") int id){
        UserAddressEntity userAddressEntity = tblUserAddressService.findById(id);
        JSONObject result=new JSONObject();
        if (userAddressEntity == null)
        {
            result.put("port","500");
            result.put("msg","无此地址");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",userAddressEntity);
            return result;
        }
    }

    @RequestMapping(value="/findAllByUserId",method= RequestMethod.GET)
    public JSONObject findByUserId(@RequestParam(value = "user_id") int user_id){
        List<UserAddressEntity> userAddressEntityList = tblUserAddressService.findAllByUserid(user_id);
        JSONObject result=new JSONObject();
        if (userAddressEntityList.size()==0)
        {
            result.put("port","500");
            result.put("msg","该用户尚未添加地址");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",userAddressEntityList);
            return result;
        }
    }

    @RequestMapping(value="/deletebyid",method= RequestMethod.GET)
    public JSONObject deleteUserAddressById(@RequestParam(value = "id") int id)
    {
        JSONObject result=new JSONObject();
        try {
            tblUserAddressService.deleteUserAddressById(id);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","删除异常");
        }
        return result;
    }

    @RequestMapping(value="/add",method= RequestMethod.POST)
    public JSONObject addUserAddress(@RequestParam(value = "user_id")int user_id,
                              @RequestParam(value = "prov")String prov,
                              @RequestParam(value = "city")String city,
                              @RequestParam(value = "area")String area,
                              @RequestParam(value = "detail")String detail,
                              @RequestParam(value = "name")String name,
                              @RequestParam(value = "tel")String tel,
                              @RequestParam(value = "address_code")String address_code)
    {
        JSONObject result=new JSONObject();
        try {
                if (tblUserAddressService.findAllByUserid(user_id).size()==0)
                    tblUserAddressService.addUserAddress(user_id, prov, city, area,detail, name, tel,1,address_code);
                else
                    tblUserAddressService.addUserAddress(user_id, prov, city, area,detail, name, tel,0,address_code);
                result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","添加异常");//增加异常
        }
        return result;
    }

    @RequestMapping(value="/modify",method= RequestMethod.POST)
    public JSONObject modifyUserAddress(@RequestParam(value = "user_id")int user_id,
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
            tblUserAddressService.modifyUserAddress(user_id, prov, city, area,detail, name, tel,tblUserAddressService.findById(id).getIsDefault(),address_code,id);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","修改异常");
        }
        return result;
    }

    @RequestMapping(value="/modifyDefault",method= RequestMethod.POST)
    public JSONObject modifyUserDefaultAddress(@RequestParam(value = "user_id")int user_id,
                                        @RequestParam(value = "id")int id )
    {
        JSONObject result=new JSONObject();
        try {
            UserAddressEntity old_default = tblUserAddressService.findDefaultAddressByUserId(user_id);
            System.out.println(old_default.getId());
            tblUserAddressService.modifyUserAddress(old_default.getUserId(), old_default.getProv(), old_default.getCity(),old_default.getArea(),old_default.getDetail(),old_default.getName(),old_default.getTel(),0,old_default.getAddressCode(),old_default.getId());
            UserAddressEntity new_default = tblUserAddressService.findById(id);
            tblUserAddressService.modifyUserAddress(new_default.getUserId(),new_default.getProv(),new_default.getCity(),new_default.getArea(),new_default.getDetail(),new_default.getName(),new_default.getTel(),1,new_default.getAddressCode(),new_default.getId());
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","修改异常");
        }
        return result;
    }
}
