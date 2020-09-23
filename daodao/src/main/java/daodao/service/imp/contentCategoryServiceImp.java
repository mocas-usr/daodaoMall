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
import daodao.service.contentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        }
        return resultList;
    }
}
