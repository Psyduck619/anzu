package zucc.dorm316.anzu.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import zucc.dorm316.anzu.entity.CartEntity;
import zucc.dorm316.anzu.entity.CategoryEntity;
import zucc.dorm316.anzu.service.TblCartService;
import zucc.dorm316.anzu.service.TblCategoryService;

import java.util.List;

@Transactional
@RestController
@CrossOrigin
@RequestMapping(value="/category")
public class TblCategoryController {
    @Autowired
    TblCategoryService tblCategoryService;

    @RequestMapping(value="/findById",method= RequestMethod.GET)
    public JSONObject findById(@RequestParam(value = "id") int id){
        CategoryEntity categoryEntity = tblCategoryService.findById(id);
        JSONObject result=new JSONObject();
        if (categoryEntity == null)
        {
            result.put("port","500");
            result.put("msg","无此类别");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",categoryEntity);
            return result;
        }
    }

    @RequestMapping(value="/findAll",method= RequestMethod.GET)
    public JSONObject findAll(){
        List<CategoryEntity> categoryEntityList = tblCategoryService.findAll();
        JSONObject result=new JSONObject();
        if (categoryEntityList.size()==0)
        {
            result.put("port","500");
            result.put("msg","尚无类别记录");
            return result;
        }
        else{
            result.put("port","200");
            result.put("data",categoryEntityList);
            return result;
        }
    }

    @RequestMapping(value="/deletebyid",method= RequestMethod.GET)
    public JSONObject deleteCategoryById(@RequestParam(value = "id") int id)
    {
        JSONObject result=new JSONObject();
        try {
            tblCategoryService.deleteById(id);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","删除异常");
        }
        return result;
    }

    @RequestMapping(value="/add",method= RequestMethod.POST)
    public JSONObject addCategory(@RequestParam(value = "name")String name)
    {
        JSONObject result=new JSONObject();
        try {
            tblCategoryService.addCategory(name);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","添加异常");//增加异常
        }
        return result;
    }

    @RequestMapping(value="/modify",method= RequestMethod.POST)
    public JSONObject modifyCategory(@RequestParam(value = "name")String name,
                                        @RequestParam(value = "id")int id )
    {
        JSONObject result=new JSONObject();
        try {
            tblCategoryService.modifyCategory(name,id);
            result.put("port","200");
        }
        catch (Exception e){
            result.put("port","500");
            result.put("msg","修改异常");
        }
        return result;
    }
}
