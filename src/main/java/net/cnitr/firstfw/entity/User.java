package net.cnitr.firstfw.entity;

import lombok.Data;

import java.util.Date;

/**
 * 管理员表
 */
@Data
public class User {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 账号
     */
    private Integer account;

    /**
     * 密码
     */
    private String password;

    /**
     * md5密码盐
     */
    private String salt;

    /**
     * 名字
     */
    private String name;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别（0：女，1：男）
     */
    private Integer sex;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 角色id
     */
    private String roleid;

    /**
     * 部门id
     */
    private Integer deptid;

    /**
     * 状态(1：启用  2：冻结  3：删除）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 保留字段
     */
    private Integer version;
}