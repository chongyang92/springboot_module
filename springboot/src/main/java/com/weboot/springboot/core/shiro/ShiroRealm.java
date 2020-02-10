package com.weboot.springboot.core.shiro;

import com.weboot.springboot.model.LoginUserModel;
import com.weboot.springboot.service.PathService;
import com.weboot.springboot.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/*AuthenticatingRealm-------->用于认证方法的Realm
 *AuthorizingRealm--------->用于授权和认证的realm一般使用这个
 */
public class ShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    /*这种方式获取不到userService
    @Resource
    private UserService userService;*/

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //获取授权相关信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    //提供获取认证相关信息供Authenticator获取后和前端token(用户名、密码)做判断
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取登陆名称
        String loginName = (String)authenticationToken.getPrincipal();
        //根据登陆名去数据库查询用户信息
        LoginUserModel loginUser = userService.getLoginUserModelByLoginName(loginName);
        if (loginUser == null) {
            logger.info("doGetAuthenticationInfo fail UnknownAccountException, loginName:{}", loginName);
            throw new UnknownAccountException("loginName not exist");
        }
        //将数据库中数据，user对象、数据库查询密码、realm域名，封装到认证信息AuthenticationInfo中
        SimpleAuthenticationInfo sa = new SimpleAuthenticationInfo(loginUser,loginUser.getPassword(),getName());
        StringBuilder sb = new StringBuilder(loginUser.getUserId()).append(loginName);
        //这里的加盐，是或被Authenticator拿到，然后分别对前端获取的数据进行凭证匹配器加密后进行加盐,对后端获取的密码进行加盐，结果匹配则认证通过
        sa.setCredentialsSalt(new ShiroByteSource(sb.toString()));
        //调用login的时候，会自动比较这里的第二个参数password和token里的Credentials
        return sa;
    }
}
