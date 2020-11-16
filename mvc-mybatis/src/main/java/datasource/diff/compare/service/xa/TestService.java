package datasource.diff.compare.service.xa;

import datasource.diff.compare.db.two.DB02Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenyao
 * @date 2020/1/6 14:06
 * @description
 */
@Service
public class TestService {

    @Autowired
    DB02Mapper db02Mapper;

    public void update(){
        db02Mapper.update();
    }

}
