/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.controller;

import com.ico.liftserver4demo.util.LogSingleton;
import com.itextpdf.text.pdf.BaseFont;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author Sean
 */
public class itextHtmlPdf extends HttpServlet {

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
    @SuppressWarnings("deprecation")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 //       response.setContentType("text/html;charset=UTF-8")
        response.setContentType("application/pdf");

         
        StringBuilder htmlBuilder = new StringBuilder("");
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head>");
  //    htmlBuilder.append("<title>Servlet itext_create</title>");
        htmlBuilder.append("</head>");
        htmlBuilder.append("<body>");
        htmlBuilder.append("<div style='color:red;'>Servlet itext_create at " + request.getContextPath() + "</div>");
        htmlBuilder.append("<div style=\"padding:10px; width:300px; height:50px;background:#FC9; -moz-border-radius: 15px;-webkit-border-radius: 15px;border-radius:15px;\"> hello world</div>  ");
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");
        
//     /** The StyleSheet. */
//      StyleSheet styles = null;
//    /** Extra properties. */
//      HashMap<String,Object> providers = null;
// 
//        Document document = new Document(PageSize.A4, 36, 36, 36, 36);
//        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        try {
//            PdfWriter writer = PdfWriter.getInstance(document, ba);
//            document.open();
//
//            document.addTitle("电梯检查报告");
//            document.addSubject("电梯检查报告");
//            document.addKeywords("电梯  检查  报告");
////            document.addHeader("", " ");
//            document.addAuthor("ICO Lift Admin");
//            document.addCreationDate();
//            
// 

 //  1.   使用的字符串的方式来创建 
//     DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//    Document doc = builder.parse(new StringBufferInputStream(buf.toString()));
       
            

//            HtmlParser.parse(document, "legal.html");
            ServletOutputStream out = response.getOutputStream();
            ServletContext SContext = this.getServletContext();
            String htmlPath = SContext.getRealPath("/") + "WEB-INF/classes/";
            String url = htmlPath + "legal.html";

        
            //解决相对路径引用资源问题
            String newPath = new File(url).toURI().toURL().toString();
            LogSingleton.GetInstance().debug(newPath);
           
            ITextRenderer renderer = new ITextRenderer();
           // renderer.getSharedContext().setBaseURL(htmlPath);
            
          //解决中文支持
           ITextFontResolver fontResolver = renderer.getFontResolver();  
           fontResolver.addFont(htmlPath+"font/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);  
        
           //需要控制正常PDF 输出
          //  renderer.setDocument(newPath);
   
           //遇到问题，相对图片不能正常显示
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc =  builder.parse(new File(url));
           
            //得到文档名称为Student的元素的节点列表
//            Element htmlElement = (Element)doc.getElementsByTagName("html").item(0);
//            Element  headElement = (Element)htmlElement.getElementsByTagName("head").item(0);
//            LogSingleton.GetInstance().debug( htmlElement.getNodeValue());
            
        //  Element htmlElement = doc.getElementById("legal_title");  
         LogSingleton.GetInstance().debug( doc.toString());

             renderer.setDocument(doc,newPath);
 
            renderer.layout();
            renderer.createPDF(out);
            out.flush();
            out.close();
        } catch (Exception de) {
            System.err.println("A Document error:" + de.getMessage());
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
