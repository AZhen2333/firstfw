package net.cnitr.firstfw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cnitr.firstfw.entity.Role;
import net.cnitr.firstfw.node.ZTreeNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 角色表 Mapper 接口
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据条件查询角色列表
     *
     * @param condition
     * @return
     */
    List<Map<String, Object>> selectRoles(@Param("condition") String condition);

    /**
     * 删除某个角色的所有权限
     *
     * @param roleId
     * @return
     */
    int deleteRolesById(@Param("roleId") Integer roleId);

    /**
     * 获取角色列表树
     *
     * @return
     */
    List<ZTreeNode> roleTreeList();

    /**
     * 获取角色列表树
     *
     * @param roleId
     * @return
     */
    List<ZTreeNode> roleTreeListByRoleId(String[] roleId);
}
