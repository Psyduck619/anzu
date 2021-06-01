package zucc.dorm316.anzu.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import zucc.dorm316.anzu.entity.UserAddressEntity;
import zucc.dorm316.anzu.entity.UserEntity;
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
                              @RequestParam(value = "detail")String detail,
                              @RequestParam(value = "name",required=false)String name,
                              @RequestParam(value = "tel",required=false)String tel)
    {
        JSONObject result=new JSONObject();
        try {
               tblUserAddressService.addUserAddress(user_id, prov, city, detail, name, tel);
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
                              @RequestParam(value = "detail")String detail,
                              @RequestParam(value = "name",required=false)String name,
                              @RequestParam(value = "tel",required=false)String tel,
                              @RequestParam(value = "id")int id )
    {
        JSONObject result=new JSONObject();
        try {
            tblUserAddressService.modifyUserAddress(user_id, prov, city, detail, name, tel,id);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","修改异常");
        }
        return result;
    }

}
