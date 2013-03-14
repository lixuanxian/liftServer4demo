/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.model.service;

import com.ico.liftserver4demo.model.User;
import com.ico.liftserver4demo.model.controller.UserJpaController;
import java.net.URI;
import java.util.List;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author Sean
 */
@Path("user")
public class UserRESTFacade extends RESTFacde {

   
    private UserJpaController getJpaController() {
        try {
            return new UserJpaController(null, getEntityManagerFactory());
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public UserRESTFacade() {
    }
     
    @POST 
    @Path("login")//for post
    //  @Path("login/{email}/{password}") //for get
    @Produces({"application/json"})
    public User findlogin(User user) {
        System.out.println("<<<<email >" + user.getEmail() + "   <<<<<password >" + user.getPassword());
        //     return  user;
        return getJpaController().loginUser(user.getEmail(), user.getPassword());
    }
    
//    @POST
//    @Consumes({"application/xml", "application/json"})
//    public Response create(User entity) {
//        try {
//            getJpaController().create(entity);
//            return Response.created(URI.create(entity.getId().toString())).build();
//        } catch (Exception ex) {
//            return Response.notModified(ex.getMessage()).build();
//        }
//    }
//
//    @PUT
//    @Consumes({"application/xml", "application/json"})
//    public Response edit(User entity) {
//        try {
//            getJpaController().edit(entity);
//            return Response.ok().build();
//        } catch (Exception ex) {
//            return Response.notModified(ex.getMessage()).build();
//        }
//    }
//
//    @DELETE
//    @Path("{id}")
//    public Response remove(@PathParam("id") Integer id) {
//        try {
//            getJpaController().destroy(id);
//            return Response.ok().build();
//        } catch (Exception ex) {
//            return Response.notModified(ex.getMessage()).build();
//        }
//    }
//
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public User find(@PathParam("id") Integer id) {
        return getJpaController().findUser(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<User> findAll() {
        return getJpaController().findUserEntities();
    }
//
//    @GET
//    @Path("{max}/{first}")
//    @Produces({"application/xml", "application/json"})
//    public List<User> findRange(@PathParam("max") Integer max, @PathParam("first") Integer first) {
//        return getJpaController().findUserEntities(max, first);
//    }
//
//    @GET
//    @Path("count")
//    @Produces("text/plain")
//    public String count() {
//        return String.valueOf(getJpaController().getUserCount());
//    }
//    
}
