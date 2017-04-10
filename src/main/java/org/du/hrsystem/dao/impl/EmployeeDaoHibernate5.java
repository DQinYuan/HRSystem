package org.du.hrsystem.dao.impl;

import org.du.common.dao.impl.BaseDaoHibernate5;
import org.du.hrsystem.dao.EmployeeDao;
import org.du.hrsystem.domain.Employee;

import java.util.List;

/**
 * Created by duqinyuan on 2017/3/27.
 */
public class EmployeeDaoHibernate5 extends BaseDaoHibernate5<Employee> implements EmployeeDao {
    public List<Employee> findByNameAndPass(Employee emp) {
        return find("select p from Employee p where p.name=?0 and p.pass=?1", emp.getName(), emp.getPass());
    }

    public Employee findByName(String name) {
        List<Employee> emps = find("select e from Employee e where e.name=?0", name);
        if (emps != null && emps.size() >= 1) {
            return emps.get(0);
        }
        return null;
    }
}
