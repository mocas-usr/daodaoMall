package rest.controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/16 下午6:11
 * @email: wangyuhang_mocas@163.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rest.pojo.catResult;
import rest.service.itemCatService;
import rest.utils.JsonUtils;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-16 18:11
 */

@Controller
@RequestMapping("/itemCat")
public class itemCatController {
    @Autowired
    private itemCatService itemCatService;

    @RequestMapping(value = "/getItemCatList",
            produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemCatList(String callback)
    {
        catResult  catResult=itemCatService.getItemCatList();
        //把pojo转换成字符串
        String json= JsonUtils.objectToJson(catResult);
        //拼装返回值
        String result=callback+"("+json+")";
        return result;

    }

    /*测试页面*/
    @RequestMapping("/test")
    public  String testIndex()
    {

        return "test";
    }

}
