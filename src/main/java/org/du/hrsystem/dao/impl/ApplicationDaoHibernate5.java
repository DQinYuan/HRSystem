package org.du.hrsystem.dao.impl;

import org.du.common.dao.impl.BaseDaoHibernate5;
import org.du.hrsystem.dao.ApplicationDao;
import org.du.hrsystem.domain.Application;
import org.du.hrsystem.domain.Employee;

import java.util.List;

/**
 * Created by duqinyuan on 2017/3/27.
 */
public class ApplicationDaoHibernate5 extends BaseDaoHibernate5<Application> implements ApplicationDao{
    /**
     * 根据员工查询未处理的异动申请
     * @param emp 需要查询的员工
     * @return 该员工对应的未处理的异动申请
     */
    public List<Application> findByEmp(Employee emp){
        return find("select a from Application as a where" +
        "a.attend.employee=?0", emp);
    }
}
