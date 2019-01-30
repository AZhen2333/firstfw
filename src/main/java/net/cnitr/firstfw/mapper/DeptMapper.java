package net.cnitr.firstfw.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.javafx.collections.MappingChange;
import net.cnitr.firstfw.entity.Dept;
import net.cnitr.firstfw.node.ZTreeNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 部门表 Mapper 接口
 */
public interface DeptMapper extends BaseMapper<Dept> {

    /**
     * 获取ztree的节点列表
     *
     * @return
     */
    List<ZTreeNode> tree();

    /**
     * 获取所有部门列表
     *
     * @param condition
     * @return
     */
    List<Map<String, Object>> list(@Param("condition") String condition);
}
