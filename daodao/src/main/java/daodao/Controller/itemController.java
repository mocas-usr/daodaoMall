package daodao.Controller;
/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/11 上午9:12
 * @email: wangyuhang_mocas@163.com
 */

import daodao.entity.TbItem;
import daodao.entity.pojo.EUDataGridResult;
import daodao.entity.pojo.TaotaoResult;
import daodao.service.itemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *@program: daodao
 *@description:商品展示
 *@author: mocas_wang
 *@create: 2020-09-11 09:12
 */


@Controller
@RequestMapping("/item")
public class itemController {

    @Autowired
    public itemService itemService;

    /**
     * @Description:商品id获取
     * @Author: mocas_wang
     * @Date: 下午3:45 2020/10/10
     * @Param: [itemId]商品id
     * @return: daodao.entity.TbItem
    **/

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


    /**
    *@Description: 增加商品
    *@Param: 
    *@return: 
    *@Author: mocas_wang
    *@date: 
    */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createItem(TbItem tbItem,String desc,String itemParams) throws Exception {

        TaotaoResult taotaoResult=itemService.createItem(tbItem,desc,itemParams);
        return taotaoResult;
    }
}
