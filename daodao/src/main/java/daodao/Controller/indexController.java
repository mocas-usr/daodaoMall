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
 *@description:主页跳转
 *@author: mocas_wang
 *@create: 2020-09-12 09:22
 */
@Controller
public class indexController {

    /*打开首页*/
    @RequestMapping("/")
    public  String showIndex()
    {

        return "index";
    }


    //展示其他页面
    @RequestMapping("/{pages}")
    public String showPages(@PathVariable String pages)
    {
        return  pages;
    }


    /*测试页面*/
    @RequestMapping("/test")
    public  String testIndex()
    {

        return "test";
    }

}

