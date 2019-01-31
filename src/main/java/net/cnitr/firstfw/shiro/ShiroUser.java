package net.cnitr.firstfw.shiro;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 */
@Data
public class ShiroUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    public Long id;

    /**
     * 帐号
     */
    public String account;

    /**
     * 姓名
     */
    public String name;

    /**
     * 部门id
     */
    public Integer deptId;

    /**
     * 角色集
     */
    public List<Integer> roleList;

    /**
     * 部门名称
     */
    public String deptName;

    /**
     * 角色名称集
     */
    public List<String> roleNames;
}
