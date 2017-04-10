package org.du.hrsystem.exception;

/**
 * @author duqinyuan
 * @version 1.0
 */
public class HrException extends RuntimeException
{
	public HrException(){}
	public HrException(String msg)
	{
		super(msg);
	}
}