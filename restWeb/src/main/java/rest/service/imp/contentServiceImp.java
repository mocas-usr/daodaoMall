package rest.service.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/24 下午3:35
 * @email: wangyuhang_mocas@163.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.dao.TbContentMapper;
import rest.entity.TbContent;
import rest.entity.TbContentExample;
import rest.service.contentService;

import java.util.List;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-24 15:35
 */
@Service
public class contentServiceImp implements contentService {
    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public List<TbContent> getContentList(long contentCid) {
        //根据内容分类id查询内容列表
        TbContentExample example=new TbContentExample();
        TbContentExample.Criteria criteria=example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);
        //执行查询
        List<TbContent> list=contentMapper.selectByExample(example);

        return list;
    }
}
