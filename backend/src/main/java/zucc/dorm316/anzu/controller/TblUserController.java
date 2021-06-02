package zucc.dorm316.anzu.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import zucc.dorm316.anzu.entity.UserEntity;
import zucc.dorm316.anzu.service.TblUserService;


@Transactional
@RestController
@CrossOrigin
@RequestMapping(value="/user")
public class TblUserController {
    @Autowired
    TblUserService tblUserService;
    @RequestMapping(value="/login",method= RequestMethod.GET)

    public JSONObject login(@RequestParam(value = "account") String account,
                                 @RequestParam(value = "password") String password){
        UserEntity userEntity = tblUserService.findByUserAccount(account);
        JSONObject result=new JSONObject();
        if (userEntity == null)
        {
            result.put("port","500");     //无该用户
            result.put("msg","无该用户");
            return result;
        }
        else if(!userEntity.getPassword().equals(password)){
            result.put("port","400");      //密码错误
            result.put("msg","密码错误");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",userEntity);
            return result;
        }
    }

    @RequestMapping(value="/findByAccount",method= RequestMethod.GET)
    public JSONObject findByAccount(@RequestParam(value = "account") String account){
        UserEntity userEntity = tblUserService.findByUserAccount(account);
        JSONObject result=new JSONObject();
        if (userEntity == null)
        {
            result.put("port","500");
            result.put("msg","无该用户");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",userEntity);
            return result;
        }
    }

    @RequestMapping(value="/deletebyaccount",method= RequestMethod.GET)
    public JSONObject deleteUserByAccount(@RequestParam(value = "account") String account)
    {
        JSONObject result=new JSONObject();
        try {
            tblUserService.deleteUserByUserAccount(account);
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
            tblUserService.deleteUserByUserid(id);
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
                              @RequestParam(value = "userName")String userName)
    {
        JSONObject result=new JSONObject();
        try {
            if (tblUserService.findByUserAccount(account) != null){
                result.put("port","400");
                result.put("msg","该账户已存在");
            }
            else{
                tblUserService.addUser(account,password,userName,0);
                result.put("port","200");
            }
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","添加异常");//增加异常
        }
        return result;
    }

    @RequestMapping(value="/modifyInfo",method= RequestMethod.POST)
    public JSONObject modifyUserInfo(@RequestParam(value = "account")String account,
                              @RequestParam(value = "userName")String userName)
    {
        JSONObject result=new JSONObject();
        try {
            UserEntity user = tblUserService.findByUserAccount(account);
            tblUserService.modifyUser(user.getId(),account,user.getPassword(),userName,user.getBalance());
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","修改异常");//修改异常
        }
        return result;
    }

    @RequestMapping(value="/modifyPassword",method= RequestMethod.POST)
    public JSONObject modifyUserPassword(@RequestParam(value = "account")String account,
                                     @RequestParam(value = "password")String password
    )
    {
        JSONObject result=new JSONObject();
        try {
            UserEntity user = tblUserService.findByUserAccount(account);
            tblUserService.modifyUser(user.getId(),account,password,user.getUsername(),user.getBalance());
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","修改异常");//修改异常
        }
        return result;
    }

    @RequestMapping(value="/modifyBalance",method= RequestMethod.POST)
    public JSONObject modifyUserBalance(@RequestParam(value = "account")String account,
                                     @RequestParam(value = "difBalance")double difBalance   //传入差值金额
    )
    {
        JSONObject result=new JSONObject();
        try {
            UserEntity user = tblUserService.findByUserAccount(account);
            if (user.getBalance()+difBalance < 0){
                result.put("port","400");
                result.put("msg","余额不足");
            }
            else
            {
                tblUserService.modifyUser(user.getId(),account,user.getPassword(),user.getUsername(),user.getBalance()+difBalance);
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
