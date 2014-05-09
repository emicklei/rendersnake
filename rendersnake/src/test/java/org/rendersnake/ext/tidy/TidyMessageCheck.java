package org.rendersnake.ext.tidy;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Assert;
import org.w3c.tidy.TidyMessage;
import org.w3c.tidy.TidyMessage.Level;
import org.w3c.tidy.TidyMessageListener;
/**
 * TidyMessageCheck is a helper class for Unit testing Renderable components and pages.
 * 
 * Unless instructed to collect all messages, it reports the first error using the jUnit Assert class and all warnings using a simple Logger.
 * 
 * @author ernestmicklei
 */
public class TidyMessageCheck implements TidyMessageListener{
    private Logger LOG = Logger.getLogger("org.rendersnake.ext.tidy.TidyMessageCheck");

    public boolean collectMessages = false;
    public List<TidyMessage> messages = new ArrayList<TidyMessage>();
    
    public void messageReceived(TidyMessage aMessage) {
        if (Level.ERROR.equals(aMessage.getLevel())) {
            if (collectMessages) {
                Assert.fail(aMessage.getMessage());
            } else {
                messages.add(aMessage);
            }
        } else {
            if (collectMessages) {
                messages.add(aMessage);
            } else {
                LOG.log(java.util.logging.Level.WARNING, aMessage.getMessage());
            }
        }
    }
    
}
