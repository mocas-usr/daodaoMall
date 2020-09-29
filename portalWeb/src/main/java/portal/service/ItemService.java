package portal.service;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/29 上午10:08
 * @email: wangyuhang_mocas@163.com
 */

import portal.pojo.ItemInfo;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-29 10:08
 */
public interface ItemService {
    ItemInfo getItemById(Long itemId);
    String getItemDescById(Long itemId);
    String getItemParam(Long itemId);

}
