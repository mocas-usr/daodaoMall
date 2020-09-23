package com.taotao;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/12 上午11:23
 * @email: wangyuhang_mocas@163.com
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import daodao.dao.TbItemMapper;
import daodao.entity.TbItem;
import daodao.entity.TbItemExample;
import javafx.application.Application;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-12 11:23
 */
public class pagehelperTest {
    @Test
    public void  testPageHelpher()
    {
        //创建一个spring容器，
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");

        //从spring容器中获得mapper对象
        TbItemMapper mapper=applicationContext.getBean(TbItemMapper.class);

        //执行查询，并分页

        TbItemExample example=new TbItemExample();
        //分页处理
        PageHelper.startPage(1,10);
        List<TbItem> list=mapper.selectByExample(example);
        //获取商品列表
        for (TbItem tbItem:list)
        {
            System.out.println(tbItem.getTitle());
        }
        //取分页信息
        PageInfo<TbItem> pageInfo=new PageInfo<>(list);
        long total=pageInfo.getTotal();
        System.out.println("共有商品信息："+total);

    }
}
