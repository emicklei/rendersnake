package com.company.ui;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexAction {
    
    @RequestMapping("/index.html")
    @ResponseBody
    public void home(HtmlCanvas html) throws IOException  {
        html.render(new SiteLayoutWrapper(new HomePage()));
    }
}
