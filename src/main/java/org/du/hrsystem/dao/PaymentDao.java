package org.du.hrsystem.dao;

import org.du.common.dao.BaseDao;
import org.du.hrsystem.domain.Employee;
import org.du.hrsystem.domain.Payment;

import java.util.List;

/**
 * Created by duqinyuan on 2017/3/24.
 */
public interface PaymentDao extends BaseDao<Payment> {
    /**
     * 根据员工查询月结薪水
     * @param emp
     * @return 该员工对应的月结薪水集合
     */
    List<Payment> findByEmp(Employee emp);

    /**
     *
     * @param payMonth
     * @param emp
     * @return
     */
    Payment findByMonthAndEmp(String payMonth, Employee emp);
}
