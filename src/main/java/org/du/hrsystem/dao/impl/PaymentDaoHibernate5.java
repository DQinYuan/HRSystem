package org.du.hrsystem.dao.impl;

import java.util.*;

import org.du.common.dao.impl.BaseDaoHibernate5;
import org.du.hrsystem.dao.PaymentDao;
import org.du.hrsystem.domain.Employee;
import org.du.hrsystem.domain.Payment;

/**
 * Created by duqinyuan
 */
public class PaymentDaoHibernate5 extends BaseDaoHibernate5<Payment>
	implements PaymentDao
{

	public List<Payment> findByEmp(Employee emp)
	{
		return find("select p from Payment as p where p.employee=?0" , emp);
	}

	public Payment findByMonthAndEmp(String payMonth
		 , Employee emp)
	{
		List<Payment> pays = find("select p from Payment as p where"
			+ " p.employee=?0 and p.payMonth=?1" , emp , payMonth);
		if (pays != null && pays.size() > 0)
		{
			return pays.get(0);
		}
		return null;
	}
}
