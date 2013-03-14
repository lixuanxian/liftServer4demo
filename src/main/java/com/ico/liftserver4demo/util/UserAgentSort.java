/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.util;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 *
 * @author Sean
 */


public class UserAgentSort {
    
    
   public enum  BrowserSystem{
  iOS,
  Mac,
  Linux,
  Windows,
  Other
}
    /**
     *1. chorme  for mac
    *Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_1) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.101 Safari/537.11
    *2. firefox for mac
    * Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:17.0) Gecko/20100101 Firefox/17.0
    *3. safari  for mac
    *Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_1) AppleWebKit/536.25 (KHTML, like Gecko) Version/6.0 Safari/536.25
    * 
     * Mozilla/5.0 (iPad; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/420.1 (KHTML, like Gecko) Version/3.0 Mobile/1A542a Safari/419.3
    * Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_0 like Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8A293 Safari/6531.22.7
    */

  public  static  BrowserSystem getBrowserSystem(String userAgentString){
  if(userAgentString == null){
          return  BrowserSystem.Other;
  }
      BrowserSystem browserSystemValue = BrowserSystem.iOS; //default is iOS
      
        String sortString = null;
        //http://regexr.com?33b2c  
         
           boolean isMatcher=(userAgentString.contains("iPad") | userAgentString.contains("iPhone") )&& userAgentString.contains("Mobile");
 
        if (!isMatcher) {
          browserSystemValue = BrowserSystem.Other;
      }
        //
    return browserSystemValue; 
    }  
    
    
    public  static  String getBrowserSort(String userAgentString){
        String sortString = null;
        //http://regexr.com?33b2c  
        String regex = "(iPad|iPhone).*Mobile"; //  iOS
       
        //
    return sortString; 
    }  
    
     
   public  static  Dictionary<String,String> getBrowserInfo(String userAgentString){
      Dictionary<String , String>  browserInfoDic =  new Hashtable<String, String>();
        //
    return browserInfoDic; 
    }  
}
