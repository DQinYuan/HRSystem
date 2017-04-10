package org.du.hrsystem.dao;

import org.du.common.dao.BaseDao;
import org.du.hrsystem.domain.Employee;

import java.util.List;

/**
 * Created by duqinyuan on 2017/3/24.
 */
public interface EmployeeDao extends BaseDao<Employee> {
    /**
     *
     * @param emp 包含指定用户名，密码的员工
     * @return
     */
    List<Employee> findByNameAndPass(Employee emp);

    /**
     *
     * @param name
     * @return
     */
    Employee findByName(String name);
}
