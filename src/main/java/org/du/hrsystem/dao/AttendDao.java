package org.du.hrsystem.dao;

import org.du.common.dao.BaseDao;
import org.du.hrsystem.domain.Attend;
import org.du.hrsystem.domain.AttendType;
import org.du.hrsystem.domain.Employee;

import java.util.List;

/**
 * Created by duqinyuan on 2017/3/24.
 */
public interface AttendDao extends BaseDao<Attend> {
    /**
     * 根据员工，月份查询该员工的出勤记录
     * @param emp 员工
     * @param month 月份
     * @return 该员工，指定月份的全出勤记录
     */
    List<Attend> findByEmpAndMonth(Employee emp, String month);

    /**
     *
     * @param emp
     * @param dutyDay
     * @return
     */
    List<Attend> findByEmpAndDutyDay(Employee emp, String dutyDay);

    /**
     *
     * @param emp
     * @param dutyDay
     * @param isCome
     * @return
     */
    Attend findByEmpAndDutyDayAndCome(Employee emp, String dutyDay, boolean isCome);

    /**
     * 查看员工前三天的非正常打卡
     * @param emp
     * @param type
     * @return
     */
    List<Attend> findByEmpUnAttend(Employee emp, AttendType type);
}
