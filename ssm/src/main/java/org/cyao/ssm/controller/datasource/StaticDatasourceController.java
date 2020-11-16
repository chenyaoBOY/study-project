package org.cyao.ssm.controller.datasource;

import org.cyao.ssm.dao.bean.DynamicDataSource;
import org.cyao.ssm.dao.bean.Feedback;
import org.cyao.ssm.dao.mapper.data.DataFeebackMapper;
import org.cyao.ssm.dao.mapper.frog.FrogFeebackMapper;
import org.cyao.ssm.dao.mapper.ssm.SsmFeebackMapper;
import org.cyao.ssm.service.FeebackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author chenyao
 * @Description:
 * 通过静态的硬编码方式，在application-dao*.xml文件中 针对不同的mapper文件，
 *      在扫描时传入不同的SqlSesessionFactory工厂，该工厂使用的不同的数据源，所以不同包下的
 *      mapper类使用的数据源就不一样
 * @date 2018/6/24/10:13
 */
@Controller
public class StaticDatasourceController {

    @Autowired
    private DataFeebackMapper dataMapper;
    @Autowired
    private SsmFeebackMapper ssmFeebackMapper;
    @Autowired
    private FrogFeebackMapper frogMapper;

    /**
     * 通过不同的mapper 调用不同的数据库
     * @param db 数据源类型
     * @return
     */
    @RequestMapping("/getList")
    @ResponseBody
    public List<Feedback> getList(Integer db){
        if(db == null){
            return ssmFeebackMapper.getList();
        }else if(db ==1){
            return dataMapper.getList();
        }else if(db==2){
            return ssmFeebackMapper.getList();
        }else if(db == 3){
            return frogMapper.getList();
        }

        return ssmFeebackMapper.getList();
    }


}
