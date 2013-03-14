/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.model.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.ico.liftserver4demo.model.User;
import com.ico.liftserver4demo.model.Lift;
import com.ico.liftserver4demo.model.Task;
import com.ico.liftserver4demo.model.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sean
 */
public class TaskJpaController implements Serializable {

    public TaskJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Task task) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User sendUid = task.getSendUid();
            if (sendUid != null) {
                sendUid = em.getReference(sendUid.getClass(), sendUid.getId());
                task.setSendUid(sendUid);
            }
            Lift liftLid = task.getLiftLid();
            if (liftLid != null) {
                liftLid = em.getReference(liftLid.getClass(), liftLid.getId());
                task.setLiftLid(liftLid);
            }
            User createUid = task.getCreateUid();
            if (createUid != null) {
                createUid = em.getReference(createUid.getClass(), createUid.getId());
                task.setCreateUid(createUid);
            }
            em.persist(task);
            if (sendUid != null) {
                sendUid.getTaskCollection().add(task);
                sendUid = em.merge(sendUid);
            }
            if (liftLid != null) {
                liftLid.getTaskCollection().add(task);
                liftLid = em.merge(liftLid);
            }
            if (createUid != null) {
                createUid.getTaskCollection().add(task);
                createUid = em.merge(createUid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Task task) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Task persistentTask = em.find(Task.class, task.getId());
            User sendUidOld = persistentTask.getSendUid();
            User sendUidNew = task.getSendUid();
            Lift liftLidOld = persistentTask.getLiftLid();
            Lift liftLidNew = task.getLiftLid();
            User createUidOld = persistentTask.getCreateUid();
            User createUidNew = task.getCreateUid();
            if (sendUidNew != null) {
                sendUidNew = em.getReference(sendUidNew.getClass(), sendUidNew.getId());
                task.setSendUid(sendUidNew);
            }
            if (liftLidNew != null) {
                liftLidNew = em.getReference(liftLidNew.getClass(), liftLidNew.getId());
                task.setLiftLid(liftLidNew);
            }
            if (createUidNew != null) {
                createUidNew = em.getReference(createUidNew.getClass(), createUidNew.getId());
                task.setCreateUid(createUidNew);
            }
            task = em.merge(task);
            if (sendUidOld != null && !sendUidOld.equals(sendUidNew)) {
                sendUidOld.getTaskCollection().remove(task);
                sendUidOld = em.merge(sendUidOld);
            }
            if (sendUidNew != null && !sendUidNew.equals(sendUidOld)) {
                sendUidNew.getTaskCollection().add(task);
                sendUidNew = em.merge(sendUidNew);
            }
            if (liftLidOld != null && !liftLidOld.equals(liftLidNew)) {
                liftLidOld.getTaskCollection().remove(task);
                liftLidOld = em.merge(liftLidOld);
            }
            if (liftLidNew != null && !liftLidNew.equals(liftLidOld)) {
                liftLidNew.getTaskCollection().add(task);
                liftLidNew = em.merge(liftLidNew);
            }
            if (createUidOld != null && !createUidOld.equals(createUidNew)) {
                createUidOld.getTaskCollection().remove(task);
                createUidOld = em.merge(createUidOld);
            }
            if (createUidNew != null && !createUidNew.equals(createUidOld)) {
                createUidNew.getTaskCollection().add(task);
                createUidNew = em.merge(createUidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = task.getId();
                if (findTask(id) == null) {
                    throw new NonexistentEntityException("The task with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Task task;
            try {
                task = em.getReference(Task.class, id);
                task.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The task with id " + id + " no longer exists.", enfe);
            }
            User sendUid = task.getSendUid();
            if (sendUid != null) {
                sendUid.getTaskCollection().remove(task);
                sendUid = em.merge(sendUid);
            }
            Lift liftLid = task.getLiftLid();
            if (liftLid != null) {
                liftLid.getTaskCollection().remove(task);
                liftLid = em.merge(liftLid);
            }
            User createUid = task.getCreateUid();
            if (createUid != null) {
                createUid.getTaskCollection().remove(task);
                createUid = em.merge(createUid);
            }
            em.remove(task);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Task> findTaskEntities() {
        return findTaskEntities(true, -1, -1);
    }

    public List<Task> findTaskEntities(int maxResults, int firstResult) {
        return findTaskEntities(false, maxResults, firstResult);
    }

    private List<Task> findTaskEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Task.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Task findTask(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Task.class, id);
        } finally {
            em.close();
        }
    }

    public int getTaskCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Task> rt = cq.from(Task.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
