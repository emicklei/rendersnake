package org.rendersnake.ext.spring.template;

import java.lang.annotation.Annotation;

import org.rendersnake.Renderable;

/**
 * Resolve and apply the template to the page.
 * 
 * @author Thibaut Mottet
 *
 */
public class TemplateAnnotationResolver {

	public static Renderable compute(Renderable component) {
		final Class<? extends Renderable> obj = component.getClass();
		if (obj.isAnnotationPresent(Template.class)) {
			final Annotation annotation = obj.getAnnotation(Template.class);
			final Template template = (Template) annotation;

			final Class<? extends TemplateDescriptor> templateClass = template.value();

			final TemplateDescriptor templateDescriptor = createInstance(templateClass);
			return new TemplateImplementation(component, templateDescriptor);
		}
		return component;
	}

	// Instanciate the template
	private static TemplateDescriptor createInstance(Class<? extends TemplateDescriptor> templateDescriptor) {
		try {
			return templateDescriptor.getConstructor().newInstance();
		} 
		catch (Exception e) {
			throw new RuntimeException("Problem to instanciate the template "+ templateDescriptor.getName() 
					+ ". You must have a simple constructor (without argument)", e);
		}
	}
}
