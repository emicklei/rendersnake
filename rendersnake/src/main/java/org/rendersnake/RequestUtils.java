package org.rendersnake;

import org.rendersnake.internal.ContextMap;
/**
 * Helper class to access details of the Http request for which the HtmlCanvas was created.
 * @author ernest
 */
public class RequestUtils {

    /**
     * @param html
     * @return a ContextMap to access the parameters of the inbound Http request.
     */
    public static ContextMap getParameters(HtmlCanvas html) {
        return html.getPageContext().getContextMap(PageContext.REQUEST_PARAMETERS);
    }
    /**
     * @param html
     * @return aString | null which is the value of a parameter of the inbound Http request.
     */
    public static String getParameter(HtmlCanvas html, String key) {
        ContextMap map = getParameters(html);
        if (null == map) return null;
        return map.getString(key);
    }    
    /** 
     * @param html
     * @param key
     * @return an Integer | null which is the value of a parameter of the inbound Http request.
     */    
    public static Integer getIntegerParameter(HtmlCanvas html, String key) {
        String valueOrNull = getParameter(html, key);
        return valueOrNull == null || valueOrNull.length() == 0 ? null : Integer.parseInt(valueOrNull);
    }
    /** 
     * @param html
     * @param key
     * @return a Boolean | null which is the value of a parameter of the inbound Http request.
     */
    public static Boolean getBooleanParameter(HtmlCanvas html, String key) {
        String valueOrNull = getParameter(html, key);
        return valueOrNull == null || valueOrNull.length() == 0 ? null : Boolean.parseBoolean(valueOrNull);
    }    
    /**
     * 
     * @param html
     * @return a ContextMap to access the session associated to the inbound request.
     */
    public static ContextMap getSession(HtmlCanvas html) {
        return html.getPageContext().getContextMap(PageContext.SESSION);
    }
    /**
     * 
     * @param html
     * @param name
     * @return aString | null which is the value of a header of the inbound Http request.
     */
    public static String getHeaderValue(HtmlCanvas html, String name) {
        ContextMap map = html.getPageContext().getContextMap(PageContext.REQUEST_HEADERS);
        if (null == map) return null;
        return map.getString(name);
    }
    /**
     * 
     * @param html
     * @param name
     * @return a ContextMap to access the Cookies passed in by the inbound Http request.
     */
    public static ContextMap getCookies(HtmlCanvas html) {
        return html.getPageContext().getContextMap(PageContext.REQUEST_COOKIES);
    }
    /**
     * 
     * @param html
     * @return the actual path of the inbound Http request.
     */
    public static String getPath(HtmlCanvas html) {
        return html.getPageContext().getString(PageContext.REQUEST_PATH);
    }
    /**
     * Return the full undecoded URL.
     * @param html
     * @return
     */
    public static String getRequestURLAndQuery(HtmlCanvas html) {
        return html.getPageContext().getString(PageContext.REQUEST_URIQ);
    }
}