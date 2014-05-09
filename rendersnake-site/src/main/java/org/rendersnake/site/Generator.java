package org.rendersnake.site;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

public class Generator {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String home = "target/org.rendersnake";
        new File(home).mkdirs();

        //generate(home, new IndexAction(), "index.html");
        //generate(home, new ExamplesAction(), "examples.html");

        FileUtils.copyDirectory(new File("src/main/webapp/htdocs"), new File(home + "/htdocs"));
    }

    private static void generate(String home, Renderable page, String uri) throws IOException {
        FileWriter fw = new FileWriter(home + "/" + uri);
        new HtmlCanvas(fw).render(page);
        fw.close();
        System.out.println("[generator] wrote " + uri + " size:" + new File(home + "/" + uri).length());
    }

}
