package org.rendersnake.internal;

import java.io.IOException;
import java.io.Writer;
/**
 * WriteBuffer is like a StringBuilder but adds the ability to transfer its characters to a io.Writer
 * 
 * @author emicklei
 */
public class WriteBuffer extends Writer {
    private char[] buffer;
    private int begin = 0;
    private int end = -1;

    /**
     * 
     */
    public WriteBuffer() {
        this(64);
    }

    /**
     * @param initialCapacity
     */
    public WriteBuffer(int initialCapacity) {
        buffer = new char[initialCapacity];
    }

    /**
     * 
     */
    public void reset() {
        this.begin = 0;
        this.end = -1;
    }
    
    @Override
    public WriteBuffer append(char ch) {
        if (begin == buffer.length)
            this.grow();
        this.buffer[this.begin] = ch;
        this.begin++;
        this.end++;
        return this;
    }

    /**
     * @param s
     */
    public void append(String s) {
        int l = s.length();
        while (begin + l > buffer.length)
            this.grow();
        s.getChars(0, l, this.buffer, this.begin);
        this.begin += l;
        this.end += l;
    }

    /**
     * @param integer
     */
    public void append(int integer) {
        this.append(String.valueOf(integer));
    }

    public String toString() {
        if (this.end == -1)
            return "";
        return new String(this.buffer, 0, this.end + 1);
    }

    private void grow() {
        char[] newbuffer = new char[buffer.length * 2];
        System.arraycopy(buffer, 0, newbuffer, 0, buffer.length);
        buffer = newbuffer;
    }

    /**
     * @return
     */
    public int length() {
        return end + 1;
    }

    /**
     * @param writer
     * @throws IOException
     */
    public void writeCharsOn(Writer writer) throws IOException {
        writer.write(buffer, 0, this.end + 1);
    }

	@Override
	public void close() throws IOException {}

	@Override
	public void flush() throws IOException {}

	@Override
	public void write(char[] charArray, int from, int to) throws IOException {
		for (int i=from;i<to;i++) this.append(charArray[i]);		
	}
}
