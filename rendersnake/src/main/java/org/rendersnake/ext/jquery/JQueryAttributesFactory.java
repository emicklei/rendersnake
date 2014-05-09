package org.rendersnake.ext.jquery;

import java.io.IOException;

/**
 * Use org.rendersnake.HtmlAttributesFactory instead.
 * @author ernest
 *
 */
@Deprecated
public class JQueryAttributesFactory {

    public static JQueryAttributes dataAjax(boolean enabled) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-ajax", Boolean.toString(enabled),false);
    }    
    
    public static JQueryAttributes dataRole(String role) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-role", role);
    }

    public static JQueryAttributes dataIcon(String icon) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-icon", icon);
    }

    public static JQueryAttributes dataType(String type) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-type", type);
    }

    public static JQueryAttributes dataInline(boolean inline) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-inline", String.valueOf(inline));
    }

    public static JQueryAttributes dataCollapsed(boolean collapsed) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-collapsed", String.valueOf(collapsed));
    }

    public static JQueryAttributes dataTheme(String theme) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-theme", theme);
    }

    public static JQueryAttributes dataInset(boolean inset) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-inset", Boolean.toString(inset));
    }

    public static JQueryAttributes dataDirection(String direction) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-direction", direction, false);
    }
    
    public static JQueryAttributes dataScroll(String xy) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-scroll", xy, false);
    }

    public static JQueryAttributes dataRel(String rel) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-rel", rel, false);
    }

    public static JQueryAttributes dataInline(String inline) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-inline", inline, false);
    }     
    public static JQueryAttributes dataMini(boolean isMini) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-mini", Boolean.toString(isMini), false);
    }        
    // fixed, inline
    public static JQueryAttributes dataPosition(String dataPosition) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-position", dataPosition, false);
    }    
    public static JQueryAttributes dataContentTheme(String dataContentTheme) throws IOException {
        return (JQueryAttributes) new JQueryAttributes().add("data-content-theme", dataContentTheme, false);
    }    
}
