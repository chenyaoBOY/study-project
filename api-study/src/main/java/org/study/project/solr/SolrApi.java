package org.study.project.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyao
 * @Description: 通过solrj访问索引库
 * @date 2018/6/9/17:46
 */
public class SolrApi {

    private static   HttpSolrServer solrServer ;
    private static   String  url = "http://192.168.16.130:18080/solr/";
    static {
        solrServer = new HttpSolrServer(url);
    }
    public static void main(String[] args) throws IOException, SolrServerException {

       addIndex();
//       deleleIndex();

    }

    /**
     * 删除索引
     * @throws IOException
     * @throws SolrServerException
     */
    private static void deleleIndex() throws IOException, SolrServerException {
        UpdateResponse id = solrServer.deleteById("003");
        solrServer.commit();
    }
    private static void query() throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery();

        query.setQuery("");
        query.setFilterQueries("");
        query.setStart(0);
        query.setRows(10);
        query.setHighlight(true);
        query.addSort("price",SolrQuery.ORDER.asc);


    }

    /**
     * 添加索引
     * @throws IOException
     * @throws SolrServerException
     */
    private static void addIndex() throws IOException, SolrServerException {
        /**
         * 不使用Field注解方式
         */
//        SolrInputDocument document= new SolrInputDocument();
//        document.addField("id",book.getId());
//        document.addField("item_title",book.getTitle());
//        document.addField("item_price",book.getSize());

//        solrServer.add(document);

        /**
         * 使用注解方式 更简单
         */
        Book book = new Book("003", "风华小说", "12", 199, "小说风情");

        solrServer.addBean(book);

        solrServer.commit();
    }
}
