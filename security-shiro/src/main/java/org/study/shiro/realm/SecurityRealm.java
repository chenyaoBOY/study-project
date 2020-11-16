package org.study.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class SecurityRealm extends AuthorizingRealm {

    //模拟用户
    private final static String USER_NAME="chenyao";
    private final static String PASSWORD="123456";

    /**
     * 获取授权信息
     * @param pc
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        Set<String> roles = new HashSet<String>();
        Set<String> permissions = new HashSet<String>();
        roles.add("admin");
        permissions.add("add");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        info.setStringPermissions(permissions);

        return info;
    }

    /**
     * 获取认证信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //类型转换
        UsernamePasswordToken upt = (UsernamePasswordToken)token;
        //获取用户名
        String username = upt.getUsername();
        //获取密码  得到的是字符数组 转为string类型
        char[] passwordChar = upt.getPassword();
        String password = new String(passwordChar);

        //模拟用户验证  实际应该从数据库或者缓存中进行验证
        if(USER_NAME.equals(username)){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,getName());
            return info;
        }else{
            throw new AuthenticationException();
        }

    }
}
