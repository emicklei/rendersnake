package org.rendersnake.nesting;


public class ElementFactory {
	public static Element div(Attributes attributes, Element ... elements) {
		attributes.buffer.append("div");
		for (Element each : elements) {
			each.appendTo(attributes.buffer);
		}
		attributes.buffer.append("/div");
		return null;
	}
}
