package com.ldu.newsdisplay.sysconfig.service.Impl;

import com.ldu.newsdisplay.sysconfig.dao.SysconfigMapper;
import com.ldu.newsdisplay.sysconfig.domain.Sysconfig;
import com.ldu.newsdisplay.sysconfig.domain.SysconfigExample;
import com.ldu.newsdisplay.sysconfig.service.SysconfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class SysconfigServiceImpl implements SysconfigService {
    @Resource
    private SysconfigMapper sysconfigMapper;

    @Override
    public void updateSysconfig(Sysconfig sysconfig) {
        sysconfigMapper.updateByPrimaryKeySelective(sysconfig);
    }

    @Override
    public void deleteSysconfig(Long configId) {
        sysconfigMapper.deleteByPrimaryKey(configId);
    }

    @Override
    public void insertSysconfig(Sysconfig sysconfig) {
        sysconfigMapper.insertSelective(sysconfig);
    }

    @Override
    public List<Sysconfig> querySysconfigList(String name) {
        SysconfigExample sysconfigExample = new SysconfigExample();
        SysconfigExample.Criteria criteria =  sysconfigExample.createCriteria();
        if (name != null && !"".equals(name)){
            criteria.andNameLike("%"+name+"%");
        }
        List<Sysconfig> sysconfigs = sysconfigMapper.selectByExample(sysconfigExample);
        return sysconfigs;
    }

    @Override
    public Sysconfig querySysconfigById(Long configId) {

        return sysconfigMapper.selectByPrimaryKey(configId);
    }
}
