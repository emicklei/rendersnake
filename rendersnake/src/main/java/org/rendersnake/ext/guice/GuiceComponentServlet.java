package org.rendersnake.ext.guice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.PageContext;
import org.rendersnake.Renderable;
import org.rendersnake.ext.servlet.PostHandler;
import org.rendersnake.ext.servlet.RequestHeadersMap;
import org.rendersnake.ext.servlet.RequestParametersMap;
import org.rendersnake.ext.servlet.SessionAttributesMap;
import org.rendersnake.ext.spring.HtmlCanvasFactory;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Singleton;
/**
 * GuiceComponentServlet is used to serve RenderSnake components.
 * Each such component must be annotation with @Named in order to pickup here.
 *
 * Usage
 * <pre>
  new ServletModule(){              
       protected void configureServlets() {
           serve("/web/*").with(GuiceComponentServlet.class);
           // list all page classes here
           bind(HomePage.class);
       }
   }
 * </pre>         
 * 
 * 
 * @author emicklei
 */
@Singleton
public class GuiceComponentServlet extends HttpServlet {
    private static final long serialVersionUID = -5260542693799682553L;
    private static final Logger LOG = Logger.getLogger("org.rendersnake.ext.guice");
    
    /**
     * Injector needed for initialization of the mapping.
     */
    @Inject
    private Injector injector;   
    
    /**
     * Cached map of uri paths to Renderable components.
     */
    private Map<String,Renderable> dispatchMap = new HashMap<String,Renderable>();
    /**
     * Cached map of uri paths to FormHandler components.
     */
    private Map<String,PostHandler> handlerMap = new HashMap<String,PostHandler>();
    
    /**
     * Dispatch an incoming request to a Renderable component
     * by looking for a named component that matches the path.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getPathInfo();
        Renderable component = this.dispatchMap.get(uri);
        if (component == null) {
            response.sendError(404);
        } else {
            HtmlCanvas canvas = HtmlCanvasFactory.createCanvas(request,response,response.getWriter());
            PageContext context = canvas.getPageContext();
            String q = request.getQueryString();
            context.withObject(PageContext.REQUEST_PATH, request.getPathTranslated());
            context.withObject(PageContext.REQUEST_URIQ, request.getRequestURI() + (q == null ? "" : "?" + q));
            context.withObject(PageContext.SESSION, new SessionAttributesMap(request));
            context.withObject(PageContext.REQUEST_PARAMETERS, new RequestParametersMap(request));  
            context.withObject(PageContext.REQUEST_HEADERS, new RequestHeadersMap(request));
            canvas.render(component);          
        }
    }

    /**
     * Dispatch an incoming request to a PostHandler component
     * by looking for a named component that matches the path.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getPathInfo();
        PostHandler handler = this.handlerMap.get(uri);
        if (handler == null) {
            resp.sendError(404);
        } else {
            handler.handlePost(req, resp);
        }
    }    
    /**
     * Initializes the mapping between path info and Renderable components.
     * It is implemented by inspecting all bindings of the injector.
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        for (Key<?> key : injector.getBindings().keySet()) {
            Type type = key.getTypeLiteral().getType();
            if (type instanceof Class<?>) {
                Class<?> klass = (Class<?>) type;
                if (Renderable.class.isAssignableFrom(klass)) {
                    Renderable p = (Renderable)injector.getInstance(klass);
                    Named annotation = klass.getAnnotation(Named.class);
                    if (annotation != null) {
                        dispatchMap.put(annotation.value(), p);
                        LOG.info("Binding component:"+klass+" to:" + annotation);
                    } else {
                        LOG.severe("Missing @Named annotation in component:"+klass);
                    }
                }
                if (PostHandler.class.isAssignableFrom(klass)) {
                    PostHandler h = (PostHandler)injector.getInstance(klass);
                    Named annotation = klass.getAnnotation(Named.class);
                    if (annotation != null) {
                        handlerMap.put(annotation.value(), h);
                        LOG.info("Binding component:"+klass+" to:" + annotation);
                    } else {
                        LOG.severe("Missing @Named annotation in component:"+klass);
                    }
                }                
            }
        }                
        super.init(config);
    }
}
