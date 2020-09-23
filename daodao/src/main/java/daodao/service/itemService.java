package daodao.service;

import daodao.entity.TbItem;
import daodao.entity.pojo.EUDataGridResult;
import daodao.entity.pojo.TaotaoResult;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/10 下午4:30
 * @email: wangyuhang_mocas@163.com
 */
public interface itemService {

    TbItem getItemById(long ItemId);

    EUDataGridResult getItemList(int  page,int rows);

    TaotaoResult createItem(TbItem tbItem,String desc,String itemParam) throws Exception;
}
