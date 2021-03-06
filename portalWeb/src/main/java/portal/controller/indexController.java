package portal.controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/16 下午2:48
 * @email: wangyuhang_mocas@163.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import portal.service.contentService;

/**
 *@program: daodao
 *@description:广告页面
 *@author: mocas_wang
 *@create: 2020-09-16 14:48
 */
@Controller
@RequestMapping("/")
public class indexController {
    @Autowired
    private contentService contentService;

    /**
     * @Description:广告页面
     * @Author: mocas_wang
     * @Date: 下午3:51 2020/10/10
     * @Param: [model]
     * @return: java.lang.String
    **/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model)
    {
        String adJson=contentService.getContentList();
        model.addAttribute("ad1",adJson);

        return "index";
    }
}
