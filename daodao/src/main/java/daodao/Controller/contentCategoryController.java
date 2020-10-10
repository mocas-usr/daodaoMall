package daodao.Controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/22 下午7:45
 * @email: wangyuhang_mocas@163.com
 */

import daodao.entity.pojo.EUtreeNode;
import daodao.entity.pojo.TaotaoResult;
import daodao.service.contentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *@program: daodao
 *@description:管理后台列表内容
 *@author: mocas_wang
 *@create: 2020-09-22 19:45
 */
@Controller
@RequestMapping("/contentCategory")
public class contentCategoryController {
    @Autowired
    private contentCategoryService contentCategoryService;

    /**
     * @Description:列表显示内容分类
     * @Author: mocas_wang
     * @Date: 上午10:14 2020/9/24
     * @Param: [parentId]
     * @return: java.util.List<daodao.entity.pojo.EUtreeNode>
    **/

    @RequestMapping("/list")
    @ResponseBody
    public List<EUtreeNode> getContentCatList(@RequestParam(value = "id",defaultValue = "0") long parentId)
    {
        List<EUtreeNode> list=contentCategoryService.getCategoryList(parentId);
        return list;

    }

    /**
     * @Description:增加内容节点
     * @Author: mocas_wang
     * @Date: 上午10:15 2020/9/24
     * @Param: [parentId, name]
     * @return: daodao.entity.pojo.TaotaoResult
    **/

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createContentCategory(Long parentId, String name) {
        TaotaoResult result = contentCategoryService.insertContentCategory(parentId, name);
        return result;
    }
}
