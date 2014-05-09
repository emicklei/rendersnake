package org.rendersnake.test;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

public class MockServletConfig implements ServletConfig {

    public Map<String,String> map = new HashMap<String,String>();
    
    public String getInitParameter(String arg0) {
        // TODO Auto-generated method stub
        return map.get(arg0);
    }

    public Enumeration getInitParameterNames() {
        // TODO Auto-generated method stub
        return null;
    }

    public ServletContext getServletContext() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getServletName() {
        // TODO Auto-generated method stub
        return null;
    }

}
