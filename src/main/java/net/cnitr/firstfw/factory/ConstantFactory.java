package net.cnitr.firstfw.factory;

import net.cnitr.firstfw.cache.Cache;
import net.cnitr.firstfw.cache.CacheKey;
import net.cnitr.firstfw.entity.Dept;
import net.cnitr.firstfw.mapper.DeptMapper;
import net.cnitr.firstfw.shiro.SpringContextHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * 常量的生产工厂
 */
@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory {

    private DeptMapper deptMapper = SpringContextHolder.getBean(DeptMapper.class);

    public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }

    /**
     * 获取部门名称
     *
     * @param deptId
     * @return
     */
    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.DEPT_NAME + "' + #deptId")
    public String getDeptName(Integer deptId) {
        Dept dept = deptMapper.selectById(deptId);
        if (dept != null && StringUtils.isNotBlank(dept.getFullname())) {
            return dept.getFullname();
        }
        return "";
    }

    /**
     * 通过角色id获取角色名称
     * @param roleId
     * @return
     */
    @Override
    @Cacheable(value = Cache.CONSTANT, key = "''" + CacheKey.SINGLE_ROLE_NAME + ", + #roleId")
    public String getSingleRoleName(Integer roleId) {
        if(0 == roleId){
            return "--";
        }

        return null;
    }
}
