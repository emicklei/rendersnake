package org.rendersnake.site.components;

import static org.rendersnake.HtmlAttributesFactory.class_;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.site.SiteConstants;

public class SourceLink implements Renderable {

    public String url;
    public String title = "Full Source";
    
    public static SourceLink example(String className) {
        SourceLink link = new SourceLink();
        link.url = SiteConstants.SVN_BASE_EXAMPLE + className + ".java";
        return link;
    }
    
    public static SourceLink site(String className, String title) {
        SourceLink link = new SourceLink();
        link.title = title;
        link.url = SiteConstants.SVN_BASE_SITE + className + ".java";
        return link;
    }    
    
    public static SourceLink folder(String packageName, String title) {
        SourceLink link = new SourceLink();
        link.title = title;
        link.url = "http://code.google.com/p/rendersnake/source/browse/#svn/trunk/rendersnake-site/src/main/java/"
            + (packageName.replaceAll("\\.", "/"));
        return link;
    }
    
    public void renderOn(HtmlCanvas html) throws IOException {
        html.p()
            .a(class_("sourcelink").href(url).target("_new")).write(title)._a()
            ._p();        
    }

}
