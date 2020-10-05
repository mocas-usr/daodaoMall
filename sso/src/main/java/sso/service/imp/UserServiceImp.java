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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sso.service.TaotaoResult;
import sso.service.UserService;

import java.util.List;

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
}
