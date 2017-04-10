package org.du.hrsystem.schedule;

import org.du.hrsystem.service.EmpManager;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by duqinyuan on 2017/3/30.
 *
 * @author duqinyuan
 * @version 1.0
 */
public class PayJob extends QuartzJobBean {
    private boolean isRunning = false;
    private EmpManager empMgr;
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if ( !isRunning ){
            System.out.println("开始调度自动结算工资任务");
            isRunning = true;
            empMgr.autoPay();
            isRunning = false;
        }
    }

    public void setEmpMgr(EmpManager empMgr) {
        this.empMgr = empMgr;
    }
}
