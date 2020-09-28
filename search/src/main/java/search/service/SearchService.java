package search.service;

import search.pojo.SearchResult;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/28 上午11:17
 * @email: wangyuhang_mocas@163.com
 */
public interface SearchService {

    SearchResult search(String queryString, int page, int rows) throws Exception;
}