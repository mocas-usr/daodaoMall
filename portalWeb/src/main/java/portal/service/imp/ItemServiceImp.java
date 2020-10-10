package portal.service.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/29 上午10:11
 * @email: wangyuhang_mocas@163.com
 */

import daodao.entity.TbItemDesc;
import daodao.entity.TbItemParamItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import portal.httpcilent.HttpClientUtil;
import portal.pojo.ItemInfo;
import portal.service.ItemService;
import portal.utils.JsonUtils;
import portal.utils.TaotaoResult;

import java.util.List;
import java.util.Map;

/**
 *@program: daodao
 *@description:商品
 *@author: mocas_wang
 *@create: 2020-09-29 10:11
 */
@Service
public class ItemServiceImp implements ItemService {

    @Value("${REST_BASIC_URL}")
    private String REST_BASE_URL;
    @Value("${ITME_INFO_URL}")
    private String ITME_INFO_URL;
    @Value("${ITEM_DESC_URL}")
    private String ITEM_DESC_URL;
    @Value("${ITEM_PARAM_URL}")
    private String ITEM_PARAM_URL;

    /**
     * @Description:通过id获取商品
     * @Author: mocas_wang
     * @Date: 下午3:55 2020/10/10
     * @Param: [itemId]
     * @return: portal.pojo.ItemInfo
    **/

    @Override
    public ItemInfo getItemById(Long itemId) {

        try {
            //调用rest的服务查询商品基本信息
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITME_INFO_URL + itemId);
            if (!StringUtils.isBlank(json)) {
                TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, ItemInfo.class);
                if (taotaoResult.getStatus() == 200) {
                    ItemInfo item = (ItemInfo) taotaoResult.getData();
                    return item;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * @Description:通过商品id获取货物描述
     * @Author: mocas_wang
     * @Date: 下午3:55 2020/10/10
     * @Param: [itemId]
     * @return: java.lang.String
    **/

    @Override
    public String getItemDescById(Long itemId) {
        try {
            //查询商品描述
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);
            //转换成java对象
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemDesc.class);
            if (taotaoResult.getStatus() == 200) {
                TbItemDesc itemDesc = (TbItemDesc) taotaoResult.getData();
                //取商品描述信息
                String result = itemDesc.getItemDesc();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @Description:获取购物参数
     * @Author: mocas_wang
     * @Date: 下午3:55 2020/10/10
     * @Param: [itemId]
     * @return: java.lang.String
    **/

    @Override
    public String getItemParam(Long itemId) {
        try {
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + itemId);
            //把json转换成java对象
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
            if (taotaoResult.getStatus() == 200) {
                TbItemParamItem itemParamItem = (TbItemParamItem) taotaoResult.getData();
                String paramData = itemParamItem.getParamData();
                //生成html
                // 把规格参数json数据转换成java对象
                List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
                StringBuffer sb = new StringBuffer();
                sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
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
                //返回html片段
                return sb.toString();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
