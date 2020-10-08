package sso.controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/10/5 下午3:04
 * @email: wangyuhang_mocas@163.com
 */


import com.fasterxml.jackson.databind.util.JSONPObject;
import daodao.entity.TbUser;
import daodao.service.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sso.service.TaotaoResult;
import sso.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-10-05 15:04
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {

        TaotaoResult result = null;

        //参数有效性校验
        if (StringUtils.isBlank(param)) {
            result = TaotaoResult.build(400, "校验内容不能为空");
        }
        if (type == null) {
            result = TaotaoResult.build(400, "校验内容类型不能为空");
        }
        if (type != 1 && type != 2 && type != 3 ) {
            result = TaotaoResult.build(400, "校验内容类型错误");
        }
        //校验出错
        if (null != result) {
            if (null != callback) {
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            } else {
                return result;
            }
        }
        //调用服务
        try {
            result = userService.checkData(param, type);

        } catch (Exception e) {
            result = TaotaoResult.build(500, e.getMessage());
        }

        if (null != callback) {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        } else {
            return result;
        }
    }

    //创建用户
    @RequestMapping(value="/register", method=RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createUser(TbUser user) {

        try {
            TaotaoResult result = userService.createUser(user);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500,e.getMessage());
        }
    }


    //用户登录
//    @RequestMapping(value="/login", method=RequestMethod.POST)
    @RequestMapping(value="/login", method=RequestMethod.POST)
    @ResponseBody
    public TaotaoResult userLogin(String username, String password,
                                  HttpServletRequest request, HttpServletResponse response) {
        try {

            TaotaoResult result = userService.userLogin(username, password, request, response);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, e.getMessage());
        }
    }


    /** 跨域申请jsonp时，取用户数据
     * @Description:
     * @Author: mocas_wang
     * @Date: 下午3:18 2020/10/8
     * @Param: [token,
     * jsonpCallback]
     * @return: java.lang.Object
    **/

    @RequestMapping(value = "/token/{token}",produces = {"application/jsonp;charset=UTF-8"})
    @ResponseBody
    public Object getUserByToken(@PathVariable String token, String jsonpCallback) {
        TaotaoResult result = null;
        String resultStr="";
        try {
            result = userService.getUserByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            result = TaotaoResult.build(500, e.getMessage());
        }

        //判断是否为jsonp调用
        if (StringUtils.isBlank(jsonpCallback)) {
            return result;
        } else {
            /*通过object转换成json，转换不了jsonp*/
//            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
//            mappingJacksonValue.setJsonpFunction(jsonpCallback);
//            return mappingJacksonValue;
            /*直接构建jsonp格式*/
            resultStr=jsonpCallback+"("+JsonUtils.objectToJson(result)+")";
            return resultStr;

        }
    }


    /**
     * @Description:通过token获取用户信息，不跨域
     * @Author: mocas_wang
     * @Date: 下午3:20 2020/10/8
     * @Param: [token]
     * @return: java.lang.Object
     * **/
    @RequestMapping(value = "/token/user/{token}")
    @ResponseBody
    public Object getUserBy(@PathVariable String token) {
        TaotaoResult result = null;
        try {
            result = userService.getUserByToken(token);

        } catch (Exception e) {
            e.printStackTrace();
            result = TaotaoResult.build(500, e.getMessage());
        }
        return result;
    }
}
