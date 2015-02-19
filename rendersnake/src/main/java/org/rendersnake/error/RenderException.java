package org.rendersnake.error;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import static org.rendersnake.HtmlAttributesFactory.*;
/**
 * RenderException is a RuntimeException that is thrown in the following cases
 * <ul>
 * <li>sending close() when there is no open tag
 * <li>sending close(someTag) where otherTag was expected
 * <li>general exception occurred when rendering a component
 * </ul>
 * 
 * @author ernest
 */
public class RenderException extends RuntimeException implements Renderable {    
    private static final long serialVersionUID = 5155772408981115672L;
    /**
     * 
     */
    public static final String KEY_PAGECONTEXT = "renderable.error";

    /**
     * 
     */
    public boolean isNullTag = false;
    /**
     * 
     */
    public boolean isEmptyStack = false;
    /**
     * 
     */
    public String expectedTag = null;
    /**
     * 
     */
    public String closingTag = null;
    /**
     * 
     */
    public Exception exception = null;

    /**
     * Return a new RenderException for the situation that a different tag is being close than expected.
     * This indicates that a required close tag was not sent.
     * @return
     */
    public static RenderException nullTag() {
        RenderException rex = new RenderException();
        rex.isNullTag = true;
        return rex;
    }
    /**
     * Return a new RenderException for the situation that a different tag is being close than expected.
     * This indicates that a required close tag was not sent.
     * @param expected , what should have been the close tag
     * @param actual , what the program is trying to close
     * @return
     */
    public static RenderException unexpectedTag(String expected, String actual) {
        RenderException rex = new RenderException();
        rex.expectedTag = expected;
        rex.closingTag = actual;
        return rex;
    }
    /**
     * Return a new RenderException for the situation that a component is trying to close an open tag when there are no open tags to close.
     * @return
     */
    public static RenderException emptyStack() {
        RenderException rex = new RenderException();
        rex.isEmptyStack = true;
        return rex;
    }
    /**
     * Return a new RenderException for the situation that an unkown problem was detected when rendering a component.
     * @param ex
     * @return
     */
    public static RenderException caught(Exception ex) {
        RenderException rex = new RenderException();
        rex.exception = ex;
        return rex;
    }    
    /**
     * Render the receiver on a html to provide debugging information about the exception.
     */
    public void renderOn(HtmlCanvas html) throws IOException {
        html.div(style("color:red"));
        if (isEmptyStack) {
            html.write("[Render Error] unexpected close()");
            return;
        }
        if (expectedTag != null) {
            // tag mismatch
            html.write(String.format("[Render Error] expected [%s] actual [%s]", expectedTag, closingTag));
        }
        if (exception != null) {
            // general error
            html.write(String.format("[Render Error] general exception [%s]", exception.getMessage()));
        }
        // if debugging, the error component is available
        Renderable errorComponent = (Renderable)(html.getPageContext().getObject("renderable.error"));
        if (errorComponent != null) {
            html.write(" in ").write(errorComponent.getClass().getName());
        } else {
            html.a(href("?inspect")).write(" Inspect details")._a();
            this.renderStackTraceOn(html);
        }
        html._div();
    }
    /**
     * Render information about the exception (if any)
     * @param html
     * @throws IOException
     */
    public void renderStackTraceOn(HtmlCanvas html) throws IOException { // @formatter:off
        if (null == exception) return;
        
        StringWriter sw = new StringWriter();
        PrintWriter buffer = new PrintWriter(sw);
        exception.printStackTrace(buffer);
        html.pre().write(sw.toString(), true)._pre();
        html.hr();
    }
    
    @Override
    public String toString() {
        return super.toString() 
           + "{expected=" + expectedTag 
           + ",actual=" + closingTag 
           + ",exception=" + exception
           + ",empty=" + isEmptyStack
           + ",null=" + isNullTag
           + "}";
    }
}
