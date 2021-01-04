package com.buko.db.designticketingsystem;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class EmptyBlock {
    public static void main(String[] args) {
        long time = 1608517800710L;
        log.debug(String.valueOf(new Date(time)));
        log.debug(String.valueOf(time - System.currentTimeMillis()));
    }
}
