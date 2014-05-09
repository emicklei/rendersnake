package org.rendersnake;

import org.rendersnake.js.Function;
import org.rendersnake.js.Statement;

import static org.rendersnake.jquery.JQueryCanvasFactory.*;
import junit.framework.TestCase;

public class FunctionTest extends TestCase {

    public void testSimple(){        
        Function f = new Function("arg");
        f.add($("this").addClass("hide"));
        System.out.println(f);
    }

    public void testSimple2(){        
        Function f = new Function("arg");
        f.name = "test";
        f.add($("this").addClass("hide"));
        f.add(new Statement("alert('hello')"));
        f.returns = "1+2";
        
        System.out.println(f);
    }

    
}
