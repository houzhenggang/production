package com.rubik.support.interceptor;

public class OracleDialect extends Dialect {

	/**
	 * @param sql
	 * @param skipResults 页码
	 * @param maxResults 每页记录数
	 * @return
	 */
	@Override     
	public String getLimitString(String sql, int offset, int limit) {     
	    return null;
	} 

}
