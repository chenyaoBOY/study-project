package org.cyao.ssm.controller.datasource;

import org.cyao.ssm.dao.bean.DynamicDataSource;
import org.cyao.ssm.dao.bean.Feedback;
import org.cyao.ssm.dao.mapper.dynamic.DynamicFeebackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author chenyao
 * @Description:
 *      动态配置数据源
 *     原理： AbstractRoutingDataSource 的属性 Map集合targetDataSources保存了xml中
 *     配置的多个数据源，通过重写 determineCurrentLookupKey 获取数据源的key值，决定数据源
 * @date 2018/6/23/23:eight
 */
@Controller
public class DynamicDatasourceController {

    @Autowired
    private DynamicFeebackMapper dynamicFeebackMapper;

    /**
     * 通过设置不同的key值 调用不同的数据源
     * @param db 数据库类型
     * @return
     */
    @RequestMapping("/getDynamicList")
    @ResponseBody
    public List<Feedback> getDynamicList(Integer db){
        /**
         * 通过设置
         */
        if(db ==1){
            DynamicDataSource.setDbType( DynamicDataSource.DATA_SOURCE1 );
        }else if(db==2){
            DynamicDataSource.setDbType( DynamicDataSource.DATA_SOURCE2 );
        }else if(db == 3){
            DynamicDataSource.setDbType( DynamicDataSource.DATA_SOURCE3 );
        }else{
            DynamicDataSource.setDbType( DynamicDataSource.DEFAULT_DATA_SOURCE );
        }
        return dynamicFeebackMapper.getList();
    }
}
