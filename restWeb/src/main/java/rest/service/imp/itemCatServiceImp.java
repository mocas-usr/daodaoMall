package rest.service.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/16 下午4:45
 * @email: wangyuhang_mocas@163.com
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.dao.TbItemCatMapper;
import rest.entity.TbItemCat;
import rest.entity.TbItemCatExample;
import rest.pojo.catNode;
import rest.pojo.catResult;
import rest.service.itemCatService;

import java.util.ArrayList;
import java.util.List;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-16 16:45
 */
@Service
public class itemCatServiceImp implements itemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;

    /**
    *@Description: 获取分类展示的信息
    *@Param:
    *@return:
    *@Author: mocas_wang
    *@date:
    */
    @Override
    public catResult getItemCatList() {

        catResult catResult=new catResult();
        catResult.setData(getCatList(0));
        return catResult;
    }


    /** 
     * @Description:分类列表
     * @Author: mocas_wang
     * @Date: 上午10:13 2020/9/22
     * @Param: [parentId]
     * @return: java.util.List<?>
    **/
    private List<?> getCatList(long parentId)
    {
        //创建查询条件
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        //返回值list
        List resultList = new ArrayList<>();
        //向list中添加节点
        int count = 0;
        for (TbItemCat tbItemCat : list) {
            //判断是否为父节点
            if (tbItemCat.getIsParent()) {
                catNode catNode = new catNode();
                if (parentId == 0) {
                    catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
                } else {
                    catNode.setName(tbItemCat.getName());
                }
                catNode.setUrl("/products/"+tbItemCat.getId()+".html");
                catNode.setItem(getCatList(tbItemCat.getId()));

                resultList.add(catNode);
                count ++;
                //第一层只取14条记录
                if (parentId == 0 && count >=14) {
                    break;
                }
                //如果是叶子节点
            } else {
                resultList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
            }
        }
        return resultList;
    }
}
