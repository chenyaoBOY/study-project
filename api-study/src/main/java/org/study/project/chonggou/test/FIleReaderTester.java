package org.study.project.chonggou.test;



import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author chenyao
 * @date 2020/5/6 19:50
 * @description
 */
public class FIleReaderTester extends TestCase {
    InputStreamReader inputStream;

    public FIleReaderTester(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        try {
            inputStream = new FileReader("E:\\IDEA\\WorkSpace\\my-project\\study-project\\api-study\\src\\main\\java\\org\\study\\project\\chonggou\\test\\file.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file not exist");
        }
    }

    @Override
    protected void tearDown() throws Exception {
        if(inputStream!=null){
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("file close exception");
            }
        }
    }

    public void testRead() throws IOException {
        char c = '&';
        for (int i = 0; i < 4; i++) {
            c = (char) inputStream.read();
        }
        assertEquals('d',c);
    }
    public void testReadLast() throws IOException {
        char c = '&';
        for (int i = 0; i < 140; i++) {
            c = (char) inputStream.read();
        }
        assertEquals('1',c);
        assertEquals('6',c);
    }

    public static Test suite(){
        TestSuite testSuite = new TestSuite();
        testSuite.addTest(new FIleReaderTester("read"));
        testSuite.addTest(new FIleReaderTester("readLast"));
        return testSuite;
    }

    public static void main(String[] args) {

//        TestRunner.run(suite());
        TestRunner.run(new TestSuite(FIleReaderTester.class));
    }
}
