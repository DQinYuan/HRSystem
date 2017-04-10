package org.du.hrsystem.dao.impl;

import java.util.*;
import org.du.common.dao.impl.BaseDaoHibernate5;
import org.du.hrsystem.dao.ManagerDao;
import org.du.hrsystem.domain.Manager;

/**
 * Create by duqinyuan
 */
public class ManagerDaoHibernate5 extends BaseDaoHibernate5<Manager>
	implements ManagerDao
{
	public List<Manager> findByNameAndPass(Manager mgr)
	{
		return find("select m from Manager m where m.name = ?0 and m.pass=?1"
			, mgr.getName() , mgr.getPass());
	}

	public Manager findByName(String name)
	{
		List<Manager> ml = find("select m from Manager m where m.name=?0"
			, name);
		if (ml != null && ml.size() > 0)
		{
			return ml.get(0);
		}
		return null;
	}
}
