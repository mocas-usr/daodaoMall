package daodao.Controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/15 上午11:10
 * @email: wangyuhang_mocas@163.com
 */

import daodao.entity.TbItemParam;
import daodao.entity.pojo.TaotaoResult;
import daodao.service.itemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-15 11:10
 */
@Controller
@RequestMapping("/itemParam")
public class itemParamController {
    @Autowired
    private itemParamService itemParamService;

    /**
    *@Description: 通过id添加规格参数
    *@Param: 
    *@return: 
    *@Author: mocas_wang
    *@date: 
    */
    @RequestMapping("/getItemParamByCid/{itemCatId}")
    @ResponseBody
    public TaotaoResult getItemParamByCid(@PathVariable long itemCatId)
    {
        TaotaoResult taotaoResult= itemParamService.getItemParamByCid(itemCatId);
        return taotaoResult;
    }

    /**
    *@Description: 规格参数添加
    *@Param: 
    *@return: 
    *@Author: mocas_wang
    *@date: 
    */
    @RequestMapping("/insertItemParam/{cid}")
    @ResponseBody
    public TaotaoResult insertItemParam(@PathVariable long cid,String paramData)
    {
        //创建pojo对象
        TbItemParam itemParam=new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        TaotaoResult result= itemParamService.insertItemParam(itemParam);
        return result;
    }
}
