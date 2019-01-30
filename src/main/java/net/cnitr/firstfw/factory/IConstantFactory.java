package net.cnitr.firstfw.factory;

import net.cnitr.firstfw.shiro.SpringContextHolder;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;


public interface IConstantFactory {

    /**
     * 获取部门名称
     */
    String getDeptName(Integer deptId);

    /**
     * 通过角色id获取角色名称
     */
    String getSingleRoleName(Integer roleId);
}
