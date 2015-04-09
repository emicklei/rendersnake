package org.rendersnake.ext.spring.template;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies the template to apply to your renderable page.
 * Create your own template using {@link TemplateDescriptor}.
 * For example @see quickstart-rendersnake-spring-boot project
 * 
 * @author Thibaut Mottet
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Template {
	public Class<? extends TemplateDescriptor> value();
}