import com.storm.apigw.utils.ApiUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class MyTest {



    @Test
    public void test1() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        ApiUtils.invokeApi("getUsername",null);
    }
}
