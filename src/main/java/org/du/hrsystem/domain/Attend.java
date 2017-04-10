package org.du.hrsystem.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/21.
 */
@Entity
@Table(name = "attend_inf")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Attend implements Serializable{
    private static final long serialVersionUID = 48L;
    @Id
    @Column(name = "attend_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "duty_day", nullable = false, length = 50)
    private String dutyDay;
    @Column(name="punch_time")
    private Date punchTime;
    @Column(name = "is_come", nullable = false)
    private boolean isCome;
    @ManyToOne(targetEntity = AttendType.class)
    @JoinColumn(name = "type_id", nullable = false)
    private AttendType type;
    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    public Attend() {}

    public Attend(int id, String dutyDay, Date punchTime, boolean isCome, AttendType type, Employee employee) {
        this.id = id;
        this.dutyDay = dutyDay;
        this.punchTime = punchTime;
        this.isCome = isCome;
        this.type = type;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDutyDay() {
        return dutyDay;
    }

    public void setDutyDay(String dutyDay) {
        this.dutyDay = dutyDay;
    }

    public Date getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(Date punchTime) {
        this.punchTime = punchTime;
    }

    public boolean isCome() {
        return isCome;
    }

    public void setCome(boolean come) {
        isCome = come;
    }

    public AttendType getType() {
        return type;
    }

    public void setType(AttendType type) {
        this.type = type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dutyDay == null) ? 0 : dutyDay.hashCode());
        result = prime * result + ((employee == null) ? 0 : employee.hashCode());
        result = prime * result + (isCome ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if ( this == obj ) return  true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        Attend other = (Attend) obj;

        if ( dutyDay == null ){
            if ( other.getDutyDay() != null ) return false;
        }
        else if ( !dutyDay.equals(other.getDutyDay()) ) return false;

        if ( employee == null ){
            if ( other.getEmployee() != null ) return  false;
        }
        else if ( !employee.equals(other.getEmployee()) ) return false;

        if ( isCome != other.isCome() ) return false;

        return true;
    }
}
