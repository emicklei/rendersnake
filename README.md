rendersnake
===========

RenderSnake is a Java library for creating components and pages that produce HTML using only Java. Its purpose is to support the creation of Web applications that are better maintainable, allows for easier reuse, have testable UI components and produces compact HTML in an efficient way.

Visit [rendersnake.org](http://rendersnake.org) to read the full documentation from a renderSnake powered application.

Hello example
```java
HtmlCanvas html = new HtmlCanvas();
html
  .html()
    .body()
       .h1().content("Hello Coder")
    ._body()
  ._html();
System.out.println(html.toHtml());
```


Example of a complex Form element to pick one of four options
```java
html.div(dataRole("fieldcontain"))
    .fieldset(dataRole("controlgroup").dataType("horizontal"))
        .legend().content("Method")
        .input(type("radio").name("method").id("radio-get").value("method-get").checked("checked").onChange("clickedMethod(this.value);"))
        .label(for_("radio-get")).content("GET")
        
        .input(type("radio").name("method").id("radio-post").value("method-post").onChange("clickedMethod(this.value);"))
        .label(for_("radio-post")).content("POST")
        
        .input(type("radio").name("method").id("radio-put").value("method-put").onChange("clickedMethod(this.value);"))
        .label(for_("radio-put")).content("PUT")
        
        .input(type("radio").name("method").id("radio-delete").value("method-delete").onChange("clickedMethod(this.value);"))
        .label(for_("radio-delete")).content("DELETE")
    ._fieldset()
    ._div();
```


Example of a HTML5 page wrapper and JQuery Mobile
```java
public class MobileSiteLayoutWrapper extends RenderableWrapper {

    public MobileSiteLayoutWrapper(Renderable component) {
        super(component);
    }

    @Override
    public void renderOn(HtmlCanvas html) throws IOException {
        html
        .render(DocType.HTML5)
        .html()
            .head()
                .title().content("renderSnake - Mobile")
                .render(JQueryLibrary.mobileTheme("1.0"))
                .render(JQueryLibrary.core("1.6.4"))
                .render(JQueryLibrary.mobile("1.0"))
            ._head()
        .body()
            .div(dataRole("page"))
                .div(dataRole("header").dataTheme("b"))
                    .render(new PageHeader())
                    ._div()
                .div(dataRole("content").dataTheme("b"))
                    .render(this.component)
                    ._div()
                .div(dataRole("footer").dataTheme("b"))
                    .render(new PageFooter())
                    ._div()
            ._div()
        ._body()
        ._html();
    }
}
```

Example of a login component
```java
public class LoginPageContent implements Renderable {

    @Override
    public void renderOn(HtmlCanvas html) throws IOException {// @formatter:off

        html.form(action("/login").method("post").id("login-form"))
            .fieldset()
                .div(dataRole("fieldcontain"))
                    .label(for_("name")).content("Username")                    
                    .input(type("text").name("name").id("name"))                    
                ._div()
                .div(dataRole("fieldcontain"))
                    .label(for_("password")).content("Password")
                    .input(type("password").name("password").id("password"))                    
                ._div()
                .input(type("submit").value("Login"))
            ._fieldset()
            ._form();                                               
    }        
}
```
Maven Installation
````
<dependency>
    <groupId>org.rendersnake</groupId>
    <artifactId>rendersnake</artifactId>
    <version>1.8</version>
</dependency>
```
