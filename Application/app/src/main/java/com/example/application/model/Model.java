package com.example.application.model;

public class Model {
    String id;
    String name;
    String position;
    String office;
    Double salary;

    public Model(String id, String name, String position, String office, Double salary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.office = office;
        this.salary = salary;
    }

    public Model(String name, String position, String office, Double salary) {
        this.name = name;
        this.position = position;
        this.office = office;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
