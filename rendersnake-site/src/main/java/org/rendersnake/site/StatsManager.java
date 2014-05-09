package org.rendersnake.site;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatsManager  {
    private static final Logger LOG = LoggerFactory.getLogger(StatsManager.class);
    
    @Inject private void init() {
        this.contextInitialized();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() { StatsManager.this.contextDestroyed(); }
        });
    }
    
    public void contextDestroyed() {
        LOG.info("saving stats");
        Properties props = new Properties();
        props.put("translator.failed", String.valueOf(HtmlToRenderSnakeTranslator.TRANSLATIONS_FAILED));
        props.put("translator.linecount", String.valueOf(HtmlToRenderSnakeTranslator.TRANSLATIONS_LINECOUNT));
        props.put("translator.ok", String.valueOf(HtmlToRenderSnakeTranslator.TRANSLATIONS_OK));
        try {
            FileWriter fw = new FileWriter("stats.properties");
            props.store(new FileWriter("stats.properties"), "nothing to say");
            fw.close();
        } catch (IOException e) {
            LOG.error("Unable to save stats",e);
        }
    }

    
    public void contextInitialized() {
        LOG.info("loading stats");
        Properties props = new Properties();
        try {
            props.load(new FileReader("stats.properties"));
            HtmlToRenderSnakeTranslator.TRANSLATIONS_FAILED = Integer.valueOf(props.getProperty("translator.failed"));
            HtmlToRenderSnakeTranslator.TRANSLATIONS_LINECOUNT = Integer.valueOf(props.getProperty("translator.linecount"));
            HtmlToRenderSnakeTranslator.TRANSLATIONS_OK = Integer.valueOf(props.getProperty("translator.ok"));
        } catch (FileNotFoundException e) {
            // ignore, we will write one on exit
        } catch (IOException e) {
            LOG.error("Unable to load stats",e);
        }
    }
}
