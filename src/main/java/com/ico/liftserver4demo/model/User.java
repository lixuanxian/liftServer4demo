/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Sean
 */
@Entity
@Table(name = "User")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.loginWithEmailAndPassword",
    query = "SELECT u FROM User u WHERE u.password = :password and u.email = :email"),
       
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPhoneNumber", query = "SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "User.findByType", query = "SELECT u FROM User u WHERE u.type = :type"),
    @NamedQuery(name = "User.findByApnsToken", query = "SELECT u FROM User u WHERE u.apnsToken = :apnsToken"),
    @NamedQuery(name = "User.findByActive", query = "SELECT u FROM User u WHERE u.active = :active"),
    @NamedQuery(name = "User.findByAgreeNotice", query = "SELECT u FROM User u WHERE u.agreeNotice = :agreeNotice"),
    @NamedQuery(name = "User.findByRegisterTime", query = "SELECT u FROM User u WHERE u.registerTime = :registerTime")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "type")
    private Integer type;
    @Column(name = "apns_token")
    private String apnsToken;
    @Column(name = "active")
    private Integer active;
    @Column(name = "agree_notice")
    private Integer agreeNotice;
    @Column(name = "register_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerTime;
    @OneToMany(mappedBy = "sendUid")
    private Collection<Task> taskCollection;
    @OneToMany(mappedBy = "createUid")
    private Collection<Task> taskCollection1;
    @OneToMany(mappedBy = "selfCheckUid")
    private Collection<Lift> liftCollection;
    @OneToMany(mappedBy = "maintenanceUid")
    private Collection<Lift> liftCollection1;
    @OneToMany(mappedBy = "updateUid")
    private Collection<LiftHistory> liftHistoryCollection;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getApnsToken() {
        return apnsToken;
    }

    public void setApnsToken(String apnsToken) {
        this.apnsToken = apnsToken;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getAgreeNotice() {
        return agreeNotice;
    }

    public void setAgreeNotice(Integer agreeNotice) {
        this.agreeNotice = agreeNotice;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Task> getTaskCollection() {
        return taskCollection;
    }

    public void setTaskCollection(Collection<Task> taskCollection) {
        this.taskCollection = taskCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Task> getTaskCollection1() {
        return taskCollection1;
    }

    public void setTaskCollection1(Collection<Task> taskCollection1) {
        this.taskCollection1 = taskCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Lift> getLiftCollection() {
        return liftCollection;
    }

    public void setLiftCollection(Collection<Lift> liftCollection) {
        this.liftCollection = liftCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Lift> getLiftCollection1() {
        return liftCollection1;
    }

    public void setLiftCollection1(Collection<Lift> liftCollection1) {
        this.liftCollection1 = liftCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<LiftHistory> getLiftHistoryCollection() {
        return liftHistoryCollection;
    }

    public void setLiftHistoryCollection(Collection<LiftHistory> liftHistoryCollection) {
        this.liftHistoryCollection = liftHistoryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ico.liftserver4demo.model.User[ id=" + id + " ]";
    }
    
}
