package org.rendersnake.js;

import java.util.ArrayList;
import java.util.List;

import org.rendersnake.ToJavascript;



public class Function implements ToJavascript {
    public String name;
    public String returns;
    List<String> arguments = new ArrayList<String>();
    List<ToJavascript> statements = new ArrayList<ToJavascript>();
    
    public Function(String arg) {
        arguments.add(arg);
    }
    
    public String toJavascript() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public Function add(ToJavascript js) {
        statements.add(js);
        return this;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("function");
        if (name != null) {
            sb.append(' ').append(name);
        }
        sb.append('(');
        for (int i=0;i<arguments.size();i++) {
            if (i>0) sb.append(',');
            sb.append(arguments.get(i));
        }
        sb.append("){");
        for (ToJavascript each : statements) {
            sb.append(each.toJavascript());
        }
        if (returns != null) {
            sb.append("return ").append(returns).append(';');
        }
        sb.append("}");
        return sb.toString();
    }
}
