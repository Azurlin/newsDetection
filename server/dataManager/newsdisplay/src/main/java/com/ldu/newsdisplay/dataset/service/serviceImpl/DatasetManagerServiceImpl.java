package com.ldu.newsdisplay.dataset.service.serviceImpl;

import com.ldu.newsdisplay.dataset.dao.DatasetMapper;
import com.ldu.newsdisplay.dataset.domain.Dataset;
import com.ldu.newsdisplay.dataset.domain.DatasetExample;
import com.ldu.newsdisplay.dataset.service.DatasetManagerService;
import com.ldu.newsdisplay.display.dao.NewsdisplyMapper;
import com.ldu.newsdisplay.display.domain.NewsdisplyExample;
import com.ldu.newsdisplay.user.dao.UserDatasetMapper;
import com.ldu.newsdisplay.user.domain.UserDataset;
import com.ldu.newsdisplay.user.domain.UserDatasetExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class DatasetManagerServiceImpl implements DatasetManagerService {
    @Resource
    private UserDatasetMapper userDatasetMapper;
    @Resource
    private DatasetMapper datasetMapper;
    @Resource
    private NewsdisplyMapper newsdisplyMapper;
    @Override
    public Dataset insertDataset(Dataset dataset) {
        datasetMapper.insertSelective(dataset);
        return datasetMapper.selectByPrimaryKey(dataset.getDatasetId());
    }

    @Override
    public void deleteDataset(Long datasetId) {
        datasetMapper.deleteByPrimaryKey(datasetId);
        //删除此数据集中的所有记录
        NewsdisplyExample newsdisplyExample = new NewsdisplyExample();
        NewsdisplyExample.Criteria newsdisplyExampleCriteria = newsdisplyExample.createCriteria();
        newsdisplyExampleCriteria.andDatasetIdEqualTo(datasetId);
        newsdisplyMapper.deleteByExample(newsdisplyExample);
    }

    @Override
    public Dataset updateDataset(Long datasetId, Dataset dataset) {
        datasetMapper.updateByPrimaryKeySelective(dataset);
        return datasetMapper.selectByPrimaryKey(datasetId);
    }
    //根据id查询
    @Override
    public Dataset selectDatasetBydatasetId(Long datasetId) {
        return datasetMapper.selectByPrimaryKey(datasetId);
    }

    //列表查询
    @Override
    public List<Dataset> selectDatasetList() {
        DatasetExample datasetExample = new DatasetExample();
        return datasetMapper.selectByExample(datasetExample);
    }

    @Override
    public List<Integer> countNews(Long datasetId) {
        Dataset dataset = new Dataset();
        NewsdisplyExample newsdisplyExample = new NewsdisplyExample();
        NewsdisplyExample.Criteria criteria = newsdisplyExample.createCriteria();
        criteria.andDatasetIdEqualTo(datasetId);//总数
        Integer newsCount = newsdisplyMapper.countByExample(newsdisplyExample);
        criteria.andSignEqualTo(false);//假新闻数
        Integer fakeCount = newsdisplyMapper.countByExample(newsdisplyExample);
        List<Integer> newsCountList = new ArrayList<>();
        newsCountList.add(newsCount);
        newsCountList.add(fakeCount);
        dataset.setFakeCount(fakeCount);
        dataset.setNewsCount(newsCount);
        dataset.setDatasetId(datasetId);
        datasetMapper.updateByPrimaryKeySelective(dataset);
        return newsCountList;
    }

    @Override
    public List<Dataset> selectDatasetByUserId(Long userId) {
        //查询此用户下的数据集id
        UserDatasetExample userDatasetExample = new UserDatasetExample();
        UserDatasetExample.Criteria userDatasetExampleCriteria = userDatasetExample.createCriteria();
        userDatasetExampleCriteria.andUserIdEqualTo(userId);
        List<UserDataset> datasetList =  userDatasetMapper.selectByExample(userDatasetExample);
        List<Long> datasetIdList = new ArrayList<>();
        for (UserDataset i : datasetList){ //取出数据集id
            datasetIdList.add(i.getDatasetId());
        }

        //查询数据集
        DatasetExample datasetExample = new DatasetExample();
        DatasetExample.Criteria datasetExampleCriteria = datasetExample.createCriteria();
        datasetExampleCriteria.andDatasetIdIn(datasetIdList);

        return datasetMapper.selectByExample(datasetExample);
    }
}
