package portal.service.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/24 下午4:22
 * @email: wangyuhang_mocas@163.com
 */



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import portal.dao.TaotaoResult;
import portal.dao.TbContent;
import portal.httpcilent.HttpClientUtil;
import portal.service.contentService;
import portal.utils.JsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@program: daodao
 *@description:调用服务层
 *@author: mocas_wang
 *@create: 2020-09-24 16:22
 */
@Service
public class contentServiceImp implements contentService {

    @Value("${REST_BASIC_URL}")
    private  String  REST_BASIC_URL;
    @Value("${REST_INDEX_AD_URL}")
    private String  REST_INDEX_AD_URL;

    @Override
    public String getContentList() {
        //调用服务层服务
        String result=HttpClientUtil.doGet(REST_BASIC_URL+REST_INDEX_AD_URL);
        //把字符串转换成TaotaoResult
        try {
            TaotaoResult taotaoResult = TaotaoResult.formatToList(result, portal.dao.TbContent.class);
            //取内容列表
            List<TbContent> list = (List<TbContent>) taotaoResult.getData();
            List<Map> resultList = new ArrayList<>();
            //创建一个jsp页码要求的pojo列表
            for (TbContent tbContent : list) {
                Map map = new HashMap<>();
                map.put("src", tbContent.getPic());
                map.put("height", 240);
                map.put("width", 670);
                map.put("srcB", tbContent.getPic2());
                map.put("widthB", 550);
                map.put("heightB", 240);
                map.put("href", tbContent.getUrl());
                map.put("alt", tbContent.getSubTitle());
                resultList.add(map);
            }
            return JsonUtils.objectToJson(resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
