package org.rendersnake.tools;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.RenderableWrapper;
import org.rendersnake.error.RenderException;

import static org.rendersnake.HtmlAttributesFactory.*;
/**
 * Inspector can display debugging information for a component.
 * 
 * @author ernestmicklei
 *
 */
public class Inspector extends RenderableWrapper {

	public Renderable target;

	public Inspector(Renderable toInspect) {
	    super(toInspect);
		this.target = toInspect;
	}

	public static void renderCssOn(HtmlCanvas html) throws IOException {// @formatter:off
		html
			.style(type("text/css"))
			.write(".rendersnake-inspector { border: 1px solid red }")
			._style();
	}
	// @formatter:on
	
	public void renderOn(HtmlCanvas html) throws IOException {// @formatter:off
	    // no need to inspect an exception
	    if (target instanceof RenderException) {
	        target.renderOn(html);
	        return;
	    }
		html
			.div(class_("rendersnake-inspector"))
			.write(target.getClass().getName());
		html.getPageContext().renderForInpectorOn(this,html);
		target.renderOn(html);
		html._div();
	}
	// @formatter:on
}
