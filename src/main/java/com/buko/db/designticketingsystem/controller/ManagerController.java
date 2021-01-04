package com.buko.db.designticketingsystem.controller;

import com.buko.db.designticketingsystem.annotation.power.ManagerPower;
import com.buko.db.designticketingsystem.po.Manager;
import com.buko.db.designticketingsystem.service.ManagerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author buko
 */
@RestController
public class ManagerController {
    @Resource
    private ManagerService managerService;

    /**
     * 查询业务员信息
     * @param id id
     * @return 查询明细
     */
    @ManagerPower
    @GetMapping(value = "/manager/{id}")
    public Manager getManager(@PathVariable Long id) {
        return managerService.getManagerById(id);
    }
}
