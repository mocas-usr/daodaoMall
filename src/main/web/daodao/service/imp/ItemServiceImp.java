package daodao.service.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/11 上午8:35
 * @email: wangyuhang_mocas@163.com
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import daodao.dao.TbItemMapper;
import daodao.entity.TbItem;
import daodao.entity.TbItemExample;
import daodao.entity.pojo.EUDataGridResult;
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

    /**
    *@Description: 实现商品列表查询
    *@Param: 
    *@return: 
    *@Author: mocas_wang
    *@date: 
    */
    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        //查询商品列表
        TbItemExample example=new TbItemExample();
        //分页处理
        PageHelper.startPage(page,rows);
        //这里的list是根据page，rows选取出数据
        List<TbItem> list= itemMapper.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result=new EUDataGridResult();

        result.setRows(list);
        //取记录总条数,是全部条数
        PageInfo<TbItem> pageInfo=new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());

        return result;
    }
}
