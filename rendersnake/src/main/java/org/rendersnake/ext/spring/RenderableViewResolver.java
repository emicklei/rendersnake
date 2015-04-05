package org.rendersnake.ext.spring;

import java.util.Locale;

import org.rendersnake.Renderable;
import org.rendersnake.ext.spring.template.TemplateAnnotationResolver;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.RedirectView;
/**
 * RenderableViewResolver is a view resolver that does a lookup for
 * a Renderable component in the application context.
 * If such a component is found then create a new RenderableView to
 * perform the actual rendering on a HtmlCanvas.
 *
 * Usage in servlet-config.xml
 * <p>
 * {@code
 * <bean class="org.rendersnake.ext.spring.RenderableViewResolver" /> 
 * }
 * </p>
 * 
 * @author e.micklei
 */
public class RenderableViewResolver implements ViewResolver, ApplicationContextAware {

    private ApplicationContext appCtx;
    private String contentType = "text/html; charset=UTF-8";

    /**
     * Find the Renderable component in the application context.
     * Return a RenderableView on that component or null if no bean is found.
     * 
     * @throws Exception
     * @return a RenderableView | null
     */
    public View resolveViewName(String viewName, Locale locale) throws Exception {
    	
    	// Key-word use in Spring controller for redirect url
    	if (viewName.startsWith("redirect:")) {
    		final String url = viewName.replace("redirect:", "");
    		return new RedirectView(url);
    	}

        final Renderable component = appCtx.getBean(viewName, Renderable.class);
        if (component == null) return null;
        
        // Apply template on component define by @Template annotation
        final Renderable decorateComponent = TemplateAnnotationResolver.compute(component);
        
        return new RenderableView(decorateComponent, this.contentType);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appCtx = applicationContext;
    }

    /**
     * @param contentType
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}