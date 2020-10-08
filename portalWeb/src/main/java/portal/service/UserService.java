package portal.service;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/10/8 上午9:58
 * @email: wangyuhang_mocas@163.com
 */

import daodao.entity.TbUser;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-10-08 09:58
 */
public interface UserService {
    TbUser getUserByToken(String token);
}
