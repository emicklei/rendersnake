package org.rendersnake.tag;

public class Tag {
    String open;
    String close;
    HtmlCanvas canvas;
    
    public Tag(HtmlCanvas canvas) { 
        this.canvas = canvas;
    }
    protected void init(String o,String c) {
        open = o;
        close = c;
        canvas.write(o);
    }
    public static class Body extends Tag {
        public Body(HtmlCanvas canvas){ 
            super(canvas);
            this.init("<body>","</body>"); 
        }
        public Table table() { return new Table(canvas); }
        public Body write(String s) { canvas.write(s); return this;}
    }
    public static class Table extends Tag {
        public Table(HtmlCanvas canvas){ 
            super(canvas);
            this.init("<table>","</table>"); 
        }
        public TR tr(){ return new TR(canvas); }
    }    
    public static class TR extends Tag {
        public TR(HtmlCanvas canvas){ 
            super(canvas);
            this.init("<tr>","</tr>"); 
        }
        public TD td(){ return new TD(canvas); }
    }        
    public static class TD extends Body {
        public TD(HtmlCanvas canvas){ 
            super(canvas);
            this.init("<td>","</td>");
        }
    }    
}
