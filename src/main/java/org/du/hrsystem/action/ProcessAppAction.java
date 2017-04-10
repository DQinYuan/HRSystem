package org.du.hrsystem.action;

import org.du.hrsystem.action.base.EmpBaseAction;

/**
 * Created by duqinyuan on 2017/4/6.
 *
 * @author duqinyuan
 * @version 1.0
 */
public class ProcessAppAction extends EmpBaseAction {
    private int attId;
    private int typeId;
    private String reason;

    public String execute() throws Exception{
        boolean result = empManager.addApplication(attId, typeId, reason);
        if ( result ) {
            addActionMessage("您已成功申请，等待经理批阅");
        } else {
            addActionMessage("申请失败，请注意不要重复申请");
        }
        return SUCCESS;
    }

    public int getAttId() {
        return attId;
    }

    public void setAttId(int attId) {
        this.attId = attId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
