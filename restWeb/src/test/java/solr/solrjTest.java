package solr;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/27 下午5:20
 * @email: wangyuhang_mocas@163.com
 */

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-27 17:20
 */
public class solrjTest {

    @Test
    public void addDocument() throws IOException, SolrServerException {
        //创建一个连接
        SolrServer solrServer=new HttpSolrServer("http://localhost:8080/solr/test_core1/");

        try
        {
            //创建一个文档对象
            SolrInputDocument document=new SolrInputDocument();
            document.addField("id","test001");
            document.addField("item_title","测试商品1");
            document.addField("item_price",12345);
            //将文档写入索引库
            solrServer.add(document);

            //提交
            solrServer.commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    @Test
    public void deleteDocument() throws IOException, SolrServerException {
        //创建连接
        SolrServer solrServer=new HttpSolrServer("http://localhost:8080/solr/test_core1");

        solrServer.deleteByQuery("*:*");
        solrServer.commit();

    }


}
