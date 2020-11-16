package org.study.project.chonggou.test;



import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author chenyao
 * @date 2020/5/6 19:50
 * @description
 */
public class FIleReaderTestWithMyWay {
    InputStreamReader inputStream;

    @Before
    public void setUp(){
        try {
            inputStream = new FileReader("E:\\IDEA\\WorkSpace\\my-project\\study-project\\api-study\\src\\main\\java\\org\\study\\project\\chonggou\\test\\file.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file not exist");
        }
    }

    @After
    public void tearDown() throws Exception {
        if(inputStream!=null){
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("file close exception");
            }
        }
    }

    @Test
    public void read() throws IOException {
        char c = '&';
        for (int i = 0; i < 4; i++) {
            c = (char) inputStream.read();
        }
        Assert.assertEquals('c',c);
    }

}
