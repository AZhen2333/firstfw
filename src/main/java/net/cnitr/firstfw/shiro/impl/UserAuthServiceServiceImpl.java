package net.cnitr.firstfw.shiro.impl;

import net.cnitr.firstfw.entity.User;
import net.cnitr.firstfw.exception.AccountException.CredentialsException;
import net.cnitr.firstfw.exception.AccountException.LockedAccountException;
import net.cnitr.firstfw.factory.ConstantFactory;
import net.cnitr.firstfw.mapper.UserMapper;
import net.cnitr.firstfw.shiro.ShiroUser;
import net.cnitr.firstfw.shiro.SpringContextHolder;
import net.cnitr.firstfw.shiro.UserAuthService;
import net.cnitr.firstfw.state.ManagerStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class UserAuthServiceServiceImpl implements UserAuthService {

    @Autowired
    private UserMapper userMapper;

    public static UserAuthService me() {
        return SpringContextHolder.getBean(UserAuthService.class);
    }

    @Override
    public User user(String account) {
        User user = userMapper.getByAccount(account);
        // 帐号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        // 冻结
        if (user.getStatus() != ManagerStatus.OK.getCode()) {
            throw new LockedAccountException();
        }
        return user;
    }

    @Override
    public ShiroUser shiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setId(user.getId());
        shiroUser.setAccount(user.getAccount());
        shiroUser.setDeptId(user.getDeptid());
        shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptid()));
        shiroUser.setName(user.getName());
        Integer[] roleArray = null;
        String roleid = user.getRoleid();
        if (StringUtils.isNotBlank(roleid)) {
            String[] strArray = roleid.split(",");
            for (Integer i = 0; i < strArray.length; i++) {
                roleArray[i] = Integer.parseInt(strArray[i]);
            }
        }
        List<Integer> roleList = new ArrayList<Integer>();
        List<String> roleNameList = new ArrayList<String>();
        for (int roleId: roleArray ) {
            roleList.add(roleId);
            roleNameList.add(ConstantFactory.me().getSingleRoleName(roleId));
        }
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);
        return shiroUser;
    }

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName) {
        String credentials = user.getPassword();
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }
}
