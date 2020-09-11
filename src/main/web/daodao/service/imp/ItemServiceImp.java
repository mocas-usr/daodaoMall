package daodao.service.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/11 上午8:35
 * @email: wangyuhang_mocas@163.com
 */

import daodao.dao.TbItemMapper;
import daodao.entity.TbItem;
import daodao.entity.TbItemExample;
import daodao.service.itemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@program: daodao
 *@description:商品管理service
 *@author: mocas_wang
 *@create: 2020-09-11 08:35
 */


@Service
public class ItemServiceImp  implements itemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long ItemId) {

        TbItemExample example=new TbItemExample();
        //添加查询条件
        TbItemExample.Criteria criteria=example.createCriteria();

        criteria.andIdEqualTo(ItemId);
        List<TbItem> list=itemMapper.selectByExample(example);
        if (list!=null && list.size()>0)
        {
            TbItem item=list.get(0);
            return item;
        }
        return null;
    }
}
