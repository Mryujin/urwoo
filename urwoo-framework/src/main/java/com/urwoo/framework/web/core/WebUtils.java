package com.urwoo.framework.web.core;

import com.urwoo.framework.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

public class WebUtils {

    private static Logger logger = LoggerFactory.getLogger(WebUtils.class);
    private static WebApplicationContext webApplicationContext = null;// spring上下文
    private static final int keepTime = 1 * 60 * 60 * 24 * 365;// 保存1年时间

    /**
     * 绑定servlet 容器
     * @param servletContext
     */
    public static void bindSessionContext(ServletContext servletContext) {
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
    }

    /**
     * 根据对象名称 获取spring容器bean对象
     * @param beanName bean对象所对应的名称
     * @return
     */
    public static Object getBean(String beanName) {
        return webApplicationContext.getBean(beanName);
    }


    /**
     * 根据类型获取spring容器bean对象
     * @param <T>
     * @param c bean对象对应的class
     * @return
     */
    public static <T> T getBeanByType(Class<T> c) {
        Map<String, T> map = webApplicationContext.getBeansOfType(c);
        T t = null;
        for (Map.Entry<String, T> entry : map.entrySet()) {
            t = entry.getValue();
        }
        return t;
    }

    public static String getCookies(HttpServletRequest req, String key) {
        return getCookies(req, key , "") ;
    }

    /**
     * 获取cookies 值
     * @param req 请求
     * @param key cookie的键
     * @return
     */
    public static String getCookies(HttpServletRequest req, String key , String defaultValue) {
        if (req == null || StringUtils.isBlank(key))
            return defaultValue;
        try {
            // 获取该请求的所有cookie
            Cookie[] cookies = req.getCookies();
            // 循环获取
            if (cookies != null && cookies.length > 0)
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(key)) {
                        String value = URLDecoder.decode(cookie.getValue(), "UTF-8");
                        if (StringUtils.isBlank(value)) {
                            return defaultValue ;
                        }
                        return value;
                    }
                }
        } catch (Exception e) {
            logger.error("从cookie取值失败！key[" + key + "]");
        }

        return defaultValue;
    }

    public static boolean setCookies(HttpServletResponse res, String key,
                                     String value, int keepTime , String path) {
        // 检查值是否正确
        if (res == null || StringUtils.isBlank(key)
                || StringUtils.isBlank(value))
            return false;

        try {
            // 创建cookie
            Cookie cookie = new Cookie(key, URLEncoder.encode(value, "UTF-8"));
            cookie.setPath(path);// 设置cookie的路径

            if (keepTime > 0)
                cookie.setMaxAge(keepTime); // 设置cookie的时间
            cookie.setSecure(false);// 为true时只有https 方式请求才有效
            // cookie.setDomain(Cfg.getConfig("cookie.domian"));
            res.addCookie(cookie);
        } catch (Exception e) {
            logger.error("添加cookie失败！key[" + key + "],value[" + value + "]");
        }

        return true;
    }


    public static boolean setCookiesCurrentSession(HttpServletResponse res, String key,
                                                   String value) {
        // 检查值是否正确
        if (res == null || StringUtils.isBlank(key)
                || StringUtils.isBlank(value))
            return false;
        try {
            // 创建cookie
            Cookie cookie = new Cookie(key, URLEncoder.encode(value, "UTF-8"));
            cookie.setPath("/");// 设置cookie的路径

            cookie.setSecure(false);// 为true时只有https 方式请求才有效
            // cookie.setDomain(Cfg.getConfig("cookie.domian"));
            res.addCookie(cookie);
        } catch (Exception e) {
            logger.error("添加cookie失败！key[" + key + "],value[" + value + "]");
        }
        return true;
    }


    /**
     * 设置永久的cookie
     * @param res
     * @param key
     * @param value
     * @return
     */
    public static boolean setCookies(HttpServletResponse res, String key,
                                     String value,String path) {
        return setCookies(res, key, value, keepTime , path);
    }

    /**
     * 清空cookie
     * @param request 请求
     * @param response 响应
     * @param path
     */
    public static void clearCookie(HttpServletRequest request,
                                   HttpServletResponse response,String path) {
        Cookie[] cookies = request.getCookies();
        try {
            for (int i = 0; i < cookies.length; i++) {
                // System.out.println(cookies[i].getName() + ":" +
                // cookies[i].getValue());
                Cookie cookie = new Cookie(cookies[i].getName(), null);
                cookie.setMaxAge(0);
                cookie.setPath(path);// 根据你创建cookie的路径进行填写
                response.addCookie(cookie);
            }
        } catch (Exception e) {
            logger.error("清空Cookies发生异常！", e);
        }

    }

    /**
     * 取消cookie
     * @param request
     * @param response
     * @param name
     * @param domain
     */
    public static void delCookie(HttpServletRequest request,
                                 HttpServletResponse response, String name, String domain) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        response.addCookie(cookie);
    }

    /**
     * @desc 往客户端回写
     * @param response
     * @param text
     * @author hekai
     */
    public static void respWrite(HttpServletResponse response, String text) {

        try {
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write(text);
            response.getWriter().flush();
            response.setStatus(200);
        } catch (IOException e) {
            logger.error("往客户端写失败", e);
        }
    }

    /**
     * 获得请求参数，返回int类型
     *
     * @param req
     * @param paramName
     * @param def
     * @return
     */
    public static int getRequestParam(HttpServletRequest req, String paramName,
                                      int def) {
        String val = req.getParameter(paramName);
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException ne) {
            return def;
        }
    }

    /**
     * 获得请求参数，返回string类型
     * @param req
     * @param paramName
     * @param def
     * @return
     */
    public static String getRequestParam(HttpServletRequest req,
                                         String paramName, String def) {
        String val = req.getParameter(paramName);
        if (StringUtils.isBlank(val)) {
            return def;
        }
        return val;
    }

    /**
     * 获取用户的真实ip地址
     *
     * @param req
     * @return
     */
    public static String getIpAddr(HttpServletRequest req) {

        String ip = req.getHeader("x-forwarded-for");

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }
        // 获取第一个ip
        if (StringUtils.isNotBlank(ip)) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    /**
     * 导出excel
     * @param response
     * @param wb
     * @param fileName
     * @throws Exception
     */
    public static void export(HttpServletResponse response, HSSFWorkbook wb, String fileName)throws Exception{
        response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"),"iso8859-1"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        OutputStream out=response.getOutputStream();
        wb.write(out);
        out.flush();
        out.close();
    }
}
