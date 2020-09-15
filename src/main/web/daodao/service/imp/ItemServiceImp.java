package daodao.service.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/11 上午8:35
 * @email: wangyuhang_mocas@163.com
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import daodao.dao.TbItemDescMapper;
import daodao.dao.TbItemMapper;
import daodao.dao.TbItemParamItemMapper;
import daodao.dao.TbItemParamMapper;
import daodao.entity.TbItem;
import daodao.entity.TbItemDesc;
import daodao.entity.TbItemExample;
import daodao.entity.TbItemParamItem;
import daodao.entity.pojo.EUDataGridResult;
import daodao.entity.pojo.TaotaoResult;
import daodao.service.itemService;
import daodao.service.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;;

    /**
    *@Description: 根据id参数获取商品信息
    *@Param: 
    *@return: 
    *@Author: mocas_wang
    *@date: 
    */
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

    /**
    *@Description: 实现添加商品
    *@Param: 
    *@return: 
    *@Author: mocas_wang
    *@date: 
    */
    @Override
    public TaotaoResult createItem(TbItem tbItem,String desc,String itemParam) throws Exception {
        //item id生成
        long itemId= IDUtils.genItemId();
        tbItem.setId(itemId);
        //商品状态,1正常,2下架,3删除
        tbItem.setStatus((byte)1);
        tbItem.setUpdated(new Date());
        tbItem.setCreated(new Date());
        //商品信息插入数据库
        itemMapper.insert(tbItem);

        //添加商品描述信息
        TaotaoResult result=insertItemDesc(itemId,desc);

        if (result.getStatus()!=200)
        {
            throw new Exception();
        }
        //添加规格参数
        result=insertItemParamItem(itemId,itemParam);
        if (result.getStatus()!=200)
        {
            throw new Exception();
        }
        return TaotaoResult.ok();
    }


    /**
    *@Description: 插入详细信息的描述
    *@Param: 
    *@return: 
    *@Author: mocas_wang
    *@date: 
    */
    private TaotaoResult insertItemDesc(long itemId,String desc)
    {
        TbItemDesc itemDesc=new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);
        return TaotaoResult.ok();
    }


    /**
    *@Description: 添加规格参数
    *@Param: 
    *@return: 
    *@Author: mocas_wang
    *@date: 
    */
    private TaotaoResult insertItemParamItem(long itemId,String itemParam)
    {
        //创建一个pojo
        TbItemParamItem itemParamItem=new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);

        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        //向表中插入数据
        itemParamItemMapper.insert(itemParamItem);
        return  TaotaoResult.ok();



    }
}
