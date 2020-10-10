package rest.controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/28 下午8:12
 * @email: wangyuhang_mocas@163.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rest.pojo.TaotaoResult;
import rest.service.itemService;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-28 20:12
 */
@Controller
@RequestMapping("/item")
public class itemController {

    @Autowired
    private itemService itemService;

    /**
     * @Description:商品
     * @Author: mocas_wang
     * @Date: 上午9:52 2020/9/29
     * @Param: [itemId]
     * @return: rest.pojo.TaotaoResult
    **/

    @RequestMapping("/info/{itemId}")
    @ResponseBody
    public TaotaoResult getItemBaseInfo(@PathVariable Long itemId) {
        TaotaoResult result = itemService.getItemBaseInfo(itemId);
        return result;
    }

    /**
     * @Description:商品详细描述
     * @Author: mocas_wang
     * @Date: 上午9:52 2020/9/29
     * @Param: [itemId]
     * @return: rest.pojo.TaotaoResult
    **/

    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public TaotaoResult getItemDesc(@PathVariable Long itemId) {
        TaotaoResult result = itemService.getItemDesc(itemId);
        return result;
    }

    /**
     * @Description:商品参数获取
     * @Author: mocas_wang
     * @Date: 下午4:10 2020/10/10
     * @Param: [itemId]
     * @return: rest.pojo.TaotaoResult
    **/

    @RequestMapping("/param/{itemId}")
    @ResponseBody
    public TaotaoResult getItemParam(@PathVariable Long itemId) {
        TaotaoResult result = itemService.getItemParam(itemId);
        return result;
    }
}
