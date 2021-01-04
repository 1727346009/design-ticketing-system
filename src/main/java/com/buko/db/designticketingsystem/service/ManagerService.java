package com.buko.db.designticketingsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.buko.db.designticketingsystem.po.Manager;
import com.buko.db.designticketingsystem.po.Token;

/**
 * Manager对应Service
 *
 * @author buko 2020年12月03日
 */
public interface ManagerService extends IService<Manager> {
    Manager getManagerById(Long id);

    Token loginManager(Manager manager);
}