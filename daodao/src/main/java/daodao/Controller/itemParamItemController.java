package daodao.Controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/15 下午9:02
 * @email: wangyuhang_mocas@163.com
 */

import daodao.service.itemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *@program: daodao
 *@description:显示商品信息
 *@author: mocas_wang
 *@create: 2020-09-15 21:02
 */
@Controller
@RequestMapping("/itemParamItem")
public class itemParamItemController {
    @Autowired
    private  itemParamItemService itemParamItemService;

    /**
    *@Description: 显示商品信息
    *@Param: 
    *@return: 
    *@Author: mocas_wang
    *@date: 
    */
    @RequestMapping("/showItemParam/{itemId}")
    public String showItemParam(@PathVariable long itemId, Model model)
    {
        String string=itemParamItemService.getItemParamByItemId(itemId);
        model.addAttribute("itemParam",string);
        return "item";

    }
}
