/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
 
/*
 * @author Sean
 */
public   class LogSingleton {
//    private static Logger log = LogManager.getLogger(LogSingleton.class);
     private final static  LogSingleton logSingleton = new LogSingleton();
   
     public static synchronized  LogSingleton GetInstance(){
         return  logSingleton;
     }
 
   public void debug(String msg){
  System.out.println("debug>>>"+msg);
//      log.debug("debug>>>"+msg);

   }
    public void info(String msg){
   System.out.println("info>>>"+msg);
//     log.info("info>>>"+msg);

   }
   public void Warn(String msg){
   System.out.println("worning>>>"+msg);
//         log.warn("info>>>"+msg);

   }
}
