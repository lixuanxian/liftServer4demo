/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sean
 */
public class itextPdf extends HttpServlet {

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
            throws ServletException, IOException, DocumentException {
//        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/pdf");

//        PrintWriter out = response.getWriter();
        StringBuilder htmlBuilder = new StringBuilder("");
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head>");
        htmlBuilder.append("<title>Servlet itext_create</title>");
        htmlBuilder.append("</head>");
        htmlBuilder.append("<body>");
        htmlBuilder.append("<h1>Servlet itext_create at " + request.getContextPath() + "</h1>");
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

        Document document =  new Document(PageSize.A4, 36, 36, 36, 36);
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        try {
            PdfWriter writer =   PdfWriter.getInstance(document, ba);
            document.open();
            
            document.addTitle("title");
            document.addSubject("subject");
            document.addKeywords("keywords");
            document.addHeader("header1", "header_value");
            document.addAuthor("ICO Lift Admin");
            document.addCreationDate();
  //1. html     
          
     document.add(new Paragraph(htmlBuilder.toString()));
           
//1. phrase     
        Phrase phrase = new  Phrase();
         phrase.add(new Chunk("first line"));
           phrase.add(Chunk.NEWLINE);
     phrase.add(new Chunk("second line"));

          document.add(phrase);
//2.     Paragraph   
Paragraph para = new Paragraph("first line");
para.add(new Chunk("second line"));
para.add(Chunk.NEWLINE);
para.add("third line");
para.setAlignment(Element.ALIGN_RIGHT);

document.add(para);

// 3.  list
   List pList = new List();
    ListItem li = new ListItem();
    for (int i = 0; i < 3; i++) {
        Paragraph p = new Paragraph();
        p.add(new Chunk("item : " + i));
        li.add(p);
    }
    pList.setNumbered(true);
    pList.add(li);
    document.add(pList);
    document.add(Chunk.NEWLINE);
    List pList2 = new List();
    ListItem li2 = new ListItem();
    for (int i = 0; i < 3; i++) {
        li2.add(new Chunk("mainItem : " + i));
            for (int j = 0; j < 3; j++) {
                List pList3 = new List();
                ListItem li3 = new ListItem();
                Paragraph p = new Paragraph();
                p.add(new Chunk("subItem : " + j));
                li3.add(p);
                pList3.add(li3);
                pList3.setIndentationLeft(10f);
                li2.add(pList3);
            }
        }
        pList2.add(li2);
        document.add(pList2);
        
        
       //4.  Anchor
         Anchor intentAnchor = new Anchor("Intent Link");
       intentAnchor.setName("intentLink");
 
      Anchor intentAnchorDest = new Anchor("Intent Link Destination");
        intentAnchorDest.setName("intentLinkDest");
 
          intentAnchor.setReference("#intentLinkDest");//add link
 
        Chunk chunk = new Chunk("Chunk link");
     chunk.setLocalGoto("intentLinkDest"); //  
       // chunk.setLocalDestination("chunkDest");// 
       document.add(intentAnchor);
      document.add(chunk);
        document.newPage();
   document.add(intentAnchorDest);
        
   
   //5. chapter 
    for (int i = 0; i < 3; i++) {
        Chunk chunk1 = new  Chunk("chapter :" +i);
                Paragraph pha = new Paragraph();
              pha.add(chunk1);
             
               Chapter chapter = new Chapter(pha, i);
               for (int j = 0; j < 3; j++) {
                    Paragraph secPar = new Paragraph();
                 Chunk secChunk = new Chunk("Section : " + j);
                  secPar.add(secChunk);
                   Section sec = chapter.addSection(secPar);
                 sec.setIndentationLeft(10f);
              }
                 document.add(chapter);
       }
   
  //6.  Rectangle
          Rectangle rec = new Rectangle(0f, 806f, 32f, 842f);
          rec.setBackgroundColor(BaseColor.RED);
          document.add(rec);
          
    //7. image
           String jpegPath = "http://www.google.com.hk/intl/zh-CN_cn/images/logos/images_logo_lg.gif";
            Image jpeg = Image.getInstance(jpegPath);
            jpeg.scalePercent(20f);  
            jpeg.setRotation(-30f);  
            
            Image jpeg2 = Image.getInstance(jpegPath);
            jpeg2.scaleToFit(10f, 10f); 
            
            document.add(jpeg);
            document.add(jpeg2);
           
         } catch (DocumentException de) {
            de.printStackTrace();
            System.err.println("A Document error:" + de.getMessage());
        }
        document.close();
        response.setContentLength(ba.size());
        ServletOutputStream out = response.getOutputStream();
        ba.writeTo(out);
        out.flush();
 

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
        try {    
            processRequest(request, response);
        } catch (DocumentException ex) {
            Logger.getLogger(itextPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    
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
        try {
            processRequest(request, response);
        } catch (DocumentException ex) {
            Logger.getLogger(itextPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
      
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

