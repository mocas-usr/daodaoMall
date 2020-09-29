package portal.controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/28 下午4:00
 * @email: wangyuhang_mocas@163.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import portal.pojo.SearchResult;
import portal.service.SearchService;

import java.io.UnsupportedEncodingException;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-28 16:00
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    /**
     * @Description:查找商品信息
     * @Author: mocas_wang
     * @Date: 下午7:22 2020/9/28
     * @Param: [queryString, page, model]
     * @return: java.lang.String
    **/

    @RequestMapping("/search")
    public String search(@RequestParam("q")String queryString, @RequestParam(defaultValue="1")Integer page, Model model) {
        if (queryString != null) {
            queryString=queryString;
//            try {
//                queryString = new String(queryString.getBytes("ISO-8859-1"), "UTF-8");
//                queryString=toUTF8(queryString);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
        }
        SearchResult searchResult = searchService.search(queryString, page);
        //向页面传递参数
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", searchResult.getPageCount());
        model.addAttribute("itemList", searchResult.getItemList());
        model.addAttribute("page", page);

        return "search";

    }

    /**
     * @Description:将字符串转换成utf8格式
     * @Author: mocas_wang
     * @Date: 下午4:24 2020/9/28
     * @Param: [str]
     * @return: java.lang.String
    **/
    public String toUTF8(String str) {

        try {
            if (str.equals(new String(str.getBytes("GB2312"), "GB2312"))) {
                str = new String(str.getBytes("GB2312"), "utf-8");
                return str;
            }
        } catch (Exception exception) {
        }
        try {
            if (str.equals(new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"))) {
                str = new String(str.getBytes("ISO-8859-1"), "utf-8");
                return str;
            }
        } catch (Exception exception1) {
        }


        try {
            if (str.equals(new String(str.getBytes("GBK"), "GBK"))) {
                str = new String(str.getBytes("GBK"), "utf-8");
                return str;
            }
        } catch (Exception exception3) {
        }
        return str;
    }

}