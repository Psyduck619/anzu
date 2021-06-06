package zucc.dorm316.anzu.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import zucc.dorm316.anzu.entity.MerchantEntity;
import zucc.dorm316.anzu.service.TblMerchantService;
import org.springframework.util.DigestUtils;

@Transactional
@RestController
@CrossOrigin
@RequestMapping(value="/merchant")
public class TblMerchantController {
    @Autowired
    TblMerchantService tblMerchantService;
    @RequestMapping(value="/login",method= RequestMethod.GET)

    public JSONObject login(@RequestParam(value = "account") String account,
                            @RequestParam(value = "password") String password){
        MerchantEntity merchantEntity = tblMerchantService.findByMerchantAccount(account);
        JSONObject result=new JSONObject();
        if (merchantEntity == null)
        {
            result.put("port","500");     //无该用户
            result.put("msg","无此用户");
            return result;
        }
        else if(!merchantEntity.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
            result.put("port","400");
            result.put("msg","密码错误");   //密码错误
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",merchantEntity);
            return result;
        }
    }

    @RequestMapping(value="/findByAccount",method= RequestMethod.GET)
    public JSONObject findBy(@RequestParam(value = "account") String account){
        MerchantEntity merchantEntity = tblMerchantService.findByMerchantAccount(account);
        JSONObject result=new JSONObject();
        if (merchantEntity == null)
        {
            result.put("port","500");
            result.put("msg","无该用户");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",merchantEntity);
            return result;
        }
    }


    @RequestMapping(value="/deletebyaccount",method= RequestMethod.GET)
    public JSONObject deleteUserByAccount(@RequestParam(value = "account") String account)
    {
        JSONObject result=new JSONObject();
        try {
            tblMerchantService.deleteUserByMerchantAccount(account);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","删除异常");
        }
        return result;
    }

    @RequestMapping(value="/deletebyid",method= RequestMethod.GET)
    public JSONObject deleteUserById(@RequestParam(value = "id") int id)
    {
        JSONObject result=new JSONObject();
        try {
            tblMerchantService.deleteMerchantByMerchantId(id);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","删除异常");
        }
        return result;
    }

    @RequestMapping(value="/add",method= RequestMethod.POST)
    public JSONObject addUser(@RequestParam(value = "account")String account,
                              @RequestParam(value = "password")String password,
                              @RequestParam(value = "merchant_name")String merchant_name
                              )
    {
        JSONObject result=new JSONObject();
        try {
            if (tblMerchantService.findByMerchantAccount(account) != null){
                result.put("port","400");
                result.put("msg","该账户已存在");
            }
            else{
                tblMerchantService.addMerchant(account,DigestUtils.md5DigestAsHex(password.getBytes()),merchant_name,0,0);
                result.put("port","200");
                result.put("data",tblMerchantService.findByMerchantAccount(account));
            }
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","添加异常");//增加异常
        }
        return result;
    }

    @RequestMapping(value="/modifyInfo",method= RequestMethod.POST)
    public JSONObject modifyUserInfo(@RequestParam(value = "id")int id,
                                     @RequestParam(value = "merchant_name")String merchant_name)
    {
        JSONObject result=new JSONObject();
        try {
            MerchantEntity merchantEntity = tblMerchantService.findByMerchantId(id);
            tblMerchantService.modifyMerchant(merchantEntity.getId(),merchantEntity.getAccount(),DigestUtils.md5DigestAsHex(merchantEntity.getPassword().getBytes()),merchant_name,merchantEntity.getBalance(),merchantEntity.getAdminFlag());
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","修改异常");//修改异常
        }
        return result;
    }

    @RequestMapping(value="/modifyPassword",method= RequestMethod.POST)
    public JSONObject modifyUserPassword(@RequestParam(value = "id")int id,
                                         @RequestParam(value = "password")String password
    )
    {
        JSONObject result=new JSONObject();
        try {
            MerchantEntity merchantEntity = tblMerchantService.findByMerchantId(id);
            tblMerchantService.modifyMerchant(merchantEntity.getId(),merchantEntity.getAccount(),DigestUtils.md5DigestAsHex(password.getBytes()),merchantEntity.getMerchantName(),merchantEntity.getBalance(), merchantEntity.getAdminFlag());
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","修改异常");//修改异常
        }
        return result;
    }

    @RequestMapping(value="/modifyBalance",method= RequestMethod.POST)
    public JSONObject modifyUserBalance(@RequestParam(value = "id")int id,
                                        @RequestParam(value = "difBalance")double difBalance   //传入差值金额
    )
    {
        JSONObject result=new JSONObject();
        try {
            MerchantEntity merchantEntity = tblMerchantService.findByMerchantId(id);
            if (merchantEntity.getBalance()+difBalance < 0){
                result.put("port","400");
                result.put("msg","余额不足");
            }
            else
            {
                tblMerchantService.modifyMerchant(merchantEntity.getId(),merchantEntity.getAccount(),DigestUtils.md5DigestAsHex(merchantEntity.getPassword().getBytes()),merchantEntity.getMerchantName(),merchantEntity.getBalance()+difBalance,merchantEntity.getAdminFlag());
                result.put("port","200");
            }
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","修改异常");//修改异常
        }
        return result;
    }
}
