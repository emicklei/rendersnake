package org.rendersnake.jquery;

public class JQueryCanvasFactory {
    /**
     * Create a new JQueryCanvas to compose expressions.
     * @param selector , A string containing a selector expression
     * @see {@link http://api.jquery.com/jQuery/}
     * @return a new JQueryCanvas
     */
    public static JQueryCanvas $(String selector) {
        return new JQueryCanvas().jQuery(selector);
    }
    /**
     * Create a new JQueryCanvas to compose expressions.
     * @param selector , A string containing a selector expression
     * @param context , A DOM Element, Document, or jQuery to use as context
     * @see {@link http://api.jquery.com/jQuery/}
     * @return a new JQueryCanvas
     */
    public static JQueryCanvas $(String selector, String context) {
        return new JQueryCanvas().jQuery(selector,context);
    }    

    public static JQueryCanvas $() {
        return new JQueryCanvas().jQuery();
    }
}
