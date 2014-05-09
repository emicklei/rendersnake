package org.rendersnake.tag;

import org.rendersnake.tag.Tag.Body;

public class HtmlCanvas {
    
    public static void main(String[] args) {
        HtmlCanvas c = new HtmlCanvas();
        new Body(c)
            .table()
                .tr()
                    .td()
                        .write("42");
    }
    public void write(String s) {
        System.out.print(s);
    }
}
