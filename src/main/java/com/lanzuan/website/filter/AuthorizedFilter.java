package com.lanzuan.website.filter;

import com.lanzuan.common.web.CookieTool;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Administrator on 2016/1/21.
 */
public final class AuthorizedFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest)request;
        Cookie cookieLoginStr=CookieTool.getCookieByName(httpServletRequest, "loginStr");
        Cookie cookiePassword=CookieTool.getCookieByName(httpServletRequest, "password");

//
//        if (cookieLoginStr!=null && cookiePassword!=null){
////            System.out.println("loginStr from cookie:"+cookieLoginStr.getName()+",value:"+cookieLoginStr.getValue());
////            System.out.println("password from cookie:"+cookiePassword.getName()+",value:"+cookiePassword.getValue());
//            HttpSession session=httpServletRequest.getSession();
//            Object userObjectInSession= session.getAttribute(Constant.LOGIN_USER);
//            if (userObjectInSession==null){
//                User user= ServiceManager.userService.findByEmailOrPhoneAndPassword(cookieLoginStr.getValue(), cookiePassword.getValue());
//                session.setAttribute(Constant.LOGIN_USER,user);
//            }
//        }
        chain.doFilter(httpServletRequest, response);
    }

    @Override
    public void destroy() {

    }
}