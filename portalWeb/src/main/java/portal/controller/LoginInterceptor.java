package portal.controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/10/8 上午9:44
 * @email: wangyuhang_mocas@163.com
 */

import daodao.entity.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import portal.service.imp.UserServiceImp;
import portal.utils.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.handler.Handler;

/**
 *@program: daodao
 *@description:单点登录
 *@author: mocas_wang
 *@create: 2020-10-08 09:44
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserServiceImp userService;

    /**
     * @Description:单点登录预处理
     * @Author: mocas_wang
     * @Date: 下午3:53 2020/10/10
     * @Param: [request,请求
     * response,相应
     * o]是否通过
     * @return: boolean
    **/

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //在Handler执行之前处理
        //判断用户是否登录
        //从cookie中取token
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        //根据token换取用户信息，调用sso系统的接口。
        TbUser user = userService.getUserByToken(token);
        //取不到用户信息
        if (null == user) {
            //跳转到登录页面，把用户请求的url作为参数传递给登录页面。
            response.sendRedirect(userService.SSO_DOMAIN_BASE_USRL + userService.SSO_PAGE_LOGIN
                    + "?redirect=" + request.getRequestURL());
            //返回false
            return false;
        }
        //取到用户信息，放行
        //把用户信息放入Request
        request.setAttribute("user", user);
        //返回值决定handler是否执行。true：执行，false：不执行。
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
