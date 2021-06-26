package com.ldu.newsdisplay.sysconfig.service;

import com.ldu.newsdisplay.sysconfig.domain.Sysconfig;

import java.util.List;

public interface SysconfigService {
    void updateSysconfig(Sysconfig sysconfig);

    void deleteSysconfig(Long configId);

    void insertSysconfig(Sysconfig sysconfig);

    List<Sysconfig> querySysconfigList(String name);

    Sysconfig querySysconfigById(Long configId);
}
