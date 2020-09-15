package daodao.service.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/15 下午8:48
 * @email: wangyuhang_mocas@163.com
 */

import com.sun.scenario.effect.impl.prism.PrImage;
import daodao.dao.TbItemParamItemMapper;
import daodao.entity.TbItemParamItem;
import daodao.entity.TbItemParamItemExample;
import daodao.service.itemParamItemService;
import daodao.service.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-15 20:48
 */
@Service
public class itemParamItemServiceImp implements itemParamItemService {

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public String getItemParamByItemId(long itemId) {
        //根据商品id查询参数
        TbItemParamItemExample example=new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria=example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        //执行查询

        List<TbItemParamItem> list=itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list==null && list.size()==0)
        {
          return "";
        }
        TbItemParamItem itemParamItem=list.get(0);
        String paramData=itemParamItem.getParamData();
        //生成html
        List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
        StringBuffer sb = new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for(Map m1:jsonList) {
            sb.append("        <tr>\n");
            sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
            sb.append("        </tr>\n");
            List<Map> list2 = (List<Map>) m1.get("params");
            for(Map m2:list2) {
                sb.append("        <tr>\n");
                sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
                sb.append("            <td>"+m2.get("v")+"</td>\n");
                sb.append("        </tr>\n");
            }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        return sb.toString();
    }
}
