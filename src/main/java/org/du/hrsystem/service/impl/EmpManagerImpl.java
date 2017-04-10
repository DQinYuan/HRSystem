package org.du.hrsystem.service.impl;

import org.du.hrsystem.dao.*;
import org.du.hrsystem.domain.*;
import org.du.hrsystem.service.EmpManager;
import org.du.hrsystem.vo.AttendBean;
import org.du.hrsystem.vo.PaymentBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by duqinyuan on 2017/3/29.
 */
public class EmpManagerImpl implements EmpManager{
    private ApplicationDao appDao;
    private AttendDao attendDao;
    private AttendTypeDao typeDao;
    private CheckBackDao checkDao;
    private EmployeeDao empDao;
    private ManagerDao mgrDao;
    private PaymentDao payDao;

    /**
     * 以经理身份来验证登录
     * @param mgr 登录的经理身份
     * @return 登录后的身份确认：0 登录失败，1 登录为emp，2 登录为mgr
     */
    public int validLogin(Manager mgr) {
        if ( mgrDao.findByNameAndPass(mgr) != null && mgrDao.findByNameAndPass(mgr).size() >= 1 ){
            return LOGIN_MGR;
        }
        else if ( empDao.findByNameAndPass(mgr) != null && empDao.findByNameAndPass(mgr).size() >= 1){
            return LOGIN_EMP;
        } else {
            return LOGIN_FAIL;
        }
    }

    /**
     * 自动打卡，周一到周五，早上7点为每个员工插入旷工记录
     */
    public void autoPunch() {
        System.out.println("自动插入旷工记录");
        List<Employee> emps = empDao.findAll(Employee.class);
        String dutyDay = new Date(System.currentTimeMillis()).toString();
        for (Employee e: emps) {
            //获取旷工对应的出勤类型
            AttendType atype = typeDao.get(AttendType.class, 6);
            Attend a = new Attend();
            a.setDutyDay(dutyDay);
            a.setType(atype);
            if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < AM_LIMIT){
                a.setCome(true);
            }else {
                a.setCome(false);
            }
            a.setEmployee(e);
            attendDao.save(a);
        }
    }

    /**
     * 自动结算工资，每月一号，结算上月工资
     */
    public void autoPay() {
        System.out.println("自动插入工资结算");
        List<Employee> emps = empDao.findAll(Employee.class);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -15);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String payMonth = sdf.format(c.getTime());
        for (Employee e: emps) {
            Payment pay = new Payment();
            double amount = e.getSalary();
            List<Attend> attends = attendDao.findByEmpAndMonth(e, payMonth);
            for ( Attend a : attends ){
                amount += a.getType().getAmerce();
            }
            pay.setPayMonth(payMonth);
            pay.setEmployee(e);
            pay.setAmount(amount);
            payDao.save(pay);
        }
    }

    /**
     * 验证某个员工是否可打卡
     * @param user 员工名
     * @param dutyDay 日期
     * @return 可打卡的类别
     */
    public int validPunch(String user, String dutyDay) {
        Employee emp = empDao.findByName(user);
        if ( emp == null ){
            return NO_PUNCH;
        }
        List<Attend> attends = attendDao.findByEmpAndDutyDay(emp, dutyDay);
        if ( attends == null || attends.size() <= 0 ){
            return NO_PUNCH;
        }
        else if ( attends.size() == 1
                && attends.get(0).isCome()
                && attends.get(0).getPunchTime() == null)
        {
            return COME_PUNCH;
        } else if ( attends.size() == 1
                && attends.get(0).getPunchTime() == null){
            return  LEAVE_PUNCH;
        } else if ( attends.size() == 2 ){
            if ( attends.get(0).getPunchTime() == null && attends.get(1).getPunchTime() == null ){
                return BOTH_PUNCH;
            } else if ( attends.get(1).getPunchTime() == null ){
                return LEAVE_PUNCH;
            } else {
                return NO_PUNCH;
            }
        }
        return NO_PUNCH;
    }

    /**
     * 打卡
     * @param user 员工名
     * @param dutyDay 打卡日期
     * @param isCome 是否是上班打卡
     * @return 打卡结果
     */
    public int punch(String user, String dutyDay, boolean isCome) {
        Employee emp = empDao.findByName(user);
        if ( emp == null ){
            return PUNCH_FAIL;
        }
        Attend attend =
                attendDao.findByEmpAndDutyDayAndCome(emp, dutyDay, isCome);
        if ( attend == null ){
            return PUNCH_FAIL;
        }
        if ( attend.getPunchTime() != null ){
            return PUNCHED;
        }
        System.out.println("======打卡======");
        int punchHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        attend.setPunchTime(new Date());
        if ( isCome ){
            if ( punchHour < COME_LIMIT ){
                attend.setType(typeDao.get(AttendType.class, 1));
            } else  if (punchHour < LATE_LIMIT){
                attend.setType(typeDao.get(AttendType.class, 5));
            }
        }
        attendDao.update(attend);
        return PUNCH_SUCC;
    }

    /**
     * 根据员工浏览自己的工资
     * @param empName 员工名
     * @return 该员工的工资列表
     */
    public List<PaymentBean> empSalary(String empName) {
        Employee emp = empDao.findByName(empName);
        List<Payment> pays = payDao.findByEmp(emp);
        List<PaymentBean> result = new ArrayList<PaymentBean>();
        //封装vo集合
        for (Payment p : pays ){
            result.add(new PaymentBean(p.getPayMonth(), p.getAmount()));
        }
        return result;
    }

    /**
     * 员工查看自己近三天非正常打卡
     * @param empName
     * @return 该员工近三天的非正常打卡
     */
    public List<AttendBean> unAttend(String empName) {
        AttendType type = typeDao.get(AttendType.class, 1);
        Employee emp = empDao.findByName(empName);
        List<Attend> attends = attendDao.findByEmpUnAttend(emp, type);
        List<AttendBean> result = new ArrayList<AttendBean>();
        for (Attend att : attends){
            result.add(new AttendBean(att.getId(), att.getDutyDay(), att.getType().getName(),att.getPunchTime()));
        }
        return result;
    }

    /**
     * 返回全部的出勤类别
     * @return 全部的出勤类别
     */
    public List<AttendType> getAllType() {
        return typeDao.findAll(AttendType.class);
    }

    /**
     * 添加申请
     * @param attId 申请出勤的id
     * @param typeId 申请的类型id
     * @param reason 添加的结果
     * @return
     */
    public boolean addApplication(int attId, int typeId, String reason) {
        Application app = new Application();
        Attend attend = attendDao.get(Attend.class, attId);
        AttendType type = typeDao.get(AttendType.class, typeId);
        app.setAttend(attend);
        app.setType(type);
        if ( reason != null ) {
            app.setReason(reason);
        }
        appDao.save(app);
        return true;
    }

    public ApplicationDao getAppDao() {
        return appDao;
    }

    public void setAppDao(ApplicationDao appDao) {
        this.appDao = appDao;
    }

    public AttendDao getAttendDao() {
        return attendDao;
    }

    public void setAttendDao(AttendDao attendDao) {
        this.attendDao = attendDao;
    }

    public AttendTypeDao getTypeDao() {
        return typeDao;
    }

    public void setTypeDao(AttendTypeDao typeDao) {
        this.typeDao = typeDao;
    }

    public CheckBackDao getCheckDao() {
        return checkDao;
    }

    public void setCheckDao(CheckBackDao checkDao) {
        this.checkDao = checkDao;
    }

    public EmployeeDao getEmpDao() {
        return empDao;
    }

    public void setEmpDao(EmployeeDao empDao) {
        this.empDao = empDao;
    }

    public ManagerDao getMgrDao() {
        return mgrDao;
    }

    public void setMgrDao(ManagerDao mgrDao) {
        this.mgrDao = mgrDao;
    }

    public PaymentDao getPayDao() {
        return payDao;
    }

    public void setPayDao(PaymentDao payDao) {
        this.payDao = payDao;
    }
}
