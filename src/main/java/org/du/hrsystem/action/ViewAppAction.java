package org.du.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import org.du.hrsystem.action.base.MgrBaseAction;

import java.util.List;

public class ViewAppAction extends MgrBaseAction
{
	private List apps;
	public void setApps(List apps)
	{
		this.apps = apps;
	}
	public List getApps()
	{
		return this.apps;
	}
	public String execute()
		throws Exception
	{
		ActionContext ctx = ActionContext.getContext();
		String mgrName = (String)ctx.getSession()
			.get(WebConstant.USER);
		setApps(mgr.getAppsByMgr(mgrName));
		return SUCCESS;
	}
}