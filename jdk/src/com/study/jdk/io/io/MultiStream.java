package com.study.jdk.io.io;

import java.io.IOException;
import java.io.OutputStream;

public class MultiStream extends OutputStream {
    private OutputStream o1,o2;
    public MultiStream(OutputStream o1,OutputStream o2){
        this.o1=o1;
        this.o2=o2;
    }
    @Override
    public void write(int b) throws IOException {
        o1.write(b);
        o2.write(b);
    }
}
