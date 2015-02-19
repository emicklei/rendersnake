package org.rendersnake.internal;

import java.io.IOException;
import java.io.Writer;

/**
 * CharactersWriteable is a method-level interface construction.
 * It is used for the argument of Html element methods such as div.
 * 
 * @author e.micklei
 */
public interface CharactersWriteable {
    /**
     * @param writer
     * @throws IOException
     */
    void writeCharsOn(Writer writer) throws IOException;
}
