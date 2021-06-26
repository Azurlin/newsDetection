package com.ldu.newsdisplay.display.dao;

import com.ldu.newsdisplay.display.domain.Newsdisply;
import com.ldu.newsdisplay.display.domain.NewsdisplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsdisplyMapper {
    int countByExample(NewsdisplyExample example);

    int deleteByExample(NewsdisplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Newsdisply record);

    int insertSelective(Newsdisply record);

    List<Newsdisply> selectByExampleWithBLOBs(NewsdisplyExample example);

    List<Newsdisply> selectByExample(NewsdisplyExample example);

    Newsdisply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Newsdisply record, @Param("example") NewsdisplyExample example);

    int updateByExampleWithBLOBs(@Param("record") Newsdisply record, @Param("example") NewsdisplyExample example);

    int updateByExample(@Param("record") Newsdisply record, @Param("example") NewsdisplyExample example);

    int updateByPrimaryKeySelective(Newsdisply record);

    int updateByPrimaryKeyWithBLOBs(Newsdisply record);

    int updateByPrimaryKey(Newsdisply record);
}