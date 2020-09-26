package rest.controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/26 下午4:48
 * @email: wangyuhang_mocas@163.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rest.pojo.TaotaoResult;
import rest.service.redisService;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-26 16:48
 */
@Controller
@RequestMapping("/redis")
public class redisController {
    @Autowired
    private redisService redisService;

    @RequestMapping("/CashSync/{contentCid}")
    @ResponseBody
    public TaotaoResult contentCashSync(@PathVariable long contentCid)
    {
        TaotaoResult result=redisService.syscontent(contentCid);
        return result;
    }

}
