package org.du.hrsystem.action.authority;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.du.hrsystem.action.WebConstant;

/**
 * Created by duqinyuan on 2017/4/6.
 *
 * @author duqinyuan
 * @version 1.0
 */
public class EmpAuthorityInterceptor extends AbstractInterceptor {
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        ActionContext ctx = ActionContext.getContext();
        String level = (String) ctx.getSession().get(WebConstant.LEVEL);
        if ( level != null && (level.equals(WebConstant.EMP_LEVEL) || level.equals(WebConstant.MGR_LEVEL)) ){
            return actionInvocation.invoke();
        }
        return Action.LOGIN;
    }
}
