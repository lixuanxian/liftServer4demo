/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.controller;
 
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 
@SuppressWarnings("serial")
public class Upload extends HttpServlet {

 
    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest request, HttpServletResponse response)
           throws IOException, ServletException {
       request.setCharacterEncoding("UTF-8");

     System.out.println(" Start>>>doPost");
     String uploadPath =  this.initPath()  ;
      String tempPath = uploadPath+"/temp" ; 
     File tempPathFile=this.initTempFile(tempPath);

       try {
         
            // Create a factory for disk-based file items
           DiskFileItemFactory factory = new DiskFileItemFactory();
 
           // Set factory constraints
           factory.setSizeThreshold(4096); 
           factory.setRepository(tempPathFile);
 
           // Create a new file upload handler
           ServletFileUpload upload = new ServletFileUpload(factory);
 
           // Set overall request size constraint
           upload.setSizeMax(4194304); 
 
           List<FileItem> items = upload.parseRequest(request);
           Iterator<FileItem> i = items.iterator();
           while (i.hasNext()) {
              FileItem fi = i.next();
              String fileName = fi.getName();
              if (fileName != null) {
                  File fullFile = new File(fi.getName());
                  File savedFile = new File(uploadPath, fullFile.getName());
                  fi.write(savedFile);
              }
           }
          System.out.println(" finish>>>upload succeed");
       } catch (Exception e) {
           System.out.println( "exception >>> "+e.getMessage());
       }
       
            System.out.println(" Stop>>>doPost");

    }
 
    public String initPath() throws ServletException {
  System.out.println(" Start >>>> initPath");
ServletContext SContext =this.getServletContext();
String uploadPath = SContext.getRealPath("/")+"upload";
 
       File uploadFile = new File(uploadPath);
       if (!uploadFile.exists()) {
           uploadFile.mkdirs();
       }
 
                System.out.println(" Stop >>>> initPath");
        return uploadPath;

    }
    
    public  File initTempFile(String uploadTempPath){
          File tempPathFile = new File(uploadTempPath);
        if (!tempPathFile.exists()) {
           tempPathFile.mkdirs();
       }
        return tempPathFile;
        
    }

}