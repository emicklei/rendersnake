package org.rendersnake;

import org.rendersnake.interfaces.Canvas;

import junit.framework.TestCase;

public class InterfacesTest extends TestCase {

	public void test(){
		Canvas canvas = new Canvas();
		canvas
			.html()
				.body()
					.write("Hi")
					.div()
						.write("Hello")
					._div()
				._body()
			._html();
	}
}