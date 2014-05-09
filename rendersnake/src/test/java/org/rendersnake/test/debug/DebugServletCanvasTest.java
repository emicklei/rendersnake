package org.rendersnake.test.debug;

import java.io.IOException;
import java.io.StringWriter;

import org.rendersnake.test.MockHttpRequest;
import org.rendersnake.test.MockHttpResponse;
import org.rendersnake.test.PersonalPage;
import org.rendersnake.tools.DebugHtmlCanvas;

import junit.framework.TestCase;

public class DebugServletCanvasTest extends TestCase {

    public void testRenderCount() throws IOException {
        DebugHtmlCanvas html = new DebugHtmlCanvas(new MockHttpRequest(), new MockHttpResponse(), new StringWriter());
        html.render(new PersonalPage());
        assertEquals(3, html.renderCount);
    }

    public void testRenderCountDisabled() throws IOException {
        DebugHtmlCanvas html = new DebugHtmlCanvas(new MockHttpRequest(), new MockHttpResponse(), new StringWriter());
        html.enabled = false;
        html.render(new PersonalPage());
        assertEquals(0, html.renderCount);
    }
}
