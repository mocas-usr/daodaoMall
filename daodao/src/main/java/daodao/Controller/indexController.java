package daodao.Controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/12 上午9:22
 * @email: wangyuhang_mocas@163.com
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@program: daodao
 *@description:后台管理主页跳转
 *@author: mocas_wang
 *@create: 2020-09-12 09:22
 */
@Controller
public class indexController {

    /**
     * @Description:打开首页
     * @Author: mocas_wang
     * @Date: 下午3:44 2020/10/10
     * @Param: []
     * @return: java.lang.String
    **/

    @RequestMapping("/")
    public  String showIndex()
    {

        return "index";
    }



    /**
     * @Description:展示其他页面
     * @Author: mocas_wang
     * @Date: 下午3:44 2020/10/10
     * @Param: [pages]
     * @return: java.lang.String
    **/

    @RequestMapping("/{pages}")
    public String showPages(@PathVariable String pages)
    {
        return  pages;
    }



    /** 测试页面
     * @Description:
     * @Author: mocas_wang
     * @Date: 下午3:44 2020/10/10
     * @Param: []
     * @return: java.lang.String
    **/

    @RequestMapping("/test")
    public  String testIndex()
    {

        return "test";
    }

}

