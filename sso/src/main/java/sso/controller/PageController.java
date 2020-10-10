package sso.controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/10/6 上午10:30
 * @email: wangyuhang_mocas@163.com
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@program: daodao
 *@description:页面展示
 *@author: mocas_wang
 *@create: 2020-10-06 10:30
 */
@Controller
@RequestMapping("/page")
public class PageController {

    /**
     * @Description:注册页面
     * @Author: mocas_wang
     * @Date: 下午3:58 2020/10/10
     * @Param: []
     * @return: java.lang.String
    **/

    @RequestMapping("/register")
    public String showRegister() {
        return "register";
    }

    /**
     * @Description:登录页面
     * @Author: mocas_wang
     * @Date: 下午3:59 2020/10/10
     * @Param: [redirect,准备返回的页面
     * model]
     * @return: java.lang.String
    **/

    @RequestMapping("/login")
    public String showLogin(String redirect, Model model) {
        model.addAttribute("redirect", redirect);
        return "login";
    }
}
