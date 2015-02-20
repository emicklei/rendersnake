package org.rendersnake.ext.jquery;

import java.io.IOException;

/**
 * Use org.rendersnake.HtmlAttributesFactory instead.
 * @author ernest
 *
 */
@Deprecated
public class JQueryAttributesFactory {

    /**
     * @param enabled
     * @return
     * @throws IOException
     */
    public static JQueryAttributes dataAjax(boolean enabled) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-ajax", Boolean.toString(enabled),false);
    }    
    
    /**
     * @param role
     * @return
     * @throws IOException
     */
    public static JQueryAttributes dataRole(String role) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-role", role);
    }

    /**
     * @param icon
     * @return
     * @throws IOException
     */
    public static JQueryAttributes dataIcon(String icon) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-icon", icon);
    }

    /**
     * @param type
     * @return
     * @throws IOException
     */
    public static JQueryAttributes dataType(String type) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-type", type);
    }

    /**
     * @param inline
     * @return
     * @throws IOException
     */
    public static JQueryAttributes dataInline(boolean inline) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-inline", String.valueOf(inline));
    }

    /**
     * @param collapsed
     * @return
     * @throws IOException
     */
    public static JQueryAttributes dataCollapsed(boolean collapsed) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-collapsed", String.valueOf(collapsed));
    }

    /**
     * @param theme
     * @return
     * @throws IOException
     */
    public static JQueryAttributes dataTheme(String theme) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-theme", theme);
    }

    /**
     * @param inset
     * @return
     * @throws IOException
     */
    public static JQueryAttributes dataInset(boolean inset) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-inset", Boolean.toString(inset));
    }

    /**
     * @param direction
     * @return
     * @throws IOException
     */
    public static JQueryAttributes dataDirection(String direction) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-direction", direction, false);
    }
    
    /**
     * @param xy
     * @return
     * @throws IOException
     */
    public static JQueryAttributes dataScroll(String xy) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-scroll", xy, false);
    }

    /**
     * @param rel
     * @return
     * @throws IOException
     */
    public static JQueryAttributes dataRel(String rel) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-rel", rel, false);
    }

    /**
     * @param inline
     * @return
     * @throws IOException
     */
    public static JQueryAttributes dataInline(String inline) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-inline", inline, false);
    }     
    /**
     * @param isMini
     * @return
     * @throws IOException
     */
    public static JQueryAttributes dataMini(boolean isMini) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-mini", Boolean.toString(isMini), false);
    }        
    // fixed, inline
    /**
     * @param dataPosition
     * @return
     * @throws IOException
     */
    public static JQueryAttributes dataPosition(String dataPosition) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-position", dataPosition, false);
    }    
    /**
     * @param dataContentTheme
     * @return
     * @throws IOException
     */
    public static JQueryAttributes dataContentTheme(String dataContentTheme) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-content-theme", dataContentTheme, false);
    }    
}
