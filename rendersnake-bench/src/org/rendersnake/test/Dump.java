package org.rendersnake.test;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;

public class Dump {
    public static void main(String[] args) throws IOException {
        HtmlCanvas html = new HtmlCanvas();
        html.render(HomePage.INSTANCE);
        System.out.println(html.toHtml());
    }
}
