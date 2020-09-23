package daodao.service;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/22 下午5:09
 * @email: wangyuhang_mocas@163.com
 */

import daodao.entity.pojo.EUtreeNode;

import java.util.List;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-22 17:09
 */
public interface contentCategoryService {

    List<EUtreeNode> getCategoryList(long parentId);

}
