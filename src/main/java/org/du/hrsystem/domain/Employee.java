package org.du.hrsystem.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by duqinyuan on 2017/3/21.
 */

@Entity
@Table(name="employee_inf")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DiscriminatorColumn(name="emp_type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "1")
public class Employee implements Serializable{

    private static final long serialVersionUID = 48L;
    @Id
    @Column(name = "emp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "emp_name", nullable = false, length = 50)
    private String name;

    @Column(name = "emp_pass", nullable = false, length = 50)
    private String pass;

    @Column(name = "emp_salary", nullable = false)
    private double salary;

    @ManyToOne(targetEntity = Manager.class)
    @JoinColumn(name = "mgr_id")
    private Manager manager;

    @OneToMany(targetEntity = Attend.class, mappedBy = "employee")
    private Set<Attend> attends = new HashSet<Attend>();

    @OneToMany(targetEntity = Payment.class, mappedBy  = "employee")
    private Set<Payment> payments = new HashSet<Payment>();

    public Employee(){}

    public Employee(int id, String name, String pass, double salary, Manager manager, Set<Attend> attends, Set<Payment> payments) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.salary = salary;
        this.manager = manager;
        this.attends = attends;
        this.payments = payments;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((pass == null) ? 0 : pass.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if ( this == obj ) return  true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        Employee other = (Employee) obj;

        if ( name == null ){
            if ( other.getName() != null ) return false;
        }
        else if ( !name.equals(other.getName()) ) return false;

        if ( pass == null ){
            if ( other.getPass() != null ) return  false;
        }
        else if ( !pass.equals(other.getPass()) ) return false;

        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Set<Attend> getAttends() {
        return attends;
    }

    public void setAttends(Set<Attend> attends) {
        this.attends = attends;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }
}
