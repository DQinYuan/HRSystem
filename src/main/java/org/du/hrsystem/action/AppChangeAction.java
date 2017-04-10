package org.du.hrsystem.action;

import org.du.hrsystem.action.base.EmpBaseAction;

import java.util.List;

/**
 * Created by duqinyuan on 2017/4/6.
 *
 * @author duqinyuan
 * @version 1.0
 */
public class AppChangeAction extends EmpBaseAction {
    private List types;

    public List getTypes() {
        return types;
    }

    public void setTypes(List types) {
        this.types = types;
    }

    public String execute() throws Exception{
        setTypes(empManager.getAllType());
        return SUCCESS;
    }
}
