package sso.service.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/10/5 下午2:51
 * @email: wangyuhang_mocas@163.com
 */

import daodao.dao.TbUserMapper;
import daodao.entity.TbUser;
import daodao.entity.TbUserExample;
import daodao.service.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import sso.dao.JedisClient;
import sso.service.TaotaoResult;
import sso.service.UserService;
import sso.utils.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-10-05 14:51
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private TbUserMapper tbUserMapper;


    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;

    /**
     * @Description:检验信息是否存在
     * @Author: mocas_wang
     * @Date: 下午7:13 2020/10/5
     * @Param: [content, type]
     * @return: sso.service.TaotaoResult
    **/

    @Override
    public TaotaoResult checkData(String content,Integer type) {
        //创建查询条件

        TbUserExample example=new TbUserExample();
        TbUserExample.Criteria criteria= example.createCriteria();

        //对数据进行校验，
        //用户名校验
        if (type==1)
        {
            criteria.andUsernameEqualTo(content);
            //电话校验

        }
        else if (2==type)
        {
            criteria.andPhoneEqualTo(content);
            //email校验

        }else
        {
            criteria.andEmailEqualTo(content);

        }
        //执行查询
        List<TbUser> list=tbUserMapper.selectByExample(example);
        if (list==null || list.size()==0)
        {
            return TaotaoResult.ok(true);
        }
        return TaotaoResult.ok(false);
    }

    /**
     * @Description:用户注册
     * @Author: mocas_wang
     * @Date: 下午7:12 2020/10/5
     * @Param: [tbUser]
     * @return: sso.service.TaotaoResult
    **/

    @Override
    public TaotaoResult createUser(TbUser user) {
        user.setUpdated(new Date());
        user.setCreated(new Date());
        //md5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        tbUserMapper.insert(user);
        return TaotaoResult.ok();
    }

    /**
     * @Description:用户登录
     * @Author: mocas_wang
     * @Date: 下午7:48 2020/10/5
     * @Param: [username, password, request, response]
     * @return: sso.service.TaotaoResult
    **/

    @Override
    public TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {

        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = tbUserMapper.selectByExample(example);
        //如果没有此用户名
        if (null == list || list.size() == 0) {
            return TaotaoResult.build(400, "用户名或密码错误");
        }
        TbUser user = list.get(0);
        //比对密码
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            return TaotaoResult.build(400, "用户名或密码错误");
        }
        //生成token
        String token = UUID.randomUUID().toString();
        //保存用户之前，把用户对象中的密码清空。
        user.setPassword(null);
        //把用户信息写入redis
        jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
        //设置session的过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);

       //添加写cookie的逻辑，cookie的有效期是关闭浏览器就失效。
        CookieUtils.setCookie(request, response, "TT_TOKEN", token);

        //返回token
        return TaotaoResult.ok(token);
    }

    /**
     * @Description:根据token从redis中查询用户信息
     * @Author: mocas_wang
     * @Date: 下午4:02 2020/10/10
     * @Param: [token]
     * @return: sso.service.TaotaoResult
    **/

    @Override
    public TaotaoResult getUserByToken(String token) {
        //根据token从redis中查询用户信息
        String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
        //判断是否为空
        if (StringUtils.isBlank(json)) {
            return TaotaoResult.build(400, "此session已经过期，请重新登录");
        }
        //更新过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        //返回用户信息
        return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
    }
}
