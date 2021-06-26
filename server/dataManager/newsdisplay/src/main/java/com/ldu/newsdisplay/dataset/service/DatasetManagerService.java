package com.ldu.newsdisplay.dataset.service;

import com.ldu.newsdisplay.dataset.domain.Dataset;

import java.util.List;

public interface DatasetManagerService {
    Dataset insertDataset(Dataset dataset);

    void deleteDataset(Long datasetId);

    Dataset updateDataset(Long datasetId, Dataset dataset);

    Dataset selectDatasetBydatasetId(Long datasetId);

    List<Dataset> selectDatasetList();

    List<Integer> countNews(Long datasetId);

    List<Dataset> selectDatasetByUserId(Long userId);
}
