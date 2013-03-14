/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sean
 */
@Entity
@Table(name = "task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"),
    @NamedQuery(name = "Task.findById", query = "SELECT t FROM Task t WHERE t.id = :id"),
    @NamedQuery(name = "Task.findByTitle", query = "SELECT t FROM Task t WHERE t.title = :title"),
    @NamedQuery(name = "Task.findByContent", query = "SELECT t FROM Task t WHERE t.content = :content"),
    @NamedQuery(name = "Task.findByPublishTime", query = "SELECT t FROM Task t WHERE t.publishTime = :publishTime"),
    @NamedQuery(name = "Task.findByReceivedTime", query = "SELECT t FROM Task t WHERE t.receivedTime = :receivedTime"),
    @NamedQuery(name = "Task.findByStartTime", query = "SELECT t FROM Task t WHERE t.startTime = :startTime"),
    @NamedQuery(name = "Task.findByStopTime", query = "SELECT t FROM Task t WHERE t.stopTime = :stopTime"),
    @NamedQuery(name = "Task.findByLiftState", query = "SELECT t FROM Task t WHERE t.liftState = :liftState")})
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "publish_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishTime;
    @Column(name = "received_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receivedTime;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "stop_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stopTime;
    @Column(name = "lift_state")
    private Integer liftState;
    @JoinColumn(name = "send_uid", referencedColumnName = "id")
    @ManyToOne
    private User sendUid;
    @JoinColumn(name = "lift_lid", referencedColumnName = "id")
    @ManyToOne
    private Lift liftLid;
    @JoinColumn(name = "create_uid", referencedColumnName = "id")
    @ManyToOne
    private User createUid;

    public Task() {
    }

    public Task(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public Integer getLiftState() {
        return liftState;
    }

    public void setLiftState(Integer liftState) {
        this.liftState = liftState;
    }

    public User getSendUid() {
        return sendUid;
    }

    public void setSendUid(User sendUid) {
        this.sendUid = sendUid;
    }

    public Lift getLiftLid() {
        return liftLid;
    }

    public void setLiftLid(Lift liftLid) {
        this.liftLid = liftLid;
    }

    public User getCreateUid() {
        return createUid;
    }

    public void setCreateUid(User createUid) {
        this.createUid = createUid;
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
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ico.liftserver4demo.model.Task[ id=" + id + " ]";
    }
    
}
