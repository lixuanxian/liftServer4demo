/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.util;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *
 * @author Sean
 */
public class servletRequestObject extends HttpServletRequestWrapper {
    private HttpServletRequest myServletRequest;

    public servletRequestObject(HttpServletRequest request) {
        super(request);
        this.myServletRequest = request;
//       LogSingleton.getLog().debug("servletRequestObject construct");
    }
    @Override
    public String getParameter(String name) {
      
        String value = this.getMyServletRequest().getParameter(name);
        if (value == null) {
            return null;
        }
 
        
        try {
            value = new String(value.getBytes("UTF-8"), "UTF-8").trim();
             
        } catch (UnsupportedEncodingException e) {
        }
        return value;
    }

    public HttpServletRequest getMyServletRequest() {
        return myServletRequest;
    }

    public void setMyServletRequest(HttpServletRequest myServletRequest) {
        this.myServletRequest = myServletRequest;
    }
}
