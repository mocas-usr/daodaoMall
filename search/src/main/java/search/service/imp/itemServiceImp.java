package search.service.imp;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/28 上午10:20
 * @email: wangyuhang_mocas@163.com
 */

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import search.mapper.ItemMapper;
import search.pojo.Item;
import search.service.TaotaoResult;
import search.service.itemService;

import java.io.IOException;
import java.util.List;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-28 10:20
 */
@Service
public class itemServiceImp implements itemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private SolrServer solrServer;

    /**
     * @Description:查询所有商品
     * @Author: mocas_wang
     * @Date: 上午10:44 2020/9/28
     * @Param: []
     * @return: search.service.TaotaoResult
    **/

    @Override
    public TaotaoResult importAllItems() {
        //查询商品列表
        List<Item> list=itemMapper.getItemList();
        try {
            //把商品写入索引库
            for (Item item:list)
            {
                //创建solr doc对象
                SolrInputDocument document=new SolrInputDocument();
                document.setField("id", item.getId());
                document.setField("item_title", item.getTitle());
                document.setField("item_sell_point", item.getSell_point());
                document.setField("item_price", item.getPrice());
                document.setField("item_image", item.getImage());
                document.setField("item_category_name", item.getCategory_name());
                document.setField("item_desc", item.getItem_des());
                //写入索引库
                solrServer.add(document);

            }
            solrServer.commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return TaotaoResult.build(500,e.getMessage());
        }


        return TaotaoResult.ok();
    }
}
