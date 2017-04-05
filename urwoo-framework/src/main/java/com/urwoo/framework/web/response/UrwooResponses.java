package com.urwoo.framework.web.response;

import com.urwoo.framework.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UrwooResponses implements ResponseConst{

    /** 创建成功 */
    public static UrwooResponse success() {
        return success(null);
    }

    public static UrwooResponse success(Object data) {
        return new UrwooResponse(HttpStatusCode.OPERATE_SUCCESS.code(),
                HttpStatusCode.OPERATE_SUCCESS.des(), data);
    }

    /** 创建分页 */
    public static <T> UrwooResponse page(List<T> records, int total){
        Map<String,Object> data = new HashMap<>();
        data.put("list", records);
        data.put("total", total);
        return success(data);
    }
    public static <T> UrwooResponse page(List<T> records, int total, int currentPage, int pageSize){
        Map<String,Object> data = new HashMap<>();
        data.put("list", records);
        data.put("total", total);
        data.put("currentPage", currentPage);
        data.put("pageSize", pageSize);
        return success(data);
    }

    /** 权限异常 */
    public static UrwooResponse noAuth(){
        return new UrwooResponse(HttpStatusCode.OPERATE_FAILED_NOAUTH.code(),
                HttpStatusCode.OPERATE_FAILED_NOAUTH.des());
    }

    /** 未登录 */
    public static UrwooResponse noLogin() {
        return new UrwooResponse(HttpStatusCode.OPERATE_FAILED_NOTLOGIN.code(),
                HttpStatusCode.OPERATE_FAILED_NOTLOGIN.des());
    }

    /** session过期、系统未登录*/
    public static UrwooResponse sessionExpired() {
        return new UrwooResponse(HttpStatusCode.SESSION_EXPIRED.code(),
                HttpStatusCode.SESSION_EXPIRED.des());
    }

    /** 操作失败 */
    public static UrwooResponse fail(String msg){
        return new UrwooResponse(HttpStatusCode.OPERATE_FAILED.code(),
                StringUtils.isEmpty(msg) ? HttpStatusCode.OPERATE_FAILED.des() : msg);
    }

    /** 系统错误 */
    public static UrwooResponse systemError(String msg){
        return new UrwooResponse(HttpStatusCode.SYSTEM_ERROR.code(),
                StringUtils.isEmpty(msg) ? HttpStatusCode.SYSTEM_ERROR.des() : msg);
    }

    /** 返回重定向 */
    public static UrwooResponse redirect(String url) {
        if (StringUtils.isEmpty(url.trim())) {
            throw new IllegalArgumentException("url must required!");
        }
        return new UrwooResponse(HttpStatusCode.REQUEST_REDIRECT.code(), url);
    }
}
