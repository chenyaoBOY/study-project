package com.exception.util;

import static org.junit.Assert.assertTrue;

import com.exception.util.response.UserResponseEnum;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {

        Integer userId =null;
        UserResponseEnum.USER_NOT_EXIST.assertNotNull(userId);
        UserResponseEnum.USER_NOT_EXIST.assertNotNull(userId,"时间{0}日期{1}操作人{2}","22点43分","2020年5月4日","陈瑶");

    }
}
