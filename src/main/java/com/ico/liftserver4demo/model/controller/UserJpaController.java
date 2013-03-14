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
import com.ico.liftserver4demo.model.Task;
import java.util.ArrayList;
import java.util.Collection;
import com.ico.liftserver4demo.model.Lift;
import com.ico.liftserver4demo.model.LiftHistory;
import com.ico.liftserver4demo.model.User;
import com.ico.liftserver4demo.model.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sean
 */
public class UserJpaController implements Serializable {

    public UserJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

        public User loginUser(String email, String password) {
        EntityManager em = getEntityManager();
        User user = null;
        try {
            Query query = em.createNamedQuery("User.loginWithEmailAndPassword");//根据User实体中定义的命名查询
            query.setParameter("email", email);
            query.setParameter("password", password);

            user = (User) query.getSingleResult();
        } catch (NoResultException e) {
           user = new User(-1, "email", "password");
        } finally {
            em.close();
        }
        return  user;
    }
    
//    public void create(User user) {
//        if (user.getTaskCollection() == null) {
//            user.setTaskCollection(new ArrayList<Task>());
//        }
//        if (user.getTaskCollection1() == null) {
//            user.setTaskCollection1(new ArrayList<Task>());
//        }
//        if (user.getLiftCollection() == null) {
//            user.setLiftCollection(new ArrayList<Lift>());
//        }
//        if (user.getLiftCollection1() == null) {
//            user.setLiftCollection1(new ArrayList<Lift>());
//        }
//        if (user.getLiftHistoryCollection() == null) {
//            user.setLiftHistoryCollection(new ArrayList<LiftHistory>());
//        }
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Collection<Task> attachedTaskCollection = new ArrayList<Task>();
//            for (Task taskCollectionTaskToAttach : user.getTaskCollection()) {
//                taskCollectionTaskToAttach = em.getReference(taskCollectionTaskToAttach.getClass(), taskCollectionTaskToAttach.getId());
//                attachedTaskCollection.add(taskCollectionTaskToAttach);
//            }
//            user.setTaskCollection(attachedTaskCollection);
//            Collection<Task> attachedTaskCollection1 = new ArrayList<Task>();
//            for (Task taskCollection1TaskToAttach : user.getTaskCollection1()) {
//                taskCollection1TaskToAttach = em.getReference(taskCollection1TaskToAttach.getClass(), taskCollection1TaskToAttach.getId());
//                attachedTaskCollection1.add(taskCollection1TaskToAttach);
//            }
//            user.setTaskCollection1(attachedTaskCollection1);
//            Collection<Lift> attachedLiftCollection = new ArrayList<Lift>();
//            for (Lift liftCollectionLiftToAttach : user.getLiftCollection()) {
//                liftCollectionLiftToAttach = em.getReference(liftCollectionLiftToAttach.getClass(), liftCollectionLiftToAttach.getId());
//                attachedLiftCollection.add(liftCollectionLiftToAttach);
//            }
//            user.setLiftCollection(attachedLiftCollection);
//            Collection<Lift> attachedLiftCollection1 = new ArrayList<Lift>();
//            for (Lift liftCollection1LiftToAttach : user.getLiftCollection1()) {
//                liftCollection1LiftToAttach = em.getReference(liftCollection1LiftToAttach.getClass(), liftCollection1LiftToAttach.getId());
//                attachedLiftCollection1.add(liftCollection1LiftToAttach);
//            }
//            user.setLiftCollection1(attachedLiftCollection1);
//            Collection<LiftHistory> attachedLiftHistoryCollection = new ArrayList<LiftHistory>();
//            for (LiftHistory liftHistoryCollectionLiftHistoryToAttach : user.getLiftHistoryCollection()) {
//                liftHistoryCollectionLiftHistoryToAttach = em.getReference(liftHistoryCollectionLiftHistoryToAttach.getClass(), liftHistoryCollectionLiftHistoryToAttach.getId());
//                attachedLiftHistoryCollection.add(liftHistoryCollectionLiftHistoryToAttach);
//            }
//            user.setLiftHistoryCollection(attachedLiftHistoryCollection);
//            em.persist(user);
//            for (Task taskCollectionTask : user.getTaskCollection()) {
//                User oldSendUidOfTaskCollectionTask = taskCollectionTask.getSendUid();
//                taskCollectionTask.setSendUid(user);
//                taskCollectionTask = em.merge(taskCollectionTask);
//                if (oldSendUidOfTaskCollectionTask != null) {
//                    oldSendUidOfTaskCollectionTask.getTaskCollection().remove(taskCollectionTask);
//                    oldSendUidOfTaskCollectionTask = em.merge(oldSendUidOfTaskCollectionTask);
//                }
//            }
//            for (Task taskCollection1Task : user.getTaskCollection1()) {
//                User oldCreateUidOfTaskCollection1Task = taskCollection1Task.getCreateUid();
//                taskCollection1Task.setCreateUid(user);
//                taskCollection1Task = em.merge(taskCollection1Task);
//                if (oldCreateUidOfTaskCollection1Task != null) {
//                    oldCreateUidOfTaskCollection1Task.getTaskCollection1().remove(taskCollection1Task);
//                    oldCreateUidOfTaskCollection1Task = em.merge(oldCreateUidOfTaskCollection1Task);
//                }
//            }
//            for (Lift liftCollectionLift : user.getLiftCollection()) {
//                User oldSelfCheckUidOfLiftCollectionLift = liftCollectionLift.getSelfCheckUid();
//                liftCollectionLift.setSelfCheckUid(user);
//                liftCollectionLift = em.merge(liftCollectionLift);
//                if (oldSelfCheckUidOfLiftCollectionLift != null) {
//                    oldSelfCheckUidOfLiftCollectionLift.getLiftCollection().remove(liftCollectionLift);
//                    oldSelfCheckUidOfLiftCollectionLift = em.merge(oldSelfCheckUidOfLiftCollectionLift);
//                }
//            }
//            for (Lift liftCollection1Lift : user.getLiftCollection1()) {
//                User oldMaintenanceUidOfLiftCollection1Lift = liftCollection1Lift.getMaintenanceUid();
//                liftCollection1Lift.setMaintenanceUid(user);
//                liftCollection1Lift = em.merge(liftCollection1Lift);
//                if (oldMaintenanceUidOfLiftCollection1Lift != null) {
//                    oldMaintenanceUidOfLiftCollection1Lift.getLiftCollection1().remove(liftCollection1Lift);
//                    oldMaintenanceUidOfLiftCollection1Lift = em.merge(oldMaintenanceUidOfLiftCollection1Lift);
//                }
//            }
//            for (LiftHistory liftHistoryCollectionLiftHistory : user.getLiftHistoryCollection()) {
//                User oldUpdateUidOfLiftHistoryCollectionLiftHistory = liftHistoryCollectionLiftHistory.getUpdateUid();
//                liftHistoryCollectionLiftHistory.setUpdateUid(user);
//                liftHistoryCollectionLiftHistory = em.merge(liftHistoryCollectionLiftHistory);
//                if (oldUpdateUidOfLiftHistoryCollectionLiftHistory != null) {
//                    oldUpdateUidOfLiftHistoryCollectionLiftHistory.getLiftHistoryCollection().remove(liftHistoryCollectionLiftHistory);
//                    oldUpdateUidOfLiftHistoryCollectionLiftHistory = em.merge(oldUpdateUidOfLiftHistoryCollectionLiftHistory);
//                }
//            }
//            em.getTransaction().commit();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public void edit(User user) throws NonexistentEntityException, Exception {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            User persistentUser = em.find(User.class, user.getId());
//            Collection<Task> taskCollectionOld = persistentUser.getTaskCollection();
//            Collection<Task> taskCollectionNew = user.getTaskCollection();
//            Collection<Task> taskCollection1Old = persistentUser.getTaskCollection1();
//            Collection<Task> taskCollection1New = user.getTaskCollection1();
//            Collection<Lift> liftCollectionOld = persistentUser.getLiftCollection();
//            Collection<Lift> liftCollectionNew = user.getLiftCollection();
//            Collection<Lift> liftCollection1Old = persistentUser.getLiftCollection1();
//            Collection<Lift> liftCollection1New = user.getLiftCollection1();
//            Collection<LiftHistory> liftHistoryCollectionOld = persistentUser.getLiftHistoryCollection();
//            Collection<LiftHistory> liftHistoryCollectionNew = user.getLiftHistoryCollection();
//            Collection<Task> attachedTaskCollectionNew = new ArrayList<Task>();
//            for (Task taskCollectionNewTaskToAttach : taskCollectionNew) {
//                taskCollectionNewTaskToAttach = em.getReference(taskCollectionNewTaskToAttach.getClass(), taskCollectionNewTaskToAttach.getId());
//                attachedTaskCollectionNew.add(taskCollectionNewTaskToAttach);
//            }
//            taskCollectionNew = attachedTaskCollectionNew;
//            user.setTaskCollection(taskCollectionNew);
//            Collection<Task> attachedTaskCollection1New = new ArrayList<Task>();
//            for (Task taskCollection1NewTaskToAttach : taskCollection1New) {
//                taskCollection1NewTaskToAttach = em.getReference(taskCollection1NewTaskToAttach.getClass(), taskCollection1NewTaskToAttach.getId());
//                attachedTaskCollection1New.add(taskCollection1NewTaskToAttach);
//            }
//            taskCollection1New = attachedTaskCollection1New;
//            user.setTaskCollection1(taskCollection1New);
//            Collection<Lift> attachedLiftCollectionNew = new ArrayList<Lift>();
//            for (Lift liftCollectionNewLiftToAttach : liftCollectionNew) {
//                liftCollectionNewLiftToAttach = em.getReference(liftCollectionNewLiftToAttach.getClass(), liftCollectionNewLiftToAttach.getId());
//                attachedLiftCollectionNew.add(liftCollectionNewLiftToAttach);
//            }
//            liftCollectionNew = attachedLiftCollectionNew;
//            user.setLiftCollection(liftCollectionNew);
//            Collection<Lift> attachedLiftCollection1New = new ArrayList<Lift>();
//            for (Lift liftCollection1NewLiftToAttach : liftCollection1New) {
//                liftCollection1NewLiftToAttach = em.getReference(liftCollection1NewLiftToAttach.getClass(), liftCollection1NewLiftToAttach.getId());
//                attachedLiftCollection1New.add(liftCollection1NewLiftToAttach);
//            }
//            liftCollection1New = attachedLiftCollection1New;
//            user.setLiftCollection1(liftCollection1New);
//            Collection<LiftHistory> attachedLiftHistoryCollectionNew = new ArrayList<LiftHistory>();
//            for (LiftHistory liftHistoryCollectionNewLiftHistoryToAttach : liftHistoryCollectionNew) {
//                liftHistoryCollectionNewLiftHistoryToAttach = em.getReference(liftHistoryCollectionNewLiftHistoryToAttach.getClass(), liftHistoryCollectionNewLiftHistoryToAttach.getId());
//                attachedLiftHistoryCollectionNew.add(liftHistoryCollectionNewLiftHistoryToAttach);
//            }
//            liftHistoryCollectionNew = attachedLiftHistoryCollectionNew;
//            user.setLiftHistoryCollection(liftHistoryCollectionNew);
//            user = em.merge(user);
//            for (Task taskCollectionOldTask : taskCollectionOld) {
//                if (!taskCollectionNew.contains(taskCollectionOldTask)) {
//                    taskCollectionOldTask.setSendUid(null);
//                    taskCollectionOldTask = em.merge(taskCollectionOldTask);
//                }
//            }
//            for (Task taskCollectionNewTask : taskCollectionNew) {
//                if (!taskCollectionOld.contains(taskCollectionNewTask)) {
//                    User oldSendUidOfTaskCollectionNewTask = taskCollectionNewTask.getSendUid();
//                    taskCollectionNewTask.setSendUid(user);
//                    taskCollectionNewTask = em.merge(taskCollectionNewTask);
//                    if (oldSendUidOfTaskCollectionNewTask != null && !oldSendUidOfTaskCollectionNewTask.equals(user)) {
//                        oldSendUidOfTaskCollectionNewTask.getTaskCollection().remove(taskCollectionNewTask);
//                        oldSendUidOfTaskCollectionNewTask = em.merge(oldSendUidOfTaskCollectionNewTask);
//                    }
//                }
//            }
//            for (Task taskCollection1OldTask : taskCollection1Old) {
//                if (!taskCollection1New.contains(taskCollection1OldTask)) {
//                    taskCollection1OldTask.setCreateUid(null);
//                    taskCollection1OldTask = em.merge(taskCollection1OldTask);
//                }
//            }
//            for (Task taskCollection1NewTask : taskCollection1New) {
//                if (!taskCollection1Old.contains(taskCollection1NewTask)) {
//                    User oldCreateUidOfTaskCollection1NewTask = taskCollection1NewTask.getCreateUid();
//                    taskCollection1NewTask.setCreateUid(user);
//                    taskCollection1NewTask = em.merge(taskCollection1NewTask);
//                    if (oldCreateUidOfTaskCollection1NewTask != null && !oldCreateUidOfTaskCollection1NewTask.equals(user)) {
//                        oldCreateUidOfTaskCollection1NewTask.getTaskCollection1().remove(taskCollection1NewTask);
//                        oldCreateUidOfTaskCollection1NewTask = em.merge(oldCreateUidOfTaskCollection1NewTask);
//                    }
//                }
//            }
//            for (Lift liftCollectionOldLift : liftCollectionOld) {
//                if (!liftCollectionNew.contains(liftCollectionOldLift)) {
//                    liftCollectionOldLift.setSelfCheckUid(null);
//                    liftCollectionOldLift = em.merge(liftCollectionOldLift);
//                }
//            }
//            for (Lift liftCollectionNewLift : liftCollectionNew) {
//                if (!liftCollectionOld.contains(liftCollectionNewLift)) {
//                    User oldSelfCheckUidOfLiftCollectionNewLift = liftCollectionNewLift.getSelfCheckUid();
//                    liftCollectionNewLift.setSelfCheckUid(user);
//                    liftCollectionNewLift = em.merge(liftCollectionNewLift);
//                    if (oldSelfCheckUidOfLiftCollectionNewLift != null && !oldSelfCheckUidOfLiftCollectionNewLift.equals(user)) {
//                        oldSelfCheckUidOfLiftCollectionNewLift.getLiftCollection().remove(liftCollectionNewLift);
//                        oldSelfCheckUidOfLiftCollectionNewLift = em.merge(oldSelfCheckUidOfLiftCollectionNewLift);
//                    }
//                }
//            }
//            for (Lift liftCollection1OldLift : liftCollection1Old) {
//                if (!liftCollection1New.contains(liftCollection1OldLift)) {
//                    liftCollection1OldLift.setMaintenanceUid(null);
//                    liftCollection1OldLift = em.merge(liftCollection1OldLift);
//                }
//            }
//            for (Lift liftCollection1NewLift : liftCollection1New) {
//                if (!liftCollection1Old.contains(liftCollection1NewLift)) {
//                    User oldMaintenanceUidOfLiftCollection1NewLift = liftCollection1NewLift.getMaintenanceUid();
//                    liftCollection1NewLift.setMaintenanceUid(user);
//                    liftCollection1NewLift = em.merge(liftCollection1NewLift);
//                    if (oldMaintenanceUidOfLiftCollection1NewLift != null && !oldMaintenanceUidOfLiftCollection1NewLift.equals(user)) {
//                        oldMaintenanceUidOfLiftCollection1NewLift.getLiftCollection1().remove(liftCollection1NewLift);
//                        oldMaintenanceUidOfLiftCollection1NewLift = em.merge(oldMaintenanceUidOfLiftCollection1NewLift);
//                    }
//                }
//            }
//            for (LiftHistory liftHistoryCollectionOldLiftHistory : liftHistoryCollectionOld) {
//                if (!liftHistoryCollectionNew.contains(liftHistoryCollectionOldLiftHistory)) {
//                    liftHistoryCollectionOldLiftHistory.setUpdateUid(null);
//                    liftHistoryCollectionOldLiftHistory = em.merge(liftHistoryCollectionOldLiftHistory);
//                }
//            }
//            for (LiftHistory liftHistoryCollectionNewLiftHistory : liftHistoryCollectionNew) {
//                if (!liftHistoryCollectionOld.contains(liftHistoryCollectionNewLiftHistory)) {
//                    User oldUpdateUidOfLiftHistoryCollectionNewLiftHistory = liftHistoryCollectionNewLiftHistory.getUpdateUid();
//                    liftHistoryCollectionNewLiftHistory.setUpdateUid(user);
//                    liftHistoryCollectionNewLiftHistory = em.merge(liftHistoryCollectionNewLiftHistory);
//                    if (oldUpdateUidOfLiftHistoryCollectionNewLiftHistory != null && !oldUpdateUidOfLiftHistoryCollectionNewLiftHistory.equals(user)) {
//                        oldUpdateUidOfLiftHistoryCollectionNewLiftHistory.getLiftHistoryCollection().remove(liftHistoryCollectionNewLiftHistory);
//                        oldUpdateUidOfLiftHistoryCollectionNewLiftHistory = em.merge(oldUpdateUidOfLiftHistoryCollectionNewLiftHistory);
//                    }
//                }
//            }
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            String msg = ex.getLocalizedMessage();
//            if (msg == null || msg.length() == 0) {
//                Integer id = user.getId();
//                if (findUser(id) == null) {
//                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
//                }
//            }
//            throw ex;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public void destroy(Integer id) throws NonexistentEntityException {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            User user;
//            try {
//                user = em.getReference(User.class, id);
//                user.getId();
//            } catch (EntityNotFoundException enfe) {
//                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
//            }
//            Collection<Task> taskCollection = user.getTaskCollection();
//            for (Task taskCollectionTask : taskCollection) {
//                taskCollectionTask.setSendUid(null);
//                taskCollectionTask = em.merge(taskCollectionTask);
//            }
//            Collection<Task> taskCollection1 = user.getTaskCollection1();
//            for (Task taskCollection1Task : taskCollection1) {
//                taskCollection1Task.setCreateUid(null);
//                taskCollection1Task = em.merge(taskCollection1Task);
//            }
//            Collection<Lift> liftCollection = user.getLiftCollection();
//            for (Lift liftCollectionLift : liftCollection) {
//                liftCollectionLift.setSelfCheckUid(null);
//                liftCollectionLift = em.merge(liftCollectionLift);
//            }
//            Collection<Lift> liftCollection1 = user.getLiftCollection1();
//            for (Lift liftCollection1Lift : liftCollection1) {
//                liftCollection1Lift.setMaintenanceUid(null);
//                liftCollection1Lift = em.merge(liftCollection1Lift);
//            }
//            Collection<LiftHistory> liftHistoryCollection = user.getLiftHistoryCollection();
//            for (LiftHistory liftHistoryCollectionLiftHistory : liftHistoryCollection) {
//                liftHistoryCollectionLiftHistory.setUpdateUid(null);
//                liftHistoryCollectionLiftHistory = em.merge(liftHistoryCollectionLiftHistory);
//            }
//            em.remove(user);
//            em.getTransaction().commit();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }
//
//    public int getUserCount() {
//        EntityManager em = getEntityManager();
//        try {
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            Root<User> rt = cq.from(User.class);
//            cq.select(em.getCriteriaBuilder().count(rt));
//            Query q = em.createQuery(cq);
//            return ((Long) q.getSingleResult()).intValue();
//        } finally {
//            em.close();
//        }
//    }
    
}
