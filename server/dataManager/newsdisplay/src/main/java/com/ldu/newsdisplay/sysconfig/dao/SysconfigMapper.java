package com.ldu.newsdisplay.sysconfig.dao;

import com.ldu.newsdisplay.sysconfig.domain.Sysconfig;
import com.ldu.newsdisplay.sysconfig.domain.SysconfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysconfigMapper {
    long countByExample(SysconfigExample example);

    int deleteByExample(SysconfigExample example);

    int deleteByPrimaryKey(Long configId);

    int insert(Sysconfig record);

    int insertSelective(Sysconfig record);

    List<Sysconfig> selectByExample(SysconfigExample example);

    Sysconfig selectByPrimaryKey(Long configId);

    int updateByExampleSelective(@Param("record") Sysconfig record, @Param("example") SysconfigExample example);

    int updateByExample(@Param("record") Sysconfig record, @Param("example") SysconfigExample example);

    int updateByPrimaryKeySelective(Sysconfig record);

    int updateByPrimaryKey(Sysconfig record);
}