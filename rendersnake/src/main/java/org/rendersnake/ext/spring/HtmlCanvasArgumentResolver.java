package org.rendersnake.ext.spring;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.PageContext;
import org.rendersnake.ext.servlet.RequestHeadersMap;
import org.rendersnake.ext.servlet.RequestParametersMap;
import org.rendersnake.ext.servlet.SessionAttributesMap;
import org.rendersnake.internal.SimpleContextMap;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
/**
 * HtmlCanvasArgumentResolver is a Spring Helper that is used
 * to prepare html arguments before invoking a Controller method.
 * <p><pre>
 * {@code
 * <!-- This Resolver exists for renderSnake HtmlCanvas parameter processing -->
 * <bean class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
 *   <property name="customArgumentResolver">
 *     <bean class="org.rendersnake.ext.spring.HtmlCanvasArgumentResolver">
 *     </bean>
 *   </property>
 * </bean>} 
 * </pre></p>
 * @author e.micklei
 **/
public class HtmlCanvasArgumentResolver implements WebArgumentResolver {

	/* (non-Javadoc)
	 * @see org.springframework.web.bind.support.WebArgumentResolver#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.context.request.NativeWebRequest)
	 */
	public Object resolveArgument(MethodParameter methodParameter,
			NativeWebRequest webRequest) throws Exception {
	
		// only accept HtmlCanvas typed parameter
		if (methodParameter.getParameterType() != HtmlCanvas.class)
			return UNRESOLVED;
		
		HttpServletRequest request = (HttpServletRequest)(webRequest.getNativeRequest());
		HttpServletResponse response = (HttpServletResponse)(webRequest.getNativeResponse());
		response.setContentType("text/html"); // controllers may override this
        final HtmlCanvas canvas = HtmlCanvasFactory.createCanvas(request,response,response.getWriter());        
		setupPageContext(request, canvas.getPageContext());                
		return canvas;
	}

    static void setupPageContext(HttpServletRequest request, PageContext context) {
        String q = request.getQueryString();
        context.withObject(PageContext.REQUEST_PATH, request.getPathTranslated());
        context.withObject(PageContext.REQUEST_URIQ, request.getRequestURI() + (q == null ? "" : "?" + q));
        context.withObject(PageContext.SESSION, new SessionAttributesMap(request));
		context.withObject(PageContext.REQUEST_PARAMETERS, new RequestParametersMap(request));	
        context.withObject(PageContext.REQUEST_HEADERS, new RequestHeadersMap(request));
    }
}
