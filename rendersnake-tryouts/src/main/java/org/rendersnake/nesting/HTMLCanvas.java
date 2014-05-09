package org.rendersnake.nesting;

import static org.rendersnake.nesting.ElementFactory.*;

public class HTMLCanvas {
	public static void main(String[] args){
		HTMLCanvas c = new HTMLCanvas();		
		c.html(new Attributes(),
				div(new Attributes())
		);
	}
	public StringBuilder buffer = new StringBuilder();
	
	public void html(Attributes attributes,Element ... elements){
		attributes.buffer.append("<html>");
		for (Element each : elements) {
			each.appendTo(attributes.buffer);
		}
		attributes.buffer.append("</html>");
		buffer.append(attributes.buffer.toString());
	}
}
