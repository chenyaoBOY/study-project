package spring.validate.property.analyze;
import	java.text.DateFormat;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenyao
 * @date 2019/8/6 14:48
 * @description
 */
public class DatePropertyRegistor implements PropertyEditorRegistrar {
    private String format = "yyyy-MM-dd";

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        //自定义格式转换日期
        registry.registerCustomEditor(Date.class,new CustomPropertyAnalyze(format));
        //使用spring提供的CustomDateEditor转换日期
        /**
         * 会覆盖上面的
         */
        registry.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat(format),true));
    }
}
