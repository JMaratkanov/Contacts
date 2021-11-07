package com.Johann_M.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="contacts")
public class Contact {

    private Integer id;
    private String fName;
    private String lName;
    private String eMail;
    //private List<Adress> adresses;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fName")
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    @Basic
    @Column(name = "lName")
    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    @Basic
    @Column(name = "email")
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    /*
    public List<Adress> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Adress> adresses) {
        this.adresses = adresses;
    }*/
}
