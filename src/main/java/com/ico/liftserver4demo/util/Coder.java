/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.util;

import java.security.MessageDigest;
import java.util.Date;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Sean
 */
public class Coder {

    public static final String KEY_SHA = "SHA1";
    public static final String KEY_MD5 = "MD5";
     public static final String ICOKEY = "ICO@China$Lift";
    /**
      *
     * <pre>
     * HmacMD5
     * HmacSHA1
     * HmacSHA256
     * HmacSHA384
     * HmacSHA512
     * </pre>
     */
    public static final String KEY_MAC = "HmacSHA1";

    /**
      *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }
    
     public static String decodeBASE64(String key) throws Exception {
 
        byte[] BaseString = (new BASE64Decoder()).decodeBuffer(new String(key.getBytes("UTF-8"),"UTF-8"));
        return  new  String(BaseString,"UTF-8");
    }
    /**
      *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }
    public static String encodeBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
      * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptMD5(byte[] data) throws Exception {

        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);

        return md5.digest();

    }
    
        public static String encodeSHA1(String data) throws Exception {

        byte[] byte_key = data.getBytes();
        String sha1_data = encryptBASE64(encryptSHA(byte_key)).trim();
        return sha1_data;

    }

    /**
      *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {

        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);

        return sha.digest();

    }

    /**
      *
     * @return
     * @throws Exception
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    /**
      *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);

        return mac.doFinal(data);

    }

    /**
      * @param key
     * @return
     * @throws Exception
     */
    public static String enCodeBaseICO(String key) throws Exception {
        byte[] byte_key = key.getBytes();
        String sha1_key = encryptBASE64(encryptSHA(byte_key)).trim();
        String time_string = String.valueOf((new Date()).getTime());
// System.out.println("encode:"+(time_string + "&" + sha1_key + "&" + ICOKEY));
        return encryptBASE64((time_string + "&" + sha1_key + "&" + ICOKEY).getBytes());

    }

    /**
      * @param key
     * @return
     * @throws Exception
     */
    public static String deCodeBaseICO(String key) throws Exception {
        String decode_key = decodeBASE64(key);

        String[] decode_array = decode_key.split("&");
//              System.out.print("decode_array:"+decode_array[0]    +"   ------ "+ decode_array[2]);

        if (decode_array.length == 3) {
            return decode_array[1];
        } else {
            return null;
        }

    }
    
    
    /**
      * @return
     * @throws Exception  
     */
    public static String enCodeURLBaseICO(String json) throws Exception{
        return encryptBASE64(json.toString().trim().getBytes());
    }
 
        /**
      * @return
     * @throws Exception  
     */
    public static JSONObject  deCodeURLBaseICO(String url) throws Exception{
        JSONObject json_object = new JSONObject(new String(decryptBASE64(url)));
        return json_object;
    }
    
}
