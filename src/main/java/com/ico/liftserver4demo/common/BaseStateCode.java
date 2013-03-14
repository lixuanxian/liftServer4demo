/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.common;

  
/**
 *
 * @author Sean
 */
public enum BaseStateCode {
    Ok,  //default is zero
    Error, 
    ExistSameUserName, 
    ExistSameEmail,
    ExistSamePhoneNumber,
    ExistSameSNS,
    PasswordISNull,
    SQLError,
    AccountNotActive,
    AccountNotExist;

}
