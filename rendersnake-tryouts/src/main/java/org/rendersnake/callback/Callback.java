package org.rendersnake.callback;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;

public interface Callback {
    void respondOn(HtmlCanvas html) throws IOException;
}
