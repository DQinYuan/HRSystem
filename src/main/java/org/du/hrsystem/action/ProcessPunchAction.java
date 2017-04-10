package org.du.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import org.du.hrsystem.action.base.EmpBaseAction;
import org.du.hrsystem.service.EmpManager;

import java.util.Date;


/**
 * Created by duqinyuan on 2017/4/6.
 *
 * @author duqinyuan
 * @version 1.0
 */
public class ProcessPunchAction extends EmpBaseAction {
    //处理上班打卡的方法
    public String come() throws Exception{
        return process(true);
    }

    //处理下班打卡的方法
    public String leave() throws Exception{
        return process(false);
    }

    private String process(boolean isCome) throws Exception{
        ActionContext ctx = ActionContext.getContext();
        String user = (String) ctx.getSession().get(WebConstant.USER);
        System.out.println("-----打卡-----" + user);
        String dutyDay = new Date(System.currentTimeMillis()).toString();
        int result = empManager.punch(user, dutyDay, isCome);
        switch (result){
            case EmpManager.PUNCH_FAIL:
                 addActionMessage("打卡失败");
                 break;
            case EmpManager.PUNCHED:
                addActionMessage("您已经打过卡了，不要重复打卡");
                 break;
            case EmpManager.PUNCH_SUCC:
                addActionMessage("打卡成功");
                 break;
        }
        return SUCCESS;
    }
}
