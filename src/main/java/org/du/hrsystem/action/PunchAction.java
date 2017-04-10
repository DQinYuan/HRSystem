package org.du.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import org.du.hrsystem.action.base.EmpBaseAction;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by duqinyuan on 2017/4/5.
 *
 * @author duqinyuan
 * @version 1.0
 */
public class PunchAction extends EmpBaseAction {
    private int punchIsValid;

    public int getPunchIsValid() {
        return punchIsValid;
    }

    public void setPunchIsValid(int punchIsValid) {
        this.punchIsValid = punchIsValid;
    }

    public String execute() throws Exception{
        ActionContext ctx = ActionContext.getContext();
        String user = (String) ctx.getSession().get(WebConstant.USER);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dutyDay = sdf.format(new Date());
        int result = empManager.validPunch(user, dutyDay);
        setPunchIsValid(result);
        return SUCCESS;
    }
}
