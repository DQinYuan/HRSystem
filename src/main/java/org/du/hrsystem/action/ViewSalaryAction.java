package org.du.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import org.du.hrsystem.action.base.EmpBaseAction;
import org.du.hrsystem.vo.PaymentBean;

import java.util.*;

public class ViewSalaryAction extends EmpBaseAction
{
	private List salarys;
	public void setSalarys(List salarys)
	{
		this.salarys = salarys;
	}
	public List getSalarys()
	{
		return this.salarys;
	}
	public String execute()
		throws Exception
	{
		ActionContext ctx = ActionContext.getContext();
		String user = (String)ctx.getSession()
			.get(WebConstant.USER);
		List<PaymentBean> salarys =  empManager.empSalary(user);
		setSalarys(salarys);
		return SUCCESS;
	}
}