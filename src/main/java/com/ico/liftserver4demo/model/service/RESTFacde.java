/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.model.service;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Sean
 */
public class RESTFacde {
 
        public EntityManagerFactory getEntityManagerFactory() throws NamingException {
         EntityManagerFactory  factory = Persistence.createEntityManagerFactory("com.ico_liftServer4Demo_war_1.0-SNAPSHOTPU");
        return factory;
    }
}
