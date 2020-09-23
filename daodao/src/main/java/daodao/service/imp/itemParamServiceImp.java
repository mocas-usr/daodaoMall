package daodao.service.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/15 上午11:03
 * @email: wangyuhang_mocas@163.com
 */

import daodao.dao.TbItemParamMapper;
import daodao.entity.TbItem;
import daodao.entity.TbItemParam;
import daodao.entity.TbItemParamExample;
import daodao.entity.pojo.TaotaoResult;
import daodao.service.itemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-15 11:03
 */

@Service
public class itemParamServiceImp implements itemParamService {

    @Autowired
    private TbItemParamMapper itemParamMapper;

    /**
    *@Description: 根据id获取
    *@Param:
    *@return:
    *@Author: mocas_wang
    *@date:
    */
    @Override
    public TaotaoResult getItemParamByCid(long cid) {
        TbItemParamExample tbItemParamExample=new TbItemParamExample();
        TbItemParamExample.Criteria criteria=tbItemParamExample.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list=itemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);
        //判断是否查询到结果
        if (list!=null && list.size()>0)
        {
            return TaotaoResult.ok(list.get(0));

        }
        return TaotaoResult.ok();
    }

    /**
    *@Description: 规格参数添加
    *@Param: 
    *@return: 
    *@Author: mocas_wang
    *@date: 
    */
    @Override
    public TaotaoResult insertItemParam(TbItemParam itemParam) {
        //补全pojo
        itemParam.setUpdated(new Date());
        itemParam.setCreated(new Date());
        //插入到规格参数模板表
        itemParamMapper.insert(itemParam);

        return TaotaoResult.ok();
    }
}
