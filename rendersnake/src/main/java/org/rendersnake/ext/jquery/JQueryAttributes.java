package org.rendersnake.ext.jquery;

import java.io.IOException;

import org.rendersnake.HtmlAttributes;
/**
 * Use org.rendersnake.HtmlAttributesFactory instead.
 * @author ernest
 *
 */
@Deprecated
public class JQueryAttributes extends HtmlAttributes {

    public JQueryAttributes() {
        super();
    }

    public JQueryAttributes(String key, String value) {
        super(key, value);
    }

    public JQueryAttributes dataTheme(String dataTheme) {
        return (JQueryAttributes) super.add("data-theme", dataTheme, false);
    }

    // fixed, inline
    public JQueryAttributes dataPosition(String dataPosition) {
        return (JQueryAttributes) super.add("data-position", dataPosition, false);
    }

    public JQueryAttributes dataNoBackButton(boolean noBackButton) {
        return (JQueryAttributes) super.add("data-nobackbtn", String.valueOf(noBackButton), false);
    }

    // pop, slide, slideup, slidedown, pop, fade, flip
    public JQueryAttributes dataTransition(String transition) {
        return (JQueryAttributes) super.add("data-transition", transition, false);
    }

    // dialog
    public JQueryAttributes dataRel(String rel) {
        return (JQueryAttributes) super.add("data-rel", rel, false);
    }

    // gear, check, delete
    public JQueryAttributes dataIcon(String icon) {
        return (JQueryAttributes) super.add("data-icon", icon, false);
    }

    public JQueryAttributes dataIconPos(String pos) {
        return (JQueryAttributes) super.add("data-iconpos", pos, false);
    }

    // xhtml(5) http://stackoverflow.com/questions/1678238/what-is-xhtmls-role-attribute-what-do-you-use-it-for
    public HtmlAttributes role(String role) {
        return (HtmlAttributes) super.add("role", role, false);
    }

    public JQueryAttributes dataInset(boolean inset) {
        return (JQueryAttributes) super.add("data-inset", String.valueOf(inset), false);
    }

    public JQueryAttributes dataInline(boolean inline) {
        return (JQueryAttributes) super.add("data-inline", String.valueOf(inline), false);
    }

    public JQueryAttributes dataRole(String role) {
        return (JQueryAttributes) super.add("data-role", role, false);
    }

    public JQueryAttributes dataCollapsed(boolean collapsed) {
        return (JQueryAttributes) super.add("data-collapsed", String.valueOf(collapsed), false);
    }
    
    public JQueryAttributes dataType(String type) {
        return (JQueryAttributes) super.add("data-type", type, false);
    }
    // reverse
    // deprecated?
    public JQueryAttributes dataDirection(String direction) {
        return (JQueryAttributes) super.add("data-direction", direction, false);
    }  
    public JQueryAttributes dataScroll(String xy) {
        return (JQueryAttributes) super.add("data-scroll", xy, false);
    }
    public JQueryAttributes dataInline(String inline) {
        return (JQueryAttributes) super.add("data-inline", inline, false);
    } 
    public JQueryAttributes dataMini(boolean isMini) {
        return (JQueryAttributes) super.add("data-mini", Boolean.toString(isMini), false);
    }     
    public JQueryAttributes dataContentTheme(String dataContentTheme) {
        return (JQueryAttributes) super.add("data-content-theme", dataContentTheme, false);
    }  
    public JQueryAttributes dataAjax(boolean enabled) {
        return (JQueryAttributes) super.add("data-ajax", Boolean.toString(enabled), false);
    }  
}
