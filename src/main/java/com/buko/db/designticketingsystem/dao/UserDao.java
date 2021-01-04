package com.buko.db.designticketingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buko.db.designticketingsystem.po.User;
import com.buko.db.designticketingsystem.vo.ShowUserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 表[t_user]对应实体类
 *
 * @author buko 2020年12月03日
 */
@Repository
public interface UserDao extends BaseMapper<User> {
    /**
     * 获取 user 视图
     *
     * @param username username
     * @return user vo
     */
    @Select("select * from v_user where username = #{username}")
    ShowUserVO getUserVoByUsername(@Param("username") String username);

    /**
     * 获取 user 视图
     *
     * @param id id
     * @return user vo
     */
    @Select("select * from v_user where id = #{id}")
    ShowUserVO getUserVOById(@Param("id") Long id);

    /**
     * users 分页模糊查询
     *
     * @param page 分页器
     * @param key  关键字
     * @return users
     */
    @Select("select * " +
            "from v_user " +
            "where username like concat('%', #{key}, '%') " +
            "order by id")
    IPage<ShowUserVO> selectUserPageVo(Page<?> page, @Param("key") String key);
}