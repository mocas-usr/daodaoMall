package daodao.Controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/24 下午2:31
 * @email: wangyuhang_mocas@163.com
 */

import daodao.entity.TbContent;
import daodao.entity.pojo.TaotaoResult;
import daodao.service.contentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *@program: daodao
 *@description:内容管理
 *@author: mocas_wang
 *@create: 2020-09-24 14:31
 */
@Controller
@RequestMapping("/content")
public class contentController {

    @Autowired
    private contentService contentService;

    /**
     * @Description:增加内容节点
     * @Author: mocas_wang
     * @Date: 下午3:43 2020/10/10
     * @Param: [tbContent]
     * @return: daodao.entity.pojo.TaotaoResult
    **/

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult insertContent(TbContent tbContent)
    {
        TaotaoResult result=contentService.insertContent(tbContent);
        return result;
    }

}
