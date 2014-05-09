package org.rendersnake;

import java.io.IOException;

public class AttributesCanvas {

    public static HtmlAttributes id(String id) throws IOException {
        return new HtmlAttributes().add("id", id, false);
    }
}
