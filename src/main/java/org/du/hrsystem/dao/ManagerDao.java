package org.du.hrsystem.dao;

import org.du.common.dao.BaseDao;
import org.du.hrsystem.domain.Manager;

import java.util.List;

/**
 * Created by duqinyuan on 2017/3/24.
 */
public interface ManagerDao extends BaseDao<Manager> {
    /**
     *
     * @param mgr
     * @return
     */
    List<Manager> findByNameAndPass(Manager mgr);

    /**
     *
     * @param name
     * @return
     */
    Manager findByName(String name);
}
