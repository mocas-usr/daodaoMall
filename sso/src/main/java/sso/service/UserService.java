package sso.service;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/10/5 下午2:50
 * @email: wangyuhang_mocas@163.com
 */

import daodao.entity.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-10-05 14:50
 */
public interface UserService {
    TaotaoResult checkData(String content,Integer type);
    TaotaoResult createUser(TbUser tbUser);
    TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
    TaotaoResult getUserByToken(String token);

}
