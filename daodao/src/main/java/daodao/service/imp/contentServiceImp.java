package daodao.service.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/24 下午2:29
 * @email: wangyuhang_mocas@163.com
 */

import daodao.dao.TbContentMapper;
import daodao.entity.TbContent;
import daodao.entity.pojo.TaotaoResult;
import daodao.service.contentService;
import daodao.service.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *@program: daodao
 *@description:内容获取
 *@author: mocas_wang
 *@create: 2020-09-24 14:29
 */
@Service
public class contentServiceImp implements contentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_CONTENT_URL}")
    private String REST_CONTENT_URL;

    /**
     * @Description:插入商品内容
     * @Author: mocas_wang
     * @Date: 下午3:48 2020/10/10
     * @Param: [content]
     * @return: daodao.entity.pojo.TaotaoResult
    **/

    @Override
    public TaotaoResult insertContent(TbContent content) {
        //补全pojo
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);

        //缓存同步
        try {
            HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_URL+content.getCategoryId());

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return TaotaoResult.ok();
    }
}
