package org.rendersnake;

import java.io.IOException;
import java.io.StringWriter;

import org.rendersnake.tag.ImageTag;

public class Canvas {

	public StringWriter out = new StringWriter();
	
	public ImageTag img() {
		return new ImageTag(out);
	}
	public ImageTag open_img() {
		return new ImageTag(out);
	}
	public ImageTag div() {
		return new ImageTag(out);
	}
	
	public ImageAttributes imgAttributes() {
	    return new ImageAttributes();
	}
	public HtmlAttributes variable(String formVariable, String currentValue) throws IOException {
	    return new HtmlAttributes().name("_rs_"+formVariable).value(currentValue);
	}
}
