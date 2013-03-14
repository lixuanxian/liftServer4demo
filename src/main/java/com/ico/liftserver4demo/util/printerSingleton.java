/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.util;

import com.ico.liftserver4demo.common.BaseStateCode;
import java.io.PrintWriter;

/**
 *
 * @author Sean
 */
public class printerSingleton {

    public static void printJosn(PrintWriter out, String dataString, String infoString, BaseStateCode code) {
        String info = infoString;
        if (info == null && code == BaseStateCode.Ok) {
            info = "ok";
        } else {
            info = "error";
        }
        
         String jsonString  = "{\"data\":" + dataString + ",\"info\":\"" + info + "\",\"code\":" + code.ordinal() + "}";
        out.println(jsonString);
//        LogSingleton.getLog().debug(jsonString);

        out.close();
    }
    
 
}
