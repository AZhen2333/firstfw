package net.cnitr.firstfw.shiro.impl;

import net.cnitr.firstfw.entity.User;
import net.cnitr.firstfw.exception.AccountException.CredentialsException;
import net.cnitr.firstfw.exception.AccountException.LockedAccountException;
import net.cnitr.firstfw.mapper.UserMapper;
import net.cnitr.firstfw.shiro.SpringContextHolder;
import net.cnitr.firstfw.shiro.UserAuthService;
import net.cnitr.firstfw.state.ManagerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
