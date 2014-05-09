package org.rendersnake.test;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import static org.rendersnake.HtmlAttributesFactory.*;

public class Contents implements Renderable {

    public static final Contents INSTANCE = new Contents();
    
    @Override
    public void renderOn(HtmlCanvas html) throws IOException {
        for (int t=0;t<10;t++){
            html
            	.render(Title.INSTANCE)
                .div(align("left"))
                    .write("no need to escape this",false)
                ._div();
        }        
    }
}
