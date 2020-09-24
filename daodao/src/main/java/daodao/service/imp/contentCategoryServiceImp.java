package daodao.service.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/22 下午5:10
 * @email: wangyuhang_mocas@163.com
 */

import daodao.dao.TbContentCategoryMapper;
import daodao.entity.TbContentCategory;
import daodao.entity.TbContentCategoryExample;
import daodao.entity.pojo.EUtreeNode;
import daodao.entity.pojo.TaotaoResult;
import daodao.service.contentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *@program: daodao
 *@description:内容分类管理
 *@author: mocas_wang
 *@create: 2020-09-22 17:10
 */
@Service
public class contentCategoryServiceImp implements contentCategoryService {
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    /**
     * @Description:查询功能列表
     * @Author: mocas_wang
     * @Date: 上午9:53 2020/9/24
     * @Param: [parentId] 父节点
     * @return: 查询列表   java.util.List<daodao.entity.pojo.EUtreeNode>
    **/

    @Override
    public List<EUtreeNode> getCategoryList(long parentId) {
        //利用parentid查询节点列表3
        TbContentCategoryExample example=new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria=example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbContentCategory> list=contentCategoryMapper.selectByExample(example);
        List<EUtreeNode> resultList=new ArrayList<>();

        for (TbContentCategory tbContentCategory:list)
        {
            //创建一个节点
            EUtreeNode node=new EUtreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;
    }

    /**
     * @Description:增加信息
     * @Author: mocas_wang
     * @Date: 上午10:02 2020/9/24
     * @Param: [parentId, name]
     * @return: daodao.entity.pojo.TaotaoResult
    **/

    @Override
    public TaotaoResult insertContentCategory(long parentId, String name) {
        //创建一个pojo
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setName(name);
        contentCategory.setIsParent(false);
        //'状态。可选值:1(正常),2(删除)',
        contentCategory.setStatus(1);
        contentCategory.setParentId(parentId);
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        //添加记录
        contentCategoryMapper.insert(contentCategory);
        //查看父节点的isParent列是否为true，如果不是true改成true
        TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
        //判断是否为true
        if(!parentCat.getIsParent()) {
            parentCat.setIsParent(true);
            //更新父节点
            contentCategoryMapper.updateByPrimaryKey(parentCat);
        }
        //返回结果
        return TaotaoResult.ok(contentCategory);
    }


}
