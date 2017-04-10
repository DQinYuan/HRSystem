package org.du.hrsystem.service.impl;


import org.du.hrsystem.dao.*;
import org.du.hrsystem.domain.*;
import org.du.hrsystem.exception.HrException;
import org.du.hrsystem.service.MgrManager;
import org.du.hrsystem.vo.AppBean;
import org.du.hrsystem.vo.EmpBean;
import org.du.hrsystem.vo.SalaryBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * @author duqinyuan
 * @version 1.0
 */
public class MgrManagerImpl
	implements MgrManager
{
	private ApplicationDao appDao;
	private AttendDao attendDao;
	private AttendTypeDao typeDao;
	private CheckBackDao checkDao;
	private EmployeeDao empDao;
	private ManagerDao mgrDao;
	private PaymentDao payDao;

	public void setAppDao(ApplicationDao appDao)
	{
		this.appDao = appDao;
	}

	public void setAttendDao(AttendDao attendDao)
	{
		this.attendDao = attendDao;
	}

	public void setTypeDao(AttendTypeDao typeDao)
	{
		this.typeDao = typeDao;
	}

	public void setCheckDao(CheckBackDao checkDao)
	{
		this.checkDao = checkDao;
	}

	public void setEmpDao(EmployeeDao empDao)
	{
		this.empDao = empDao;
	}

	public void setMgrDao(ManagerDao mgrDao)
	{
		this.mgrDao = mgrDao;
	}

	public void setPayDao(PaymentDao payDao)
	{
		this.payDao = payDao;
	}

	public void addEmp(Employee emp , String mgr)throws HrException
	{
		Manager m = mgrDao.findByName(mgr);
		if (m == null)
		{
			throw new HrException("你是经理吗？或你还未登录？");
		}
		emp.setManager(m);
		empDao.save(emp);
	}

	public List<SalaryBean> getSalaryByMgr(String mgr)throws HrException
	{
		Manager m = mgrDao.findByName(mgr);
		if (m == null)
		{
			throw new HrException("你是经理吗？或你还未登录？");
		}
		Set<Employee> emps = m.getEmployees();
		if (emps == null || emps.size() < 1)
		{
			throw new HrException("您的部门没有员工");
		}
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH , -1);
		SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM");
		String payMonth = sdf.format(c.getTime());
		List<SalaryBean> result = new ArrayList<SalaryBean>();
		for (Employee e : emps)
		{
			Payment p = payDao.findByMonthAndEmp(payMonth , e);
			if (p != null)
			{
				result.add(new SalaryBean(e.getName()
					, p.getAmount()));
			}
		}
		return result;
	}

	public List<EmpBean> getEmpsByMgr(String mgr)
		throws HrException
	{
		Manager m = mgrDao.findByName(mgr);
		if (m == null)
		{
			throw new HrException("你是经理吗？或你还未登录？");
		}
		Set<Employee> emps = m.getEmployees();
		if (emps == null || emps.size() < 1)
		{
			throw new HrException("您的部门没有员工");
		}
		List<EmpBean> result = new ArrayList<EmpBean>();
		for (Employee e : emps)
		{
			result.add(new EmpBean(e.getName() ,
				e.getPass() , e.getSalary()));
		}
		return result;
	}

	public List<AppBean> getAppsByMgr(String mgr)throws HrException
	{
		Manager m = mgrDao.findByName(mgr);
		if (m == null)
		{
			throw new HrException("你是经理吗？或你还未登录？");
		}
		Set<Employee> emps = m.getEmployees();
		if (emps == null || emps.size() < 1)
		{
			throw new HrException("您的部门没有员工");
		}
		List<AppBean> result = new ArrayList<AppBean>();
		for (Employee e : emps)
		{
			List<Application> apps = appDao.findByEmp(e);
			if (apps != null && apps.size() > 0)
			{
				for (Application app : apps)
				{
					if (app.getResult() == false)
					{
						Attend attend = app.getAttend();
						result.add(new AppBean(app.getId() ,
							e.getName(), attend.getType().getName(),
							app.getType().getName(), app.getReason()));
					}
				}
			}
		}
		return result;
	}

	public void check(int appid, String mgrName, boolean result)
	{
		Application app = appDao.get(Application.class , appid);
		CheckBack check = new CheckBack();
		check.setApp(app);
		Manager manager = mgrDao.findByName(mgrName);
		if (manager == null)
		{
			throw new HrException("你是经理吗？或你还未登录？");
		}
		check.setManager(manager);
		if (result)
		{
			check.setResult(true);

			app.setResult(true);
			appDao.update(app);
			Attend attend = app.getAttend();
			attend.setType(app.getType());
			attendDao.update(attend);
		}
		else
		{
			check.setResult(false);
			app.setResult(true);
			appDao.update(app);
		}
		checkDao.save(check);
	}
}
