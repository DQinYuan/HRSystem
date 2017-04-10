package org.du.hrsystem.dao.impl;

import org.du.common.dao.impl.BaseDaoHibernate5;
import org.du.hrsystem.dao.AttendDao;
import org.du.hrsystem.domain.Attend;
import org.du.hrsystem.domain.AttendType;
import org.du.hrsystem.domain.Employee;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by duqinyuan on 2017/3/27.
 */
public class AttendDaoHibernate5 extends BaseDaoHibernate5<Attend> implements AttendDao{
    public List<Attend> findByEmpAndMonth(Employee emp, String month){
        return find("from Attend as a where a.employee=?0 " +
        "and substring(a.dutyDay, 0, 7)=?1", emp, month);
    }

    public List<Attend> findByEmpAndDutyDay(Employee emp, String dutyDay){
        return find("from Attend as a where a.employee=?0 and " +
        "a.dutyDay=?1", emp, dutyDay);
    }

    public Attend findByEmpAndDutyDayAndCome(Employee emp, String dutyDay, boolean isCome){
        List<Attend> al = findByEmpAndDutyDay(emp, dutyDay);
        if ( al != null && al.size() > 0 ){
            for (Attend attend : al) {
                if ( attend.isCome() == isCome ){
                    return  attend;
                }
            }
        }
        return null;
    }

    /**
     * 查看员工前三天的非正常打卡
     * @param emp 员工
     * @param type
     * @return 该员工前三天的非正常打卡
     */
    public List<Attend> findByEmpUnAttend(Employee emp, AttendType type){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String end = sdf.format(c.getTime());
        c.add(Calendar.DAY_OF_MONTH, -3);
        String start = sdf.format(c.getTime());
        return  find("from Attend as a where a.employee=?0 and " +
        "a.type != ?1 and a.dutyDay between ?2 and ?3", emp, type, start, end);
    }
}
