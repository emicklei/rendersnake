package org.rendersnake.element;

import java.util.HashMap;
import java.util.Map;

public abstract class Element {

    public Map<String, String> attributesMap = new HashMap<String, String>();

    public Element class_(String class_) {
        attributesMap.put("class", class_);
        return this;
    }

    public Element id(String id) {
        attributesMap.put("id", id);
        return this;
    }

    public Element style(String style) {
        attributesMap.put("style", style);
        return this;
    }

    public Element title(String title) {
        attributesMap.put("title", title);
        return this;
    }
}
