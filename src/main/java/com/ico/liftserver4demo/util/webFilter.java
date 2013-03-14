/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.util;

import com.ico.liftserver4demo.util.LogSingleton;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
/**  
 *
 * @author Sean
 */
public class webFilter  implements Filter{
    private FilterConfig filterConfig; 

     
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
//  LogSingleton.debug("webFliter init");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//  LogSingleton.getLog().debug("webFliter doFilter");
// response.setContentType("text/json;charset=UTF-8");

//1. 
//request.setCharacterEncoding("UTF-8"); 
//response.setCharacterEncoding("UTF-8"); 
//response.setContentType("text/html;charset=UTF-8"); 

  Enumeration params = request.getParameterNames();
          while (params.hasMoreElements()) {
             String name = params.nextElement().toString();
             //System.out.println("name===========================" + name + "--");
             String[] value = request.getParameterValues(name);
              for (String tempString : value  ) {
                  if (tempString.contains("--")) {
                  this.hanleError(request, response, 10);
                  return;   
                  }


              }
          }

         chain.doFilter(request, response);
// chain.doFilter(new servletRequestObject((HttpServletRequest)request), response);
 
    }

    public void hanleError(ServletRequest request,ServletResponse response,Integer error_code) throws ServletException, IOException{
    request.getRequestDispatcher("/errorController?errorCode="+error_code+"").forward(request,response);
    }
    
    public void destroy() {
// LogSingleton.getLog().debug("webFliter destroy");
    }
    
}
