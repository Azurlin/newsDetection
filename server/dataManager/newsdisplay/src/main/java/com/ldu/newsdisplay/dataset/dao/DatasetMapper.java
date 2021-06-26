package com.ldu.newsdisplay.dataset.dao;

import com.ldu.newsdisplay.dataset.domain.Dataset;
import com.ldu.newsdisplay.dataset.domain.DatasetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DatasetMapper {
    long countByExample(DatasetExample example);

    int deleteByExample(DatasetExample example);

    int deleteByPrimaryKey(Long datasetId);

    int insert(Dataset record);

    int insertSelective(Dataset record);

    List<Dataset> selectByExample(DatasetExample example);

    Dataset selectByPrimaryKey(Long datasetId);

    int updateByExampleSelective(@Param("record") Dataset record, @Param("example") DatasetExample example);

    int updateByExample(@Param("record") Dataset record, @Param("example") DatasetExample example);

    int updateByPrimaryKeySelective(Dataset record);

    int updateByPrimaryKey(Dataset record);
}