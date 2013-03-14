/*
 *  Deal shortUrl for app 
 */
package com.ico.liftserver4demo.controller;

import com.ico.liftserver4demo.util.UserAgentSort;
import com.ico.liftserver4demo.util.UserAgentSort.BrowserSystem;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sean
 */
public class app extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //处理app 内短信中的短地址，如果是iOS设备则直接打开应用软件，否则提示有新消息
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        StringBuffer html_content = new StringBuffer("<html>");

        html_content.append("<head>");
        html_content.append("<title>Servlet app</title>");
        html_content.append("</head>");
        html_content.append("<body>");

        if (UserAgentSort.getBrowserSystem(request.getHeader("USER-AGENT")) == BrowserSystem.iOS) {
            //       response.sendRedirect("ICOLift://");
            html_content.append("<h1>请下载电梯检查软件 </h1>");
            html_content.append("<div> <a href='ICOLift://'>请打开电梯检查软件，查收你的最新消息</a></div>");
        } else {
            html_content.append("<h1>请下载电梯检查软件，并安装在手机端，可以直接使用手机打开</h1>");
            html_content.append("<div> 请打开电梯检查软件，查收你的最新消息</div>");
        }
        
        
        html_content.append("</body>");
        html_content.append("</html>");
        try {
            /* TODO output your page here. You may use following sample code. */
            out.print(html_content);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
