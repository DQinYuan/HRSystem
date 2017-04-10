package org.du.hrsystem.action.base;

import com.opensymphony.xwork2.ActionSupport;
import org.du.hrsystem.service.EmpManager;

import javax.annotation.Resource;

/**
 * Created by duqinyuan on 2017/4/5.
 *
 * @author duqinyuan
 * @version 1.0
 */
public class EmpBaseAction extends ActionSupport {
    //依赖的业务逻辑组件
    protected EmpManager empManager;

    //注入业务逻辑组件所必需的setter
    public void setEmpManager(EmpManager empManager) {
        this.empManager = empManager;
    }
}
