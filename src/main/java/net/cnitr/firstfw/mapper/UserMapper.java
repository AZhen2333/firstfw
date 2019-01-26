package net.cnitr.firstfw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cnitr.firstfw.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User>{
    /**
     * 通过账号获取用户
     */
    User getByAccount(@Param("account") String account);
}
