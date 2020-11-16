package org.cyao.ssm.proxy;

import org.cyao.ssm.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

@Component
public class TransactionServiceProxy {

    @Autowired
    private TransactionService service;
    public int insert(int id){
        int i=0;
        try {
            for (int j = 0; j < 2; j++) {
                i = service.saveOrder(j);
            }
        } catch ( DuplicateKeyException e) {
            System.out.println(e.getMessage());
            if(e.getMessage().contains("Duplicate")){
                System.out.println(123);
            }
            System.out.println("主键异常！");
        } catch (DataIntegrityViolationException e) {
            System.out.println("完整性异常！");
            System.exit(1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return i;
    }
}
