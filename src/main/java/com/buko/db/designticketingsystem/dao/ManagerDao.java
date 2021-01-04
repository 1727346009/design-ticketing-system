package com.buko.db.designticketingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buko.db.designticketingsystem.po.Manager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 表[t_manager]对应实体类
 *
 * @author buko 2020年12月03日
 */
@Repository
public interface ManagerDao extends BaseMapper<Manager> {
    /**
     * 通过 id 获取 manager
     *
     * @param id id
     * @return manager
     */
    @Select("select * from v_manager where id = #{id}")
    Manager getManagerById(@Param("id") Long id);
}