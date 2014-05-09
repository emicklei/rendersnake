package org.rendersnake.test;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.ext.servlet.PostHandler;

public class SearchPage implements PostHandler, Renderable {

    public void renderOn(HtmlCanvas html) throws IOException {
    }

    public void handlePost(HttpServletRequest request, HttpServletResponse response) {
    }
}
