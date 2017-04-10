package org.du.hrsystem.dao;

import org.du.common.dao.BaseDao;
import org.du.hrsystem.domain.Application;
import org.du.hrsystem.domain.Employee;

import java.util.List;

/**
 * Created by duqinyuan on 2017/3/24.
 */
public interface ApplicationDao extends BaseDao<Application> {
    /**
     * 根据员工查询未处理的异动请求
     * @param emp 需要查询的员工
     * @return 该员工对应的未处理的异动申请
     */
    List<Application> findByEmp(Employee emp);
}
