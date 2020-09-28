package portal.service.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/28 下午3:44
 * @email: wangyuhang_mocas@163.com
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import portal.httpcilent.HttpClientUtil;
import portal.pojo.SearchResult;
import portal.service.SearchService;
import portal.utils.TaotaoResult;

import java.util.HashMap;
import java.util.Map;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-28 15:44
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;

    @Override
    public SearchResult search(String queryString, int page) {
        // 调用taotao-search的服务
        //查询参数
        Map<String, String> param = new HashMap<>();
        param.put("q", queryString);
        param.put("page", page + "");
        try {
            //调用服务
            String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
            //把字符串转换成java对象
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
            if (taotaoResult.getStatus() == 200) {
                SearchResult result = (SearchResult) taotaoResult.getData();
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
