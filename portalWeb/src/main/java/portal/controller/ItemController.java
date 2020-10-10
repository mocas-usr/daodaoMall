package portal.controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/29 上午10:16
 * @email: wangyuhang_mocas@163.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import portal.pojo.ItemInfo;
import portal.service.ItemService;

/**
 *@program: daodao
 *@description:商品页面
 *@author: mocas_wang
 *@create: 2020-09-29 10:16
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * @Description:展示商品
     * @Author: mocas_wang
     * @Date: 下午3:52 2020/10/10
     * @Param: [itemId, model]
     * @return: java.lang.String
    **/

    @RequestMapping("/item/{itemId}")
    public String showItem(@PathVariable Long itemId, Model model) {
        ItemInfo item = itemService.getItemById(itemId);
        model.addAttribute("item", item);

        return "item";
    }

    /**
     * @Description:展示商品详情
     * @Author: mocas_wang
     * @Date: 下午3:52 2020/10/10
     * @Param: [itemId]
     * @return: java.lang.String
    **/

    @RequestMapping(value="/item/desc/{itemId}", produces= MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemDesc(@PathVariable Long itemId) {
        String string = itemService.getItemDescById(itemId);
        return string;
    }

    /**
     * @Description:商品参数信息
     * @Author: mocas_wang
     * @Date: 下午3:52 2020/10/10
     * @Param: [itemId]
     * @return: java.lang.String
    **/

    @RequestMapping(value="/item/param/{itemId}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemParam(@PathVariable Long itemId) {
        String string = itemService.getItemParam(itemId);
        return string;
    }
}