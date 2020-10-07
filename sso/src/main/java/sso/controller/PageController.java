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
 *@description:
 *@author: mocas_wang
 *@create: 2020-10-06 10:30
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/register")
    public String showRegister() {
        return "register";
    }
    @RequestMapping("/login")
    public String showLogin(String redirect, Model model) {
        model.addAttribute("redirect", redirect);
        return "login";
    }
}
