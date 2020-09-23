package portal.controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/16 下午2:48
 * @email: wangyuhang_mocas@163.com
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-16 14:48
 */
@Controller
public class indexController {

    @RequestMapping("/index")
    public String index()
    {

        return "index";
    }
}
