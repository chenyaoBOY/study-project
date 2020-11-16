package org.study.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sharding.jdbc.spilt.bean.OrderProductInfo;
import sharding.jdbc.spilt.bean.ShopOrder;
import sharding.jdbc.spilt.bean.ShopOrderProduct;
import sharding.jdbc.spilt.dao.ShopOrderMapper;
import sharding.jdbc.spilt.dao.ShopOrderProductMapper;

import java.util.*;

/**
 * @author chenyao
 * @date 2019/8/15 16:22
 * @description
 */
@RestController
public class OrderController {

    @Autowired
    private ShopOrderMapper orderMapper;
    @Autowired
    private ShopOrderProductMapper productMapper;

    @RequestMapping("/insertOrder")
    public String insertOrder(@RequestParam("id") int id) {
        ShopOrder record = new ShopOrder();
        record.setAddress("bj");
        record.setAddTime(new Date());
        record.setOrderSn(UUID.randomUUID().toString());
        record.setEmail("@qq.com");
        record.setOrderStatus((byte) 1);
        record.setOrderType((byte) 1);
        record.setPayTime(new Date());
        record.setUserId(1001);
        record.setTotalPay(11);
        record.setPhone("199");
        record.setId(id);
        int insert = orderMapper.insert(record);

        return record.getId() + " num=" + insert;
    }

    @RequestMapping("/getOrderById")
    public String getOrderById(@RequestParam("id") int id) {
        ShopOrder shopOrder = orderMapper.selectByPrimaryKey(id);
        return JSONObject.toJSONString(shopOrder);
    }

    @RequestMapping("/deleteById")
    public String deleteById(@RequestParam("id") int id) {
        int i = orderMapper.deleteByPrimaryKey(id);
        return i + "";
    }

    @RequestMapping("/getOrder")
    public String getOrder() {
        ShopOrder i = orderMapper.getOrder(17, 1, "@qq.com");
        return JSONObject.toJSONString(i);
    }

    @RequestMapping("/getShopOrderList")
    public String getShopOrderList(@RequestParam("ids") String ids) {
        List<Integer> list = new ArrayList();
        if (ids != null && !ids.equals("")) {
            String[] split = ids.split(",");
            for (String s : split) {
                list.add(Integer.valueOf(s));
            }
        }
        List shopOrderList = orderMapper.getShopOrderList(list);
        return JSONObject.toJSONString(shopOrderList);
    }

    @RequestMapping("/insertProduct")
    public String insertProduct(Integer id, Integer oId) {
        if (id == null) {
            id = new Random().nextInt(10000);
        }
        ShopOrderProduct pro = new ShopOrderProduct();
        pro.setId(id);
        pro.setOrderId(oId == null ? 1 : oId);
        pro.setProductName("苹果" + id);
        pro.setProductSn(UUID.randomUUID().toString());
        pro.setSellNum(10);
        pro.setProductId(10000 + id);
        pro.setSupplierName("集美供应商");
        pro.setProductMoney("10.99");

        int insert = productMapper.insert(pro);
        return insert + " productId=" + id;
    }

    @RequestMapping("/getOrderProductInfo")
    public String getOrderProductInfoById(Integer id){
        List<OrderProductInfo> list = orderMapper.getProductInfos(id);
        return JSONObject.toJSONString(list);
    }
    @RequestMapping("/getOrderProductInfoCrossDb")
    public String getOrderProductInfoCrossDb(Integer id){
       OrderProductInfo list = orderMapper.getProductInfoCrossDb(id);
        return JSONObject.toJSONString(list);
    }

}
