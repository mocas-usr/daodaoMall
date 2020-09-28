package search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import search.pojo.SearchResult;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/28 上午11:20
 * @email: wangyuhang_mocas@163.com
 */
public interface SearchDao {
    SearchResult search(SolrQuery query) throws SolrServerException;
}
