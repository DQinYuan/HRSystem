package org.du.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import org.du.hrsystem.action.base.MgrBaseAction;

public class CheckAppAction extends MgrBaseAction
{
	private int appid;
	private String result;
	public void setAppid(int appid)
	{
		this.appid = appid;
	}
	public int getAppid()
	{
		return this.appid;
	}

	public void setResult(String result)
	{
		this.result = result;
	}
	public String getResult()
	{
		return this.result;
	}

	public String execute()
		throws Exception
	{
		ActionContext ctx = ActionContext.getContext();
		String mgrName = (String)ctx.getSession()
			.get(WebConstant.USER);
		if (result.equals("pass"))
		{
			mgr.check(appid, mgrName, true);
		}
		else if (result.equals("deny"))
		{
			mgr.check(appid, mgrName, false);
		}
		else
		{
			throw new Exception("参数丢失");
		}
		return SUCCESS;
	}
}