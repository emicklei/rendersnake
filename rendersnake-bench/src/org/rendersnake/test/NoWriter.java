package org.rendersnake.test;

import java.io.IOException;
import java.io.Writer;

public class NoWriter extends Writer{

    public long written = 0;
    
    @Override
    public void close() throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void flush() throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void write(char[] arg0, int arg1, int arg2) throws IOException {
        written += arg2 - arg1;
    }    
}
