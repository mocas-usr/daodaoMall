package portal.service;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/28 下午3:42
 * @email: wangyuhang_mocas@163.com
 */

import portal.pojo.SearchResult;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-28 15:42
 */
public interface SearchService {
    SearchResult search(String queryString, int page);
}
