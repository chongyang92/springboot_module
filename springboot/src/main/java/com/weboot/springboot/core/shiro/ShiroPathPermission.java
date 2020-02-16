package com.weboot.springboot.core.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.util.AntPathMatcher;

public class ShiroPathPermission implements Permission {

    private static final String DEFUALT_HTTP_METHOD = "*";

    public ShiroPathPermission() {
    }

    public ShiroPathPermission(String httpMethod, String httpPath) {
        this.httpMethod = httpMethod;
        this.httpPath = httpPath;
    }

    /**
     * http请求方式
     */
    private String httpMethod = "*";

    /**
     * http请求path
     */
    private String httpPath;

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getHttpPath() {
        return httpPath;
    }

    public void setHttpPath(String httpPath) {
        this.httpPath = httpPath;
    }

    //判断是否有此权限,重要的判断条件
    @Override
    public boolean implies(Permission permission) {
        if (permission == null || !(permission instanceof ShiroPathPermission)) {
            return false;
        }

        ShiroPathPermission shiroPathPermission = (ShiroPathPermission) permission;
        String httpMethod = shiroPathPermission.getHttpMethod();
        String httpPath = shiroPathPermission.getHttpPath();
        //判断method是否匹配
        //忽略POST和GET的大小写
        if(!DEFUALT_HTTP_METHOD.equals(this.httpMethod)) {
            //去空格，在白名单里可以有空格
            if(!StringUtils.equalsIgnoreCase(httpMethod,this.httpMethod.trim())) {
                return false;
            }
        }
        //判断path是否为空
        if(StringUtils.isBlank(httpPath) || StringUtils.isBlank(this.httpPath)){
            return false;
        }
        //判断path是否有权限，可以使用/user/**表示user下所有uri都可以匹配
        /**
         * ?  匹配一个字符
         * *  匹配一个或多个字符
         * ** 匹配一个或多个目录
         */
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return antPathMatcher.match(httpPath,this.httpPath);
    }
}
