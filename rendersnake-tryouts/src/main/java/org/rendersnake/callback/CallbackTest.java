package org.rendersnake.callback;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;
import org.rendersnake.HtmlCanvas;

public class CallbackTest {
    
    private String who;
    @Test
    public void test() throws Exception {
        
        Callback call = this.helpClicked();
        HtmlCanvas html = new HtmlCanvas();
        html.getPageContext().withString("who","me");
        call.respondOn(html);
        Assert.assertEquals("me", who);
        
        //html.a(onClick(this.helpClicked()));
    }
    private Callback helpClicked() {
        return new Callback() {            
            public void respondOn(HtmlCanvas html) throws IOException {
                CallbackTest.this.who = html.getPageContext().getString("who");
            }
        };
    }
}
