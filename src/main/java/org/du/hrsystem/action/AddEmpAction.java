package org.du.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import org.du.hrsystem.action.base.MgrBaseAction;
import org.du.hrsystem.domain.Employee;

public class AddEmpAction extends MgrBaseAction
{
	private Employee emp;

	public void setEmp(Employee emp)
	{
		this.emp = emp;
	}
	public Employee getEmp()
	{
		return this.emp;
	}

	public String execute()
		throws Exception
	{
		ActionContext ctx = ActionContext.getContext();
		String mgrName = (String)ctx.getSession()
			.get(WebConstant.USER);
		mgr.addEmp(emp , mgrName);
		addActionMessage("新增员工成功");
		return SUCCESS;
	}
}