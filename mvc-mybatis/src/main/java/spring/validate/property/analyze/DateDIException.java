package spring.validate.property.analyze;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * @author chenyao
 * @date 2019/8/6 14:09
 * @description
 */
public class DateDIException {
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}