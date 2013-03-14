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
@Table(name = "lift_history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LiftHistory.findAll", query = "SELECT l FROM LiftHistory l"),
    @NamedQuery(name = "LiftHistory.findById", query = "SELECT l FROM LiftHistory l WHERE l.id = :id"),
    @NamedQuery(name = "LiftHistory.findByItemName", query = "SELECT l FROM LiftHistory l WHERE l.itemName = :itemName"),
    @NamedQuery(name = "LiftHistory.findByItemOld", query = "SELECT l FROM LiftHistory l WHERE l.itemOld = :itemOld"),
    @NamedQuery(name = "LiftHistory.findByItemNew", query = "SELECT l FROM LiftHistory l WHERE l.itemNew = :itemNew"),
    @NamedQuery(name = "LiftHistory.findByUpdateTime", query = "SELECT l FROM LiftHistory l WHERE l.updateTime = :updateTime")})
public class LiftHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "item_old")
    private String itemOld;
    @Column(name = "item_new")
    private String itemNew;
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @JoinColumn(name = "update_uid", referencedColumnName = "id")
    @ManyToOne
    private User updateUid;
    @JoinColumn(name = "lid", referencedColumnName = "id")
    @ManyToOne
    private Lift lid;

    public LiftHistory() {
    }

    public LiftHistory(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemOld() {
        return itemOld;
    }

    public void setItemOld(String itemOld) {
        this.itemOld = itemOld;
    }

    public String getItemNew() {
        return itemNew;
    }

    public void setItemNew(String itemNew) {
        this.itemNew = itemNew;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public User getUpdateUid() {
        return updateUid;
    }

    public void setUpdateUid(User updateUid) {
        this.updateUid = updateUid;
    }

    public Lift getLid() {
        return lid;
    }

    public void setLid(Lift lid) {
        this.lid = lid;
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
        if (!(object instanceof LiftHistory)) {
            return false;
        }
        LiftHistory other = (LiftHistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ico.liftserver4demo.model.LiftHistory[ id=" + id + " ]";
    }
    
}
