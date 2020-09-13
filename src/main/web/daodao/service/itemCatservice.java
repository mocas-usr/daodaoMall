package daodao.service;

import daodao.entity.pojo.EUtreeNode;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/13 上午9:03
 * @email: wangyuhang_mocas@163.com
 */
public interface itemCatservice {
    List<EUtreeNode> getCatList(long parentId);
}
