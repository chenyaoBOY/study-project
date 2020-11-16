package fanxing;

import org.junit.Test;

/**
 * @author chenyao
 * @Description:
 * @date 2018/6/29/17:36
 */
public class FanXingTest {

    @Test
    public void test01(){
        ResultUtil<String> result = new ResultUtil<>();
        String data = result.getData();

        Result<FanXingTest> util = new ResultUtil<>();
        FanXingTest fanXingTest = (FanXingTest) util.getData();

    }
}
