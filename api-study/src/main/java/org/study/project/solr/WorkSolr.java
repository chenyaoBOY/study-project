package org.study.project.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.study.project.util.JsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenyao
 * @Description: 工作测试
 * @date 2018/6/22/16:31
 */
public class WorkSolr {

    private static HttpSolrServer solrServer ;
    private static String url2 = "http://192.168.1.167:18801/solr/product";//翰云嘉亿
    static {
        solrServer = new HttpSolrServer(url2);
    }
    public static void main(String[] args) throws IOException, SolrServerException {

//        addIndex();
       deleleIndex();
//        query();

    }

    private static void deleleIndex() throws IOException, SolrServerException {
        UpdateResponse id = solrServer.deleteByQuery("frameProtocolCode:*");
        solrServer.commit();
    }
    private static void query() throws IOException, SolrServerException {

        SolrQuery query = new SolrQuery();

        query.setQuery("text:小天鹅 AND idShopDefault:(1 11 12 13 ) NOT frameProtocolCode:co");//查询条件  所有字段都查
//        query.setSort("id", SolrQuery.ORDER.desc);
        query.setStart(0);
        query.setRows(10);

        query.setHighlight(true);
        query.addHighlightField("text");
        query.setHighlightSimplePre("<span>");
        query.setHighlightSimplePost("</span>");

        query.setHighlightSnippets(2);// 结果分片数，默认为1
        query.setHighlightFragsize(1000);// 每个分片的最大长度， 默认为100
//        query.setFacet(true);
//        query.setFacetLimit(100);
//        query.addFacetField("idShopDefault","idBrand","idCategoryDefault");

        QueryResponse response = solrServer.query(query);
//        queryFacet(response.getFacetFields());

        SolrDocumentList documents = response.getResults();

        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        for (SolrDocument document : documents) {
            Map<String, List<String>> listMap = highlighting.get(document.get("id"));
            document.setField("text",listMap.get("text").get(0));
            System.out.println(document.get("id").toString() + document.getFieldValue("text"));
        }

    }

    public static void getFacetValues(Map<String,Object> facetMap,String key,List<FacetField.Count> fieldValues){
        List<Object[]> list = new ArrayList<>();
        for (FacetField.Count value : fieldValues) {
            if(value.getCount()>0){
                Object[] obj = new Object[2];
                obj[0]=value.getName();
                obj[1]=value.getCount();
                list.add(obj);
            }
        }
        facetMap.put(key,list);
    }

    public static void queryFacet(List<FacetField> facetFields){
        Map<String,Object> facetMap =  new HashMap<>();
        for (FacetField f : facetFields) {
            String fieldName = f.getName();
            List<FacetField.Count> fieldValues = f.getValues();
            if(fieldName.equals("idShopDefault")){
                getFacetValues(facetMap,"idShopDefault",fieldValues);
            }else if(fieldName.equals("idBrand")){
                getFacetValues(facetMap,"idBrand",fieldValues);
            }else if(fieldName.equals("idCategoryDefault")){
                getFacetValues(facetMap,"idCategoryDefault",fieldValues);
            }
        }
        System.out.println(JsonUtil.objectToJson(facetMap));
    }
    private static void addIndex() throws IOException, SolrServerException {

        Product p = new Product();
        List<String> list = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            p.setId("00"+i);
            p.setIdProduct(i);// 产品ID
            p.setIdSupplier(1);// 供应商ID
            p.setIdManufacturer(2);// 制造商ID
            p.setIdCategoryDefault(2);// 默认分类ID
            p.setIdShopDefault(1);// 默认商店ID
            p.setNameN("陈陈 阿斯顿噶山豆根"+i);// 名称
            if(i/2 ==0){
                list.add("0001");
                list.add("0002");
                list.add("0003");
            }else{
                list.add("0002");
                list.add("0003");
                list.add("0004");
                list.add("0005");
            }
            p.setOrgDeptIds(list);
            p.setFrameProtocolCode("CO2018070200"+i);
            solrServer.addBean(p);
        }
//        p.setId("006");
//        p.setIdProduct(1);// 产品ID
//        p.setIdSupplier(1);// 供应商ID
//        p.setIdManufacturer(2);// 制造商ID
//        p.setIdCategoryDefault(2);// 默认分类ID
//        p.setIdShopDefault(1);// 默认商店ID
//        p.setNameN("陈陈 阿斯顿噶山豆根");// 名称
//        p.setFrameProtocolCode("10000001,10000002,10000003");
//        solrServer.addBean(p);

        solrServer.commit();
    }
}
