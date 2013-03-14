/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ico.liftserver4demo.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Sean
 */
@Entity
@Table(name = "lift")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lift.findAll", query = "SELECT l FROM Lift l"),
    @NamedQuery(name = "Lift.findById", query = "SELECT l FROM Lift l WHERE l.id = :id"),
    @NamedQuery(name = "Lift.findByRegisterCode", query = "SELECT l FROM Lift l WHERE l.registerCode = :registerCode"),
    @NamedQuery(name = "Lift.findByUseCompany", query = "SELECT l FROM Lift l WHERE l.useCompany = :useCompany"),
    @NamedQuery(name = "Lift.findByAddress", query = "SELECT l FROM Lift l WHERE l.address = :address"),
    @NamedQuery(name = "Lift.findByCompanyCode", query = "SELECT l FROM Lift l WHERE l.companyCode = :companyCode"),
    @NamedQuery(name = "Lift.findByMaintenanceCompany", query = "SELECT l FROM Lift l WHERE l.maintenanceCompany = :maintenanceCompany"),
    @NamedQuery(name = "Lift.findByLatitude", query = "SELECT l FROM Lift l WHERE l.latitude = :latitude"),
    @NamedQuery(name = "Lift.findByLongitude", query = "SELECT l FROM Lift l WHERE l.longitude = :longitude")})
public class Lift implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "register_code")
    private String registerCode;
    @Column(name = "use_company")
    private String useCompany;
    @Column(name = "address")
    private String address;
    @Column(name = "company_code")
    private String companyCode;
    @Column(name = "maintenance_company")
    private String maintenanceCompany;
    @Column(name = "latitude")
    private Integer latitude;
    @Column(name = "longitude")
    private Integer longitude;
    @OneToMany(mappedBy = "liftLid")
    private Collection<Task> taskCollection;
    @JoinColumn(name = "self_check_uid", referencedColumnName = "id")
    @ManyToOne
    private User selfCheckUid;
    @JoinColumn(name = "maintenance_uid", referencedColumnName = "id")
    @ManyToOne
    private User maintenanceUid;
    @OneToMany(mappedBy = "lid")
    private Collection<LiftHistory> liftHistoryCollection;

    public Lift() {
    }

    public Lift(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getUseCompany() {
        return useCompany;
    }

    public void setUseCompany(String useCompany) {
        this.useCompany = useCompany;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getMaintenanceCompany() {
        return maintenanceCompany;
    }

    public void setMaintenanceCompany(String maintenanceCompany) {
        this.maintenanceCompany = maintenanceCompany;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Task> getTaskCollection() {
        return taskCollection;
    }

    public void setTaskCollection(Collection<Task> taskCollection) {
        this.taskCollection = taskCollection;
    }

    public User getSelfCheckUid() {
        return selfCheckUid;
    }

    public void setSelfCheckUid(User selfCheckUid) {
        this.selfCheckUid = selfCheckUid;
    }

    public User getMaintenanceUid() {
        return maintenanceUid;
    }

    public void setMaintenanceUid(User maintenanceUid) {
        this.maintenanceUid = maintenanceUid;
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
        if (!(object instanceof Lift)) {
            return false;
        }
        Lift other = (Lift) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ico.liftserver4demo.model.Lift[ id=" + id + " ]";
    }
    
}
