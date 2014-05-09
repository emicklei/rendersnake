package org.rendersnake.test.components;

import java.io.IOException;

import org.rendersnake.HtmlAttributes;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.test.Person;
import static org.rendersnake.HtmlAttributesFactory.*;


public class PersonUI implements Renderable {

    private Person who;
    
    public PersonUI(Person person) {
        this.who = person;
    }
    
    public void renderOn(HtmlCanvas html) throws IOException {// @formatter:off
	    
		html.table(id("person-details"))
			.tr()
				.td().write("Naam").close()
				.td().write(who.name).close()
			._tr()
			.tr()
				.td(new HtmlAttributes("background", "#FF00FF"))
				    .write("Woonplaats")
				    ._td()
				.td().write(who.city)._td()
			._tr()			
		._table();	
	}
	// @formatter:on
}
