package org.du.hrsystem.action;


import com.opensymphony.xwork2.ActionContext;
import org.du.hrsystem.action.base.MgrBaseAction;

import java.util.List;

public class ViewEmpAction extends MgrBaseAction
{
	private List emps;
	public void setEmps(List emps)
	{
		this.emps = emps;
	}
	public List getEmps()
	{
		return this.emps;
	}
	public String execute()
		throws Exception
	{
		ActionContext ctx = ActionContext.getContext();
		String mgrName = (String)ctx.getSession()
			.get(WebConstant.USER);
		setEmps(mgr.getEmpsByMgr(mgrName));
		return SUCCESS;
	}
}