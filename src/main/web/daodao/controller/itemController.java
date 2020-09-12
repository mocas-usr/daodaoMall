package daodao.controller;
/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/11 上午9:12
 * @email: wangyuhang_mocas@163.com
 */

import daodao.entity.TbItem;
import daodao.entity.pojo.EUDataGridResult;
import daodao.service.imp.ItemServiceImp;
import daodao.service.itemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-11 09:12
 */


@Controller
@RequestMapping("/item")
public class itemController {

    @Autowired
    public itemService itemService;

    @RequestMapping("/getItemById/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable long itemId)
    {
        TbItem tbItem=itemService.getItemById(itemId);
        return tbItem;
    }


        /*测试*/
    @RequestMapping("/test")
    public String test()
    {

        return "test";
    }


    /**
    *@Description: 查询分页数据
    *@Param: 
    *@return: 
    *@Author: mocas_wang
    *@date: 
    */
    @RequestMapping("/getItemList")
    @ResponseBody
    public EUDataGridResult getItemList(int page,int rows)
    {
        EUDataGridResult result=itemService.getItemList(page,rows);
        return result;
    }
}
