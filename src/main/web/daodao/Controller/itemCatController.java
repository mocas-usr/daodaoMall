package daodao.Controller;
/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/13 上午11:19
 * @email: wangyuhang_mocas@163.com
 */

import daodao.entity.pojo.EUtreeNode;
import daodao.service.itemCatservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *@program: daodao
 *@description:商品分类管理
 *@author: mocas_wang
 *@create: 2020-09-13 11:19
 */

@Controller
@RequestMapping("/itemCat")
public class itemCatController {
    @Autowired
    private itemCatservice itemCatservice;

    @RequestMapping("/getCatList")
    @ResponseBody
    public List<EUtreeNode> getCatList(@RequestParam(value = "id",defaultValue = "0") long parentId)
    {

        List<EUtreeNode> list= itemCatservice.getCatList(parentId);
        return list;
    }


    /*测试*/
    @RequestMapping("/testCat")
    public String test()
    {

        return "test";
    }
}
