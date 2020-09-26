package rest.service.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/26 下午4:26
 * @email: wangyuhang_mocas@163.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rest.dao.JedisClient;
import rest.pojo.TaotaoResult;
import rest.service.redisService;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-26 16:26
 */

@Service
public class redisServiceImp  implements redisService {
    @Autowired
    private JedisClient jedisClient;


    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String  INDEX_CONTENT_REDIS_KEY;

    @Override
    public TaotaoResult syscontent(long contentCid) {
        try {
            jedisClient.hdel(INDEX_CONTENT_REDIS_KEY,contentCid+"");

        }
        catch (Exception e)
        {
            TaotaoResult.build(500,e.getMessage());
        }

        return TaotaoResult.ok();
    }
}
