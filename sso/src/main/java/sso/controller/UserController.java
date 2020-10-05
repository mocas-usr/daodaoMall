package sso.controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/10/5 下午3:04
 * @email: wangyuhang_mocas@163.com
 */

import daodao.entity.TbUser;
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



}
