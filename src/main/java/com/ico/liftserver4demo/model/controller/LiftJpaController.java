/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.model.controller;

import com.ico.liftserver4demo.model.Lift;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.ico.liftserver4demo.model.User;
import com.ico.liftserver4demo.model.Task;
import java.util.ArrayList;
import java.util.Collection;
import com.ico.liftserver4demo.model.LiftHistory;
import com.ico.liftserver4demo.model.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sean
 */
public class LiftJpaController implements Serializable {

    public LiftJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lift lift) {
        if (lift.getTaskCollection() == null) {
            lift.setTaskCollection(new ArrayList<Task>());
        }
        if (lift.getLiftHistoryCollection() == null) {
            lift.setLiftHistoryCollection(new ArrayList<LiftHistory>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User selfCheckUid = lift.getSelfCheckUid();
            if (selfCheckUid != null) {
                selfCheckUid = em.getReference(selfCheckUid.getClass(), selfCheckUid.getId());
                lift.setSelfCheckUid(selfCheckUid);
            }
            User maintenanceUid = lift.getMaintenanceUid();
            if (maintenanceUid != null) {
                maintenanceUid = em.getReference(maintenanceUid.getClass(), maintenanceUid.getId());
                lift.setMaintenanceUid(maintenanceUid);
            }
            Collection<Task> attachedTaskCollection = new ArrayList<Task>();
            for (Task taskCollectionTaskToAttach : lift.getTaskCollection()) {
                taskCollectionTaskToAttach = em.getReference(taskCollectionTaskToAttach.getClass(), taskCollectionTaskToAttach.getId());
                attachedTaskCollection.add(taskCollectionTaskToAttach);
            }
            lift.setTaskCollection(attachedTaskCollection);
            Collection<LiftHistory> attachedLiftHistoryCollection = new ArrayList<LiftHistory>();
            for (LiftHistory liftHistoryCollectionLiftHistoryToAttach : lift.getLiftHistoryCollection()) {
                liftHistoryCollectionLiftHistoryToAttach = em.getReference(liftHistoryCollectionLiftHistoryToAttach.getClass(), liftHistoryCollectionLiftHistoryToAttach.getId());
                attachedLiftHistoryCollection.add(liftHistoryCollectionLiftHistoryToAttach);
            }
            lift.setLiftHistoryCollection(attachedLiftHistoryCollection);
            em.persist(lift);
            if (selfCheckUid != null) {
                selfCheckUid.getLiftCollection().add(lift);
                selfCheckUid = em.merge(selfCheckUid);
            }
            if (maintenanceUid != null) {
                maintenanceUid.getLiftCollection().add(lift);
                maintenanceUid = em.merge(maintenanceUid);
            }
            for (Task taskCollectionTask : lift.getTaskCollection()) {
                Lift oldLiftLidOfTaskCollectionTask = taskCollectionTask.getLiftLid();
                taskCollectionTask.setLiftLid(lift);
                taskCollectionTask = em.merge(taskCollectionTask);
                if (oldLiftLidOfTaskCollectionTask != null) {
                    oldLiftLidOfTaskCollectionTask.getTaskCollection().remove(taskCollectionTask);
                    oldLiftLidOfTaskCollectionTask = em.merge(oldLiftLidOfTaskCollectionTask);
                }
            }
            for (LiftHistory liftHistoryCollectionLiftHistory : lift.getLiftHistoryCollection()) {
                Lift oldLidOfLiftHistoryCollectionLiftHistory = liftHistoryCollectionLiftHistory.getLid();
                liftHistoryCollectionLiftHistory.setLid(lift);
                liftHistoryCollectionLiftHistory = em.merge(liftHistoryCollectionLiftHistory);
                if (oldLidOfLiftHistoryCollectionLiftHistory != null) {
                    oldLidOfLiftHistoryCollectionLiftHistory.getLiftHistoryCollection().remove(liftHistoryCollectionLiftHistory);
                    oldLidOfLiftHistoryCollectionLiftHistory = em.merge(oldLidOfLiftHistoryCollectionLiftHistory);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lift lift) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lift persistentLift = em.find(Lift.class, lift.getId());
            User selfCheckUidOld = persistentLift.getSelfCheckUid();
            User selfCheckUidNew = lift.getSelfCheckUid();
            User maintenanceUidOld = persistentLift.getMaintenanceUid();
            User maintenanceUidNew = lift.getMaintenanceUid();
            Collection<Task> taskCollectionOld = persistentLift.getTaskCollection();
            Collection<Task> taskCollectionNew = lift.getTaskCollection();
            Collection<LiftHistory> liftHistoryCollectionOld = persistentLift.getLiftHistoryCollection();
            Collection<LiftHistory> liftHistoryCollectionNew = lift.getLiftHistoryCollection();
            if (selfCheckUidNew != null) {
                selfCheckUidNew = em.getReference(selfCheckUidNew.getClass(), selfCheckUidNew.getId());
                lift.setSelfCheckUid(selfCheckUidNew);
            }
            if (maintenanceUidNew != null) {
                maintenanceUidNew = em.getReference(maintenanceUidNew.getClass(), maintenanceUidNew.getId());
                lift.setMaintenanceUid(maintenanceUidNew);
            }
            Collection<Task> attachedTaskCollectionNew = new ArrayList<Task>();
            for (Task taskCollectionNewTaskToAttach : taskCollectionNew) {
                taskCollectionNewTaskToAttach = em.getReference(taskCollectionNewTaskToAttach.getClass(), taskCollectionNewTaskToAttach.getId());
                attachedTaskCollectionNew.add(taskCollectionNewTaskToAttach);
            }
            taskCollectionNew = attachedTaskCollectionNew;
            lift.setTaskCollection(taskCollectionNew);
            Collection<LiftHistory> attachedLiftHistoryCollectionNew = new ArrayList<LiftHistory>();
            for (LiftHistory liftHistoryCollectionNewLiftHistoryToAttach : liftHistoryCollectionNew) {
                liftHistoryCollectionNewLiftHistoryToAttach = em.getReference(liftHistoryCollectionNewLiftHistoryToAttach.getClass(), liftHistoryCollectionNewLiftHistoryToAttach.getId());
                attachedLiftHistoryCollectionNew.add(liftHistoryCollectionNewLiftHistoryToAttach);
            }
            liftHistoryCollectionNew = attachedLiftHistoryCollectionNew;
            lift.setLiftHistoryCollection(liftHistoryCollectionNew);
            lift = em.merge(lift);
            if (selfCheckUidOld != null && !selfCheckUidOld.equals(selfCheckUidNew)) {
                selfCheckUidOld.getLiftCollection().remove(lift);
                selfCheckUidOld = em.merge(selfCheckUidOld);
            }
            if (selfCheckUidNew != null && !selfCheckUidNew.equals(selfCheckUidOld)) {
                selfCheckUidNew.getLiftCollection().add(lift);
                selfCheckUidNew = em.merge(selfCheckUidNew);
            }
            if (maintenanceUidOld != null && !maintenanceUidOld.equals(maintenanceUidNew)) {
                maintenanceUidOld.getLiftCollection().remove(lift);
                maintenanceUidOld = em.merge(maintenanceUidOld);
            }
            if (maintenanceUidNew != null && !maintenanceUidNew.equals(maintenanceUidOld)) {
                maintenanceUidNew.getLiftCollection().add(lift);
                maintenanceUidNew = em.merge(maintenanceUidNew);
            }
            for (Task taskCollectionOldTask : taskCollectionOld) {
                if (!taskCollectionNew.contains(taskCollectionOldTask)) {
                    taskCollectionOldTask.setLiftLid(null);
                    taskCollectionOldTask = em.merge(taskCollectionOldTask);
                }
            }
            for (Task taskCollectionNewTask : taskCollectionNew) {
                if (!taskCollectionOld.contains(taskCollectionNewTask)) {
                    Lift oldLiftLidOfTaskCollectionNewTask = taskCollectionNewTask.getLiftLid();
                    taskCollectionNewTask.setLiftLid(lift);
                    taskCollectionNewTask = em.merge(taskCollectionNewTask);
                    if (oldLiftLidOfTaskCollectionNewTask != null && !oldLiftLidOfTaskCollectionNewTask.equals(lift)) {
                        oldLiftLidOfTaskCollectionNewTask.getTaskCollection().remove(taskCollectionNewTask);
                        oldLiftLidOfTaskCollectionNewTask = em.merge(oldLiftLidOfTaskCollectionNewTask);
                    }
                }
            }
            for (LiftHistory liftHistoryCollectionOldLiftHistory : liftHistoryCollectionOld) {
                if (!liftHistoryCollectionNew.contains(liftHistoryCollectionOldLiftHistory)) {
                    liftHistoryCollectionOldLiftHistory.setLid(null);
                    liftHistoryCollectionOldLiftHistory = em.merge(liftHistoryCollectionOldLiftHistory);
                }
            }
            for (LiftHistory liftHistoryCollectionNewLiftHistory : liftHistoryCollectionNew) {
                if (!liftHistoryCollectionOld.contains(liftHistoryCollectionNewLiftHistory)) {
                    Lift oldLidOfLiftHistoryCollectionNewLiftHistory = liftHistoryCollectionNewLiftHistory.getLid();
                    liftHistoryCollectionNewLiftHistory.setLid(lift);
                    liftHistoryCollectionNewLiftHistory = em.merge(liftHistoryCollectionNewLiftHistory);
                    if (oldLidOfLiftHistoryCollectionNewLiftHistory != null && !oldLidOfLiftHistoryCollectionNewLiftHistory.equals(lift)) {
                        oldLidOfLiftHistoryCollectionNewLiftHistory.getLiftHistoryCollection().remove(liftHistoryCollectionNewLiftHistory);
                        oldLidOfLiftHistoryCollectionNewLiftHistory = em.merge(oldLidOfLiftHistoryCollectionNewLiftHistory);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lift.getId();
                if (findLift(id) == null) {
                    throw new NonexistentEntityException("The lift with id " + id + " no longer exists.");
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
            Lift lift;
            try {
                lift = em.getReference(Lift.class, id);
                lift.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lift with id " + id + " no longer exists.", enfe);
            }
            User selfCheckUid = lift.getSelfCheckUid();
            if (selfCheckUid != null) {
                selfCheckUid.getLiftCollection().remove(lift);
                selfCheckUid = em.merge(selfCheckUid);
            }
            User maintenanceUid = lift.getMaintenanceUid();
            if (maintenanceUid != null) {
                maintenanceUid.getLiftCollection().remove(lift);
                maintenanceUid = em.merge(maintenanceUid);
            }
            Collection<Task> taskCollection = lift.getTaskCollection();
            for (Task taskCollectionTask : taskCollection) {
                taskCollectionTask.setLiftLid(null);
                taskCollectionTask = em.merge(taskCollectionTask);
            }
            Collection<LiftHistory> liftHistoryCollection = lift.getLiftHistoryCollection();
            for (LiftHistory liftHistoryCollectionLiftHistory : liftHistoryCollection) {
                liftHistoryCollectionLiftHistory.setLid(null);
                liftHistoryCollectionLiftHistory = em.merge(liftHistoryCollectionLiftHistory);
            }
            em.remove(lift);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lift> findLiftEntities() {
        return findLiftEntities(true, -1, -1);
    }

    public List<Lift> findLiftEntities(int maxResults, int firstResult) {
        return findLiftEntities(false, maxResults, firstResult);
    }

    private List<Lift> findLiftEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lift.class));
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

    public Lift findLift(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lift.class, id);
        } finally {
            em.close();
        }
    }

    public int getLiftCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lift> rt = cq.from(Lift.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
