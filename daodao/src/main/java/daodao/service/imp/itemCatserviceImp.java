package daodao.service.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/13 上午9:04
 * @email: wangyuhang_mocas@163.com
 */

import daodao.dao.TbItemCatMapper;
import daodao.entity.TbItemCat;
import daodao.entity.TbItemCatExample;
import daodao.entity.pojo.EUtreeNode;
import daodao.service.itemCatservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *@program: daodao
 *@description:商品处理
 *@author: mocas_wang
 *@create: 2020-09-13 09:04
 */
@Service
public class itemCatserviceImp implements itemCatservice {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    /**
     * @Description:查询商品分类列表
     * @Author: mocas_wang
     * @Date: 下午3:48 2020/10/10
     * @Param: [parentId]
     * @return: java.util.List<daodao.entity.pojo.EUtreeNode>
    **/

    @Override
    public List<EUtreeNode> getCatList(long parentId) {
        //根据查询，创建查询条件
        TbItemCatExample example= new TbItemCatExample();
        TbItemCatExample.Criteria criteria=example.createCriteria();

        criteria.andParentIdEqualTo(parentId);
        //根据条件查询
        List<TbItemCat> list= tbItemCatMapper.selectByExample(example);
        List<EUtreeNode> resultList=new ArrayList<>();

        //把列表转换成treeenodeLIst
        for(TbItemCat tbItemCat:list)
        {
            EUtreeNode node=new EUtreeNode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;
    }
}
