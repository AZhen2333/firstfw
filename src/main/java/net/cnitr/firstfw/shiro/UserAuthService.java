package net.cnitr.firstfw.shiro;

import net.cnitr.firstfw.entity.User;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

/**
 * 用户登录接口
 */
public interface UserAuthService {

    /**
     * 根据账号获取登录用户
     *
     * @param account 账号
     */
    User user(String account);

    /**
     * 根据系统用户获取Shiro的用户
     *
     * @param user 系统用户
     */
    ShiroUser shiroUser(User user);

    /**
     * 获取shiro的认证信息
     *
     * @param shiroUser
     * @param user
     * @param realmName
     * @return
     */
    SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName);
}
