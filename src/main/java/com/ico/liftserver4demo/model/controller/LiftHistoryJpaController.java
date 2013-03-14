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
import com.ico.liftserver4demo.model.LiftHistory;
import com.ico.liftserver4demo.model.controller.exceptions.NonexistentEntityException;
import com.ico.liftserver4demo.model.controller.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sean
 */
public class LiftHistoryJpaController implements Serializable {

    public LiftHistoryJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LiftHistory liftHistory) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User updateUid = liftHistory.getUpdateUid();
            if (updateUid != null) {
                updateUid = em.getReference(updateUid.getClass(), updateUid.getId());
                liftHistory.setUpdateUid(updateUid);
            }
            Lift lid = liftHistory.getLid();
            if (lid != null) {
                lid = em.getReference(lid.getClass(), lid.getId());
                liftHistory.setLid(lid);
            }
            em.persist(liftHistory);
            if (updateUid != null) {
                updateUid.getLiftHistoryCollection().add(liftHistory);
                updateUid = em.merge(updateUid);
            }
            if (lid != null) {
                lid.getLiftHistoryCollection().add(liftHistory);
                lid = em.merge(lid);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLiftHistory(liftHistory.getId()) != null) {
                throw new PreexistingEntityException("LiftHistory " + liftHistory + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LiftHistory liftHistory) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LiftHistory persistentLiftHistory = em.find(LiftHistory.class, liftHistory.getId());
            User updateUidOld = persistentLiftHistory.getUpdateUid();
            User updateUidNew = liftHistory.getUpdateUid();
            Lift lidOld = persistentLiftHistory.getLid();
            Lift lidNew = liftHistory.getLid();
            if (updateUidNew != null) {
                updateUidNew = em.getReference(updateUidNew.getClass(), updateUidNew.getId());
                liftHistory.setUpdateUid(updateUidNew);
            }
            if (lidNew != null) {
                lidNew = em.getReference(lidNew.getClass(), lidNew.getId());
                liftHistory.setLid(lidNew);
            }
            liftHistory = em.merge(liftHistory);
            if (updateUidOld != null && !updateUidOld.equals(updateUidNew)) {
                updateUidOld.getLiftHistoryCollection().remove(liftHistory);
                updateUidOld = em.merge(updateUidOld);
            }
            if (updateUidNew != null && !updateUidNew.equals(updateUidOld)) {
                updateUidNew.getLiftHistoryCollection().add(liftHistory);
                updateUidNew = em.merge(updateUidNew);
            }
            if (lidOld != null && !lidOld.equals(lidNew)) {
                lidOld.getLiftHistoryCollection().remove(liftHistory);
                lidOld = em.merge(lidOld);
            }
            if (lidNew != null && !lidNew.equals(lidOld)) {
                lidNew.getLiftHistoryCollection().add(liftHistory);
                lidNew = em.merge(lidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = liftHistory.getId();
                if (findLiftHistory(id) == null) {
                    throw new NonexistentEntityException("The liftHistory with id " + id + " no longer exists.");
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
            LiftHistory liftHistory;
            try {
                liftHistory = em.getReference(LiftHistory.class, id);
                liftHistory.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The liftHistory with id " + id + " no longer exists.", enfe);
            }
            User updateUid = liftHistory.getUpdateUid();
            if (updateUid != null) {
                updateUid.getLiftHistoryCollection().remove(liftHistory);
                updateUid = em.merge(updateUid);
            }
            Lift lid = liftHistory.getLid();
            if (lid != null) {
                lid.getLiftHistoryCollection().remove(liftHistory);
                lid = em.merge(lid);
            }
            em.remove(liftHistory);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LiftHistory> findLiftHistoryEntities() {
        return findLiftHistoryEntities(true, -1, -1);
    }

    public List<LiftHistory> findLiftHistoryEntities(int maxResults, int firstResult) {
        return findLiftHistoryEntities(false, maxResults, firstResult);
    }

    private List<LiftHistory> findLiftHistoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LiftHistory.class));
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

    public LiftHistory findLiftHistory(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LiftHistory.class, id);
        } finally {
            em.close();
        }
    }

    public int getLiftHistoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LiftHistory> rt = cq.from(LiftHistory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
