package org.du.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import org.du.hrsystem.action.base.EmpBaseAction;
import org.du.hrsystem.domain.Manager;
import org.du.hrsystem.service.EmpManager;

/**
 * Created by duqinyuan on 2017/4/5.
 *
 * @author duqinyuan
 * @version 1.0
 */
public class LoginAction extends EmpBaseAction{
    //员工登录成功的result名
    private final String EMP_RESULT = "emp";
    //经理登录成功的result名
    private final String MGR_RESULT = "mgr";
    //封装请求参数
    private Manager manager;
    //登录的验证码
    private String vercode;

    public String execute() throws Exception{
        ActionContext ctx = ActionContext.getContext();
        String ver2 = (String) ctx.getSession().get("rand");
        if ( vercode.equalsIgnoreCase(ver2) ){
            int result = empManager.validLogin(getManager());
            //登录结果为普通员工
            if ( result == EmpManager.LOGIN_EMP){
                ctx.getSession().put(WebConstant.USER, manager.getName());
                ctx.getSession().put(WebConstant.LEVEL, WebConstant.EMP_LEVEL);
                addActionMessage("你已经成功登录系统");
                return EMP_RESULT;
                //登录结果为经理
            } else if( result == EmpManager.LOGIN_MGR ) {
                ctx.getSession().put(WebConstant.USER, manager.getName());
                ctx.getSession().put(WebConstant.LEVEL, WebConstant.MGR_LEVEL);
                addActionMessage("您已成功登录系统");
                return MGR_RESULT;
                //用户名和密码不匹配
            } else {
                addActionMessage("用户名/密码不匹配");
                return ERROR;
            }
        }
        //验证码不匹配
        addActionMessage("验证码不匹配，请重新输入");
        return ERROR;
    }

    public String getEMP_RESULT() {
        return EMP_RESULT;
    }

    public String getMGR_RESULT() {
        return MGR_RESULT;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getVercode() {
        return vercode;
    }

    public void setVercode(String vercode) {
        this.vercode = vercode;
    }
}
