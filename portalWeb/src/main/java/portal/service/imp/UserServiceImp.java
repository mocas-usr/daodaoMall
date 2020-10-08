package portal.service.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/10/8 上午9:58
 * @email: wangyuhang_mocas@163.com
 */

import daodao.entity.TbUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import portal.httpcilent.HttpClientUtil;
import portal.service.UserService;
import portal.utils.TaotaoResult;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-10-08 09:58
 */
@Service
public class UserServiceImp implements UserService {
    @Value("${SSO_BASE_URL}")
    public String SSO_BASE_URL;
    @Value("${SSO_DOMAIN_BASE_USRL}")
    public String SSO_DOMAIN_BASE_USRL;
    @Value("${SSO_USER_TOKEN}")
    private String SSO_USER_TOKEN;
    @Value("${SSO_PAGE_LOGIN}")
    public String SSO_PAGE_LOGIN;


    @Override
    public TbUser getUserByToken(String token) {
        try {
            //调用sso系统的服务，根据token取用户信息
            String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
            //把json转换成TaotaoREsult
            TaotaoResult result = TaotaoResult.formatToPojo(json, TbUser.class);
            if (result.getStatus() == 200) {
                TbUser user = (TbUser) result.getData();
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
