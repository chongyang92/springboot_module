package com.weboot.springboot.core.shiro;

import com.alibaba.fastjson.JSON;
import com.weboot.springboot.core.Result;
import com.weboot.springboot.core.ResultBuilder;
import com.weboot.springboot.exception.ServiceException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import javax.xml.ws.Service;
import java.io.IOException;

public class ShiroPathFilter extends AccessControlFilter {

    private static final String DEFAULT_MSG = "请求被拒绝";
    private static final String UNAUTHENTICATED_FLAG_KEY = "UNAUTHENTICATED_FLAG_KEY";
    private static final String UNAUTHENTICATED_MSG = "未登录，请求被拒绝";
    private static final String UNAUTHORIZATD_FLAG_KEY = "UNAUTHORIZATD_FLAG_KEY";
    private static final String UNAUTHORIZATD_MSG = "权限不足，请求被拒绝";

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        //获取uri,例如/user/list
        String uri = getPathWithinApplication(servletRequest);
        System.out.println(uri);
        //转为HTTP ServletRequest后可以使用其方法，这里传入的servletRequest，本身就是ServletRequest servletRequest = new HttpServletRequest servletRequest;所以可以再转回去
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String httpMethod = httpServletRequest.getMethod();
        //获取subject
        Subject subject = getSubject(servletRequest,servletResponse);

        //判断当前subject是否通过认证
        if(!subject.isAuthenticated()){
            servletRequest.setAttribute(UNAUTHENTICATED_FLAG_KEY, true);
            return false;
        }
        //将uri放入自定义Permission中
        ShiroPathPermission shiroPathPermission = new ShiroPathPermission(httpMethod,uri);

        if(!subject.isPermitted(shiroPathPermission)){
            servletRequest.setAttribute("UNAUTHORIZATD_FLAG_KEY",true);
            return false;
        }
        return true;
    }

    /**
     * 授权失败后执行
     * 如果isAccessAllowed方法返回True，则不会再调用onAccessDenied方法，如果isAccessAllowed方法返回Flase,则会继续调用onAccessDenied方法
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Result result = getFailResult(servletRequest);
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("application/json; charset=UTF-8");
        if (servletResponse instanceof HttpServletResponse) {
            ((HttpServletResponse) servletResponse).setStatus(200);
        }
        try {
            servletResponse.getWriter().write(JSON.toJSONString(result));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


     /**
     * 获取拒绝请求信息
     * @param request
     * @return
     */
    private Result getFailResult(ServletRequest request) {
        Object unauthenticatedFlagKey = request.getAttribute(UNAUTHENTICATED_FLAG_KEY);
        Object unauthorizatdFlagKey = request.getAttribute(UNAUTHORIZATD_FLAG_KEY);
        removeUnauthFlag(request);
        if (unauthenticatedFlagKey != null) {
            return ResultBuilder.genUnauthorizedResult(UNAUTHENTICATED_MSG);
        }
        if (unauthorizatdFlagKey != null) {
            return ResultBuilder.genForbiddenResult(UNAUTHORIZATD_MSG);
        }
        return ResultBuilder.genUnauthorizedResult(DEFAULT_MSG);
    }

    private void removeUnauthFlag(ServletRequest request) {
        request.removeAttribute(UNAUTHENTICATED_FLAG_KEY);
        request.removeAttribute(UNAUTHORIZATD_FLAG_KEY);
    }
}
