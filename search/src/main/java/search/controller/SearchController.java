package search.controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/28 上午11:15
 * @email: wangyuhang_mocas@163.com
 */

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import search.pojo.SearchResult;
import search.service.SearchService;
import search.service.TaotaoResult;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-28 11:15
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value="/query", method= RequestMethod.GET)
    @ResponseBody
    public TaotaoResult search(@RequestParam("q")String queryString,
                               @RequestParam(defaultValue="1")Integer page,
                               @RequestParam(defaultValue="60")Integer rows) {
        //查询条件不能为空
        if (StringUtils.isBlank(queryString)) {
            return TaotaoResult.build(400, "查询条件不能为空");
        }
        SearchResult searchResult = null;
        try {
//            queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
            searchResult = searchService.search(queryString, page, rows);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, e.getMessage());
        }
        return TaotaoResult.ok(searchResult);

    }

}