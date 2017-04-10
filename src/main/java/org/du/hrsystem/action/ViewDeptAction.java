package org.du.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import org.du.hrsystem.action.base.MgrBaseAction;
import org.du.hrsystem.vo.SalaryBean;

import java.util.List;


public class ViewDeptAction extends MgrBaseAction
{
	private List sals;
	public void setSals(List sals)
	{
		this.sals = sals;
	}
	public List getSals()
	{
		return this.sals;
	}

	public String execute()
		throws Exception
	{
		ActionContext ctx = ActionContext.getContext();
		String mgrName = (String)ctx.getSession()
			.get(WebConstant.USER);
		List<SalaryBean> result = mgr.getSalaryByMgr(mgrName);
		setSals(result);
		return SUCCESS;
	}
}