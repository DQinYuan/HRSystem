package org.du.hrsystem.action;


import com.opensymphony.xwork2.ActionContext;
import org.du.hrsystem.action.base.EmpBaseAction;
import org.du.hrsystem.vo.AttendBean;

import java.util.List;

public class ViewUnAttendAction extends EmpBaseAction
{
	private List<AttendBean> unAttend;
	public void setUnAttend(List<AttendBean> unAttend)
	{
		this.unAttend = unAttend;
	}
	public List<AttendBean> getUnAttend()
	{
		return this.unAttend;
	}
	public String execute()
		throws Exception
	{
		ActionContext ctx = ActionContext.getContext();
		String user = (String)ctx.getSession()
			.get(WebConstant.USER);
		List<AttendBean> result = empManager.unAttend(user);
		setUnAttend(result);
		return SUCCESS;
	}
}