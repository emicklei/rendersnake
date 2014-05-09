package org.rendersnake;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import junit.framework.TestCase;

public class CanvasTest extends TestCase {

    private static ThreadLocal<StringBuilder> threadLocalBuilder = new ThreadLocal<StringBuilder>() {
        @Override
        protected StringBuilder initialValue() {
            return new StringBuilder(64);
        }

        @Override
        public StringBuilder get() {
            StringBuilder b = super.get();
            b.setLength(0); // clear/reset the buffer
            return b;
        }

    };

    public void testImageTag() {
        Canvas c = new Canvas();
        c.open_img().src("plaatje.png").alt("plaatje").close_img();

    }

    public void testImageTag2() {
        Canvas c = new Canvas();
        c.img().src("plaatje.png").alt("plaatje").div().text("txt")._()._();
    }

    public void testBuilder() throws IOException {
        long now = System.currentTimeMillis();
        Writer pw = new PrintWriter(new StringWriter());
        for (int i = 0; i < 100000; i++) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Hello World, Here I comeHello World, Here I comeHello World, Here I come");
            pw.append(sb.toString());
        }
        long ms = System.currentTimeMillis();
        System.out.println("1=" + (ms - now));
    }

    public void testBuilder2() throws IOException {
        long now = System.currentTimeMillis();
        Writer pw = new PrintWriter(new StringWriter());
        for (int i = 0; i < 100000; i++) {
            StringBuilder sb = new StringBuilder(64);
            sb.append("Hello World, Here I comeHello World, Here I comeHello World, Here I come");
            pw.append(sb.toString());
        }
        long ms = System.currentTimeMillis();
        System.out.println("2=" + (ms - now));
    }

    public void testBuilder3() throws IOException {
        long now = System.currentTimeMillis();
        Writer pw = new PrintWriter(new StringWriter());
        for (int i = 0; i < 100000; i++) {
            StringBuilder sb = new StringBuilder(256);
            sb.append("Hello World, Here I comeHello World, Here I comeHello World, Here I come");
            pw.append(sb);
        }
        long ms = System.currentTimeMillis();
        System.out.println("3=" + (ms - now));
    }
    public void testBuilder4() throws IOException {
        long now = System.currentTimeMillis();
        Writer pw = new PrintWriter(new StringWriter());
        for (int i = 0; i < 100000; i++) {
            StringBuilder sb = threadLocalBuilder.get();
            sb.append("Hello World, Here I comeHello World, Here I comeHello World, Here I come");
            pw.append(sb);
        }
        long ms = System.currentTimeMillis();
        System.out.println("4=" + (ms - now));
    }    
}
