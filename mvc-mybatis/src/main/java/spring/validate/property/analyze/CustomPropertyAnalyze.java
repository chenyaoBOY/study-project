package spring.validate.property.analyze;
import org.springframework.beans.PropertyEditorRegistry;

import java.text.ParseException;
import	java.text.SimpleDateFormat;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * @author chenyao
 * @date 2019/8/6 14:18
 * @description
 */
public class CustomPropertyAnalyze extends PropertyEditorSupport {

    private String format;

    public CustomPropertyAnalyze(String format) {
        this.format = format;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text instanceof String) {
            SimpleDateFormat format = new SimpleDateFormat(this.format);
            try {
                Date date = format.parse(text);
                setValue(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return;
        }
        throw new java.lang.IllegalArgumentException(text);
    }
}
