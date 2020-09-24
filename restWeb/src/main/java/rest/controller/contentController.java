package rest.controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/24 下午3:48
 * @email: wangyuhang_mocas@163.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rest.entity.TbContent;
import rest.pojo.TaotaoResult;
import rest.service.contentService;

import java.util.List;

/**
 *@program: daodao
 *@description:内容管理controller
 *@author: mocas_wang
 *@create: 2020-09-24 15:48
 */
@Controller
@RequestMapping("/content")
public class contentController {

    @Autowired
    private contentService contentService;

    @RequestMapping("/list/{contentCategoryId}")
    @ResponseBody
    public TaotaoResult getContentList(@PathVariable Long contentCategoryId)
    {
        try {
            List<TbContent> list=contentService.getContentList(contentCategoryId);
            return TaotaoResult.ok(list);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return TaotaoResult.build(500,e.getMessage());

        }

    }

}
