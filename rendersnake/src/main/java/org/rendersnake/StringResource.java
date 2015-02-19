package org.rendersnake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * StringResource holds a cache for text content such as Html snippets that is
 * read from resources on the classpath.
 * <p>
 * A StringResource instance encapsulates such a content for rendering on a
 * html canvas.
 * <p>
 * Examples
 * </p>
 * 
 * <pre>
 * html.text(StringResource.get(&quot;content/Introduction.html&quot;), false);
 * html.render(new StringResource(&quot;content/Introduction.html&quot;));
 * </pre>
 * 
 * @author e.micklei
 */
public class StringResource implements Renderable {
    /**
     * Cache of content read from resources on the classpath. Unlimited size; no eviction or expiration.
     */
    private final static Map<String, String> CACHE = new ConcurrentHashMap<String, String>();
    /**
     * Intention revealing constant to emphasize that caching for an item is not needed
     */
    public final static boolean DO_NOT_CACHE = false;
    /**
     * Cached value for a given location (by constructor)
     */
    private String content;
    /**
     * If true then the content will be HTML-escaped when writing on a html
     */
    private boolean escapeNeeded = false;
    /**
     * Create a StringResource with the cached value read from the resource location
     * @param location
     */
    public StringResource(String location) {
        this.content = get(location);
    }
    /**
     * Create a StringResource with the cached value read from the resource location.
     * @param location
     * @param escapeNeeded
     */
    public StringResource(String location, boolean escapeNeeded) {
        this.content = get(location);
        this.escapeNeeded = escapeNeeded;
    }
    /**
     * Access the String contents from the given resource location.
     * The contents is cached by StringResource. Use get(location,false); for non-cached access.
     * @param location
     * @return resource contents , never null
     */
    public static String get(String location) {
        return get(location, true);
    }
    /**
     * Forget about the cached resource at the location. Ignore if missing.
     * @param location
     */
    public static void flush(String location) {
        CACHE.remove(location);
    }
    /**
     * Forget about all cached resources.
     */
    public static void flush() {
        CACHE.clear();    }
    /**
     * Access the String contents from the given resource location.
     * @param location
     * @param doCache 
     * @return resource contents , never null
     */
    public static String get(String location, boolean doCache) {
        String content = null;
        if (doCache) {
            content = CACHE.get(location);
        }
        if (content == null) {
            content = contentOrNull(location);
            if (content == null) {
                content = "[StringResource] Missing or error reading resource:" + location;
            } else {
                if (doCache) {
                    CACHE.put(location, content);
                }
            }
        }
        return content;
    }    
    /**
     * Return a StringResource for rendering the text available at <code>location</code>
     * @param location , the file on the classpath containing the resource text 
     * @return a new StringResource
     */
    public static StringResource valueOf(String location) {
        return new StringResource(location);
    }
    /**
     * Use the resource location to read the String contents.
     * @param location
     * @return String | null if resource is not available
     */
    @SuppressWarnings("PMD.EmptyCatchBlock")
    private static String contentOrNull(String location) {
        if (location.startsWith("http://")) {
            return fetchContentOrNull(location);
        }
        String content = null;
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(location);
        if (is == null) {
        	// retry different loader
        	is = StringResource.class.getResourceAsStream(location);
        }
        if (is != null) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder builder = new StringBuilder(256);
                while (reader.ready()) builder.append(reader.readLine()).append('\n');
                content = builder.toString();
            } catch (IOException e) {
                Logger.getAnonymousLogger().log(Level.WARNING, "Unable to fetch content from:"+ location);
                // eat it, content stays null
            }
        }
        return content;
    }
    
    private static String fetchContentOrNull(String urlToRead) {
        // slow but standard
        URL url;
        HttpURLConnection conn;
        BufferedReader rd = null;
        String line;
        StringBuilder result = new StringBuilder();
        try {
           url = new URL(urlToRead);
           conn = (HttpURLConnection) url.openConnection();
           conn.setRequestMethod("GET");
           rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
           while ((line = rd.readLine()) != null) {
              result.append(line);
           }
        } catch (Exception e) {
           Logger.getAnonymousLogger().log(Level.WARNING, "Unable to fetch content from:"+ urlToRead);
           // eat it, content will be null
           return null;
        } finally {
            if (rd != null) try { rd.close(); } catch (Exception ex) {};            
        }
        return result.toString();        
    }
    /**
     * Write the content as text using the html. Escape if needed.
     */
    public void renderOn(HtmlCanvas html) throws IOException {
        html.write(content, escapeNeeded);
    }
}
