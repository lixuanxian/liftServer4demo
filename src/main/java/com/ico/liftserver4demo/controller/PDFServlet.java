package com.ico.liftserver4demo.controller;

/*
 * PDFServlet.java
 *
 * Created on June 9, 2007, 10:25 PM
 */



import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author joshy
 * @version
 */
public class PDFServlet extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        
        StringBuffer buf = new StringBuffer();
        buf.append("<html>");
        
        String css = getServletContext().getRealPath("/PDFservlet.css");
        System.out.println("css url 2= " + css);
        // put in some style
        buf.append("<head><link rel='stylesheet' type='text/css' "+
                "href='"+css+"' media='print'/></head>");
        
        buf.append("<body>");
        buf.append("<h1>Quarterly Reports for "+request.getParameter("username")+"</h1>");
        
        buf.append("<table cellspacing='0'>");
        buf.append("<tr><th>Sales</th><th>Profit</th><th>Bonus</th></tr>");
        
        // generate sales data
        int totalSales = 0;
        int totalProfit = 0;
        int totalBonus = 0;
        for(int i=0; i<10; i++) {
            int currentSales = (int)(Math.random()*10000);
            int currentProfit = (int)(currentSales*0.2);
            int currentBonus = (int)(currentProfit*0.33);
            buf.append("<tr><td>"+currentSales+"$</td><td>"+currentProfit+"$</td><td>"+currentBonus+"$</td></tr>");
            totalSales  += currentSales;
            totalProfit += currentProfit;
            totalBonus  += currentBonus;
        }        
        buf.append("<tr class='total-header'><td colspan='3'>totals</td></tr>");
        buf.append("<tr class='total'><td>"+totalSales+"$</td><td>"+totalProfit+"$</td><td>"+totalBonus+"$</td></tr>");
        buf.append("</table>");
        
        
        buf.append("<tr class='total-header'><td colspan='3'>totals</td></tr>");
        buf.append("<tr class='total'><td>"+totalSales+"$</td><td>"+totalProfit+"$</td><td>"+totalBonus+"$</td></tr>");
        buf.append("</table>");
        
        buf.append("</body>");
        buf.append("</html>");
        
        // parse our markup into an xml Document
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new StringBufferInputStream(buf.toString()));
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(doc, null);
            renderer.layout();
            OutputStream os = response.getOutputStream();
            renderer.createPDF(os);
            os.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
