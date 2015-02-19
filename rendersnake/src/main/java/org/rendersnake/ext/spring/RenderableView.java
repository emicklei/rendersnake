package org.rendersnake.ext.spring;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.springframework.web.servlet.View;
/**
 * RenderableView is used together with the RenderableViewResolver
 * to render a component with context data available through the model. 
 * 
 * @author e.micklei
 */
public class RenderableView implements View {

    Renderable component;
    String contentType;

    /**
     * @param render
     * @param contentType
     */
    public RenderableView(Renderable render, String contentType) {
        this.component = render;
        this.contentType = contentType;
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.View#getContentType()
     */
    public String getContentType() {
        return contentType;
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.View#render(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HtmlCanvas html = HtmlCanvasFactory.createCanvas(request, response, response.getWriter());
        HtmlCanvasArgumentResolver.setupPageContext(request, html.getPageContext());
        html.getPageContext().attributes.putAll(model);
        html.render(component);
    }
}
