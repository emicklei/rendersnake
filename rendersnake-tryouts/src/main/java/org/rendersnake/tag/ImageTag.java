package org.rendersnake.tag;

import java.io.StringWriter;

import org.rendersnake.Canvas;

public class ImageTag {
	private StringWriter out;
	
	public ImageTag(StringWriter out) {
		this.out = out;
	}
	public ImageTag src(String src) {
		return this;
	}
	public ImageTag alt(String alt) {
		return this;
	}
	public Canvas close_img() {
		return null;
	}
	public ImageTag div() {
	    return this;
	}
    public ImageTag text(String s) {
        return this;
    }
    public ImageTag _() { return this; }
}	
