package com.urwoo.framework.web.response;

public interface ResponseConst {

    /** http请求状态码*/
    enum HttpStatusCode {
        OPERATE_FAILED(0, "操作失败！"),
        OPERATE_SUCCESS(1, "操作成功！"),
        OPERATE_FAILED_NOAUTH(126, "您没有权限访问该页面！"),
        OPERATE_FAILED_NOTLOGIN(127, "您尚未登录！"),
        SESSION_EXPIRED(129, "session过期！"),
        REQUEST_REDIRECT(302, "请求重定向！"),
        SYSTEM_ERROR(500, "对不起！系统发生错误！请联系网站管理员！");

        private int code;
        private String des;

        HttpStatusCode(int code, String des){
            this.code = code;
            this.des = des;
        }

        public int code(){
            return code;
        }

        public String des(){
            return des;
        }
    }
}
