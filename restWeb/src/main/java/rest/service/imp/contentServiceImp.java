package rest.service.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/24 下午3:35
 * @email: wangyuhang_mocas@163.com
 */

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rest.dao.JedisClient;
import rest.dao.TbContentMapper;
import rest.entity.TbContent;
import rest.entity.TbContentExample;
import rest.service.contentService;
import rest.utils.JsonUtils;

import java.util.List;

/**
 *@program: daodao
 *@description:商品内容
 *@author: mocas_wang
 *@create: 2020-09-24 15:35
 */
@Service
public class contentServiceImp implements contentService {
    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String  INDEX_CONTENT_REDIS_KEY;

    @Override
    public List<TbContent> getContentList(long contentCid) {
        //缓存中取内容

        try {
            String result=jedisClient.hget(INDEX_CONTENT_REDIS_KEY,contentCid+"");

            if (!StringUtils.isBlank(result))
            {
                //把字符串转换成list
                List<TbContent> resultList= JsonUtils.jsonToList(result,TbContent.class);
                return resultList;
            }

        }catch (Exception e)
        {
            e.printStackTrace();

        }
        //根据内容分类id查询内容列表
        TbContentExample example=new TbContentExample();
        TbContentExample.Criteria criteria=example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);
        //执行查询
        List<TbContent> list=contentMapper.selectByExample(example);

        //向缓存中添加内容
        try {
            //需要把list转换成字符串
            String casheString= JsonUtils.objectToJson(list);
            jedisClient.hset("INDEX_CONTENT_REDIS_KEY",contentCid+"",casheString);

        }
        catch(Exception e)
        {
            e.printStackTrace();

        }
        return list;
    }
}
