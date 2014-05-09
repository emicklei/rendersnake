package org.rendersnake;

import static org.rendersnake.HtmlAttributesFactory.id;

import java.io.IOException;

public class SamplePage implements Renderable {

	public void renderOn(HtmlCanvas canvas) throws IOException {

		// use id(String) without inheritance from HtmlComponent
		
		canvas.div(id("mine"))._div();
		
		// each tag has its own HtmlAttributes class
		
		// canvas.img(canvas.imgAttributes().src("hier.png"));
				
        // canvas.img(new ImageAttributes().src("hier.png").alt("here"));

        // canvas.img(HtmlAttributes.img().src("hier.png").alt("here"));		
	}

}
