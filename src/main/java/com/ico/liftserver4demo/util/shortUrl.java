/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.util;

/**
 *
 * @author Sean
 */
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
  
public class shortUrl {
 
 public static String getMD5(String inputString) throws UnsupportedEncodingException{
 StringBuilder sb = new  StringBuilder("");
     try{
  java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
  md.update(inputString.getBytes("UTF-8"));
         for (byte b:md.digest()) {
             sb.append(Integer.toString(b>>>4&0xF,16)).append(Integer.toString(b&0xF,16));
          }
 }catch(NoSuchAlgorithmException e){
     
  }
 
     return sb.toString();
 }
 
 public static   String[] shortUrl(String url) throws UnsupportedEncodingException {
         String key = "ICOSean" ;
         String[] chars = new String[] { "a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" ,
               "i" , "j" , "k" , "l" , "m" , "n" , "o" , "p" , "q" , "r" , "s" , "t" ,
               "u" , "v" , "w" , "x" , "y" , "z" , "0" , "1" , "2" , "3" , "4" , "5" ,
               "6" , "7" , "8" , "9" , "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" ,
               "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" ,
               "U" , "V" , "W" , "X" , "Y" , "Z"
     };
         String sMD5EncryptResult =shortUrl.getMD5(key + url);
        String hex = sMD5EncryptResult;
         String[] resUrl = new String[4];
        for ( int i = 0; i < 4; i++) {
             String sTempSubString = hex.substring(i * 8, i * 8 + 8);
             long lHexLong = 0x3FFFFFFF & Long.parseLong (sTempSubString, 16);
            String outChars = "" ;
            for ( int j = 0; j < 6; j++) {
                long index = 0x0000003D & lHexLong;
                outChars += chars[( int ) index];
                 lHexLong >>= 5;
            }
             resUrl[i] = outChars;
        }
        return resUrl;
     }

 public static String getShortUrl(String url)throws UnsupportedEncodingException{
     String[] a =  shortUrl.shortUrl(url);
     if(a.length == 3){
         return a[0];
     }else{
      return  null;
     }
 }

}
 