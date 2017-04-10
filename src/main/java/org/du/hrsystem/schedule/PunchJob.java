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
public class PunchJob extends QuartzJobBean {
    //判断作业是否执行的标志
    private boolean isRunning = false;
    //该作业所依赖的业务逻辑组件
    private EmpManager empMgr;

    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if ( !isRunning ){
            System.out.println("开始调度自动打卡");
            isRunning = true;
            empMgr.autoPunch();
            isRunning = false;
        }
    }

    public void setEmpMgr(EmpManager empMgr) {
        this.empMgr = empMgr;
    }
}
