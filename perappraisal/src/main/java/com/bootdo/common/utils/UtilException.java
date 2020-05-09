/**
 * FileName: UtilException.java
 */
package com.bootdo.common.utils;

/**
 * <dl>
 * <dt>UtilException</dt>
 * <dd>Description:工具类执行异常</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 高新普惠科技有限公司</dd>
 * <dd>CreateDate: 2006-9-11</dd>
 * </dl>
 */
public class UtilException extends RuntimeException {
	public UtilException() {
		super();
	}

	public UtilException(String message) {
		super(message);
	}

	public UtilException(String message, Throwable cause) {
		super(message, cause);
	}

	public UtilException(Throwable cause) {
		super(cause);
	}
}
