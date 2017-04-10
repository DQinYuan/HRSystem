package org.du.hrsystem.service;


import org.du.hrsystem.domain.Employee;
import org.du.hrsystem.exception.HrException;
import org.du.hrsystem.vo.AppBean;
import org.du.hrsystem.vo.EmpBean;
import org.du.hrsystem.vo.SalaryBean;

import java.util.List;

/**
 * @author duqinyuan
 * @version 1.0
 */
public interface MgrManager
{
	void addEmp(Employee emp, String mgr)
		throws HrException;

	List<SalaryBean> getSalaryByMgr(String mgr);

	List<EmpBean> getEmpsByMgr(String mgr);

	List<AppBean> getAppsByMgr(String mgr);

	void check(int appid, String mgrName, boolean result);
}