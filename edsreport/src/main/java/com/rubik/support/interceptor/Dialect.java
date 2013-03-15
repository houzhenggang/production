package com.rubik.support.interceptor;

public abstract class Dialect {
	public static enum Type{     
        MYSQL,     
        ORACLE     
    }
	/**
	 * @param sql
	 * @param skipResults 页码
	 * @param maxResults 每页记录数
	 * @return
	 */
    public abstract String getLimitString(String sql, int skipResults, int maxResults);
}
