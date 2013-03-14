/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.util;

import java.util.Arrays;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Sean
 */
public class autoSendEmail {
/**
  * @param email
 * @param resetKey
 * @throws EmailException  
 */
public static void SendSimpleEmail(String toEmail,String subject, String  msg ) throws EmailException {
 SimpleEmail email = new  SimpleEmail();
email.setHostName("smtp.yeah.net");
email.setAuthentication("lixuanxian@yeah.net","5303252331");
email.setCharset("UTF-8");
email.addTo("lxx8585@126.com", "sean li");
email.setFrom("lixuanxian@yeah.net", "Lift Admin");

    if (subject.isEmpty()) {
        subject = "Lift";
    }
    
email.setSubject(subject);
email.setMsg(msg);
String result_s =  email.send(); 

//LogSingleton.getLog().debug(result_s);
}

 public static void SendHTMLEmail(String toEmail,String subject, String  msg ) throws EmailException {
 HtmlEmail email = new  HtmlEmail();
email.setHostName("smtp.yeah.net");
email.setAuthentication("lixuanxian@yeah.net","5303252331");
email.setCharset("UTF-8");
email.addTo(toEmail);
email.setFrom("lixuanxian@yeah.net", "Lift Admin");
 
email.setSubject(subject);
email.setHtmlMsg("<html><body> "+msg+" </body></html>");
String result_s =  email.send(); 

//LogSingleton.getLog().debug(result_s);
}


}
