package org.rendersnake.tools;

import java.util.logging.Logger;

import org.rendersnake.PageContext;

public class LoggingPageContext extends PageContext {
    private static final Logger LOG = Logger.getLogger("org.rendersnake.tools");

    @Override
    public PageContext withObject(String key, Object value) {
        LOG.fine("PageContext.["+this.attributes.getDepth()+"].set(" + key + "," + value + ")");
        return super.withObject(key, value);
    }

    @Override
    public PageContext withInteger(String key, Integer number) {
        LOG.fine("PageContext.["+this.attributes.getDepth()+"].set(" + key + "," + number + ")");
        return super.withInteger(key, number);
    }

    @Override
    public PageContext withBoolean(String key, Boolean trueOrFalse) {
        LOG.fine("PageContext.["+this.attributes.getDepth()+"].set(" + key + "," + trueOrFalse + ")");
        return super.withBoolean(key, trueOrFalse);
    }
}
