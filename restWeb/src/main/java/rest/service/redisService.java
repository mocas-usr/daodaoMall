package rest.service;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/26 下午4:09
 * @email: wangyuhang_mocas@163.com
 */

import rest.pojo.TaotaoResult;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-26 16:09
 */
public interface redisService
{
    TaotaoResult syscontent(long contentCid);
}
