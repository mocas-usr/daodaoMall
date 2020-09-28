package search.controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/28 上午10:44
 * @email: wangyuhang_mocas@163.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import search.service.TaotaoResult;
import search.service.itemService;

/**
 *@program: daodao
 *@description:索引库维护
 *@author: mocas_wang
 *@create: 2020-09-28 10:44
 */
@Controller
@RequestMapping("/SolrManager")
public class itemController {

    @Autowired
    private itemService itemService;

    //导入商品到索引库
    @RequestMapping("/import")
    @ResponseBody
    public TaotaoResult importAllItems()
    {
        TaotaoResult taotaoResult=itemService.importAllItems();
        return  taotaoResult;
    }


}
