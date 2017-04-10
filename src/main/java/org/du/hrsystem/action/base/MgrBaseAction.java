package org.du.hrsystem.action.base;

import com.opensymphony.xwork2.ActionSupport;
import org.du.hrsystem.service.MgrManager;

import javax.annotation.Resource;


public class MgrBaseAction extends ActionSupport
{
	protected MgrManager mgr;

	public void setMgrManager(MgrManager mgr)
	{
		this.mgr = mgr;
	}
}