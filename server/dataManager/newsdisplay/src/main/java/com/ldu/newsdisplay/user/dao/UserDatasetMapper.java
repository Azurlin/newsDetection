package com.ldu.newsdisplay.user.dao;

import com.ldu.newsdisplay.user.domain.UserDataset;
import com.ldu.newsdisplay.user.domain.UserDatasetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDatasetMapper {
    long countByExample(UserDatasetExample example);

    int deleteByExample(UserDatasetExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserDataset record);

    int insertSelective(UserDataset record);

    List<UserDataset> selectByExample(UserDatasetExample example);

    UserDataset selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserDataset record, @Param("example") UserDatasetExample example);

    int updateByExample(@Param("record") UserDataset record, @Param("example") UserDatasetExample example);

    int updateByPrimaryKeySelective(UserDataset record);

    int updateByPrimaryKey(UserDataset record);
}