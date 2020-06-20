package com.bushi.diseasereportserver.pojo;

import javax.persistence.*;

@Entity
@Table(name = "hospitalchart")
public class Chart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "idnumber")
    private String idNumber;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "date")
    private String date;

    @Column(name = "description")
    private String description;

    @Column(name = "level")
    private int level = 0;

    @Column(name = "superlevel")
    private int superlevel = 0;

    public Chart(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSuperlevel() {
        return superlevel;
    }

    public void setSuperlevel(int superlevel) {
        this.superlevel = superlevel;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return "{" + "id:" + id + ", " + "name:" + name + ", " + "age:" + age + ", " + "idNumber:" + idNumber + ", "
                +"phoneNumber:" + phoneNumber + ", " + "address:" + address + ", " + "date:" + date + ", " +
                "description:" + description + "}";
    }
}
