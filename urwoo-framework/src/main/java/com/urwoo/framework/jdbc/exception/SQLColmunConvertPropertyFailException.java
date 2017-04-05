package com.urwoo.framework.jdbc.exception;

/**
 * SQL语句中列转化属性失败异常
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2017-01-01-21:51
 */
public class SQLColmunConvertPropertyFailException extends RuntimeException{

    public SQLColmunConvertPropertyFailException() {
    }

    public SQLColmunConvertPropertyFailException(String s) {
        super(s);
    }

    public SQLColmunConvertPropertyFailException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SQLColmunConvertPropertyFailException(Throwable throwable) {
        super(throwable);
    }

    public SQLColmunConvertPropertyFailException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
