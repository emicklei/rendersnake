package org.rendersnake.ext.spring.template;

import java.io.IOException;

import org.rendersnake.DocType;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.RenderableWrapper;

/**
 * Implementation of the template using the TemplateDescriptor define
 * by the devolopper
 * 
 * @author Thibaut Mottet
 *
 */
public class TemplateImplementation extends RenderableWrapper {
	private final TemplateDescriptor templateDescriptor;

	public TemplateImplementation(Renderable component, TemplateDescriptor templateDescriptor) {
		super(component);
		this.templateDescriptor = templateDescriptor;
	}

	@Override
	public void renderOn(HtmlCanvas html) throws IOException {
		html.render(DocType.HTML5)
			.html()
				.head()
					.render(new Head())
					.title().content(templateDescriptor.getDefaultTitle())
				._head()
				.body()
					.render(new BodyStart())
					.render(this.component)
					.render(new BodyEnd())
				._body()
			._html();
	}
	
	private class Head implements Renderable {
		public void renderOn(HtmlCanvas html) throws IOException {
			templateDescriptor.renderHeaderOn(html);
		}
	}
	
	private class BodyStart implements Renderable {
		public void renderOn(HtmlCanvas html) throws IOException {
			templateDescriptor.renderBodyStartOn(html);
		}
	}
	
	private class BodyEnd implements Renderable {
		public void renderOn(HtmlCanvas html) throws IOException {
			templateDescriptor.renderBodyEndOn(html);
		}
	}

}
