/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.model.service;

import com.ico.liftserver4demo.model.Task;
import com.ico.liftserver4demo.model.controller.TaskJpaController;
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
@Path("task")
public class TaskRESTFacade extends RESTFacde{

 

    private TaskJpaController getJpaController() {
        try {
            return new TaskJpaController(null, getEntityManagerFactory());
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public TaskRESTFacade() {
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public Response create(Task entity) {
        try {
            getJpaController().create(entity);
            return Response.created(URI.create(entity.getId().toString())).build();
        } catch (Exception ex) {
            return Response.notModified(ex.getMessage()).build();
        }
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public Response edit(Task entity) {
        try {
            getJpaController().edit(entity);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.notModified(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            getJpaController().destroy(id);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.notModified(ex.getMessage()).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Task find(@PathParam("id") Integer id) {
        return getJpaController().findTask(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Task> findAll() {
        return getJpaController().findTaskEntities();
    }

    @GET
    @Path("{max}/{first}")
    @Produces({"application/xml", "application/json"})
    public List<Task> findRange(@PathParam("max") Integer max, @PathParam("first") Integer first) {
        return getJpaController().findTaskEntities(max, first);
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String count() {
        return String.valueOf(getJpaController().getTaskCount());
    }
    
}
