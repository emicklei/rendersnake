package org.rendersnake.js;

import org.rendersnake.ToJavascript;


public class Statement implements ToJavascript {

    public String content;
    
    public Statement(String statement) {
        this.content = statement;
    }
    
    public String toJavascript() {
        return content + ";";
    }
}
