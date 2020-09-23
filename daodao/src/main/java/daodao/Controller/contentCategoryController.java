package daodao.Controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/22 下午7:45
 * @email: wangyuhang_mocas@163.com
 */

import daodao.entity.pojo.EUtreeNode;
import daodao.service.contentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-22 19:45
 */
@Controller
@RequestMapping("/contentCategory")
public class contentCategoryController {
    @Autowired
    private contentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUtreeNode> getContentCatList(@RequestParam(value = "id",defaultValue = "0") long parentId)
    {
        List<EUtreeNode> list=contentCategoryService.getCategoryList(parentId);
        return list;

    }
}
