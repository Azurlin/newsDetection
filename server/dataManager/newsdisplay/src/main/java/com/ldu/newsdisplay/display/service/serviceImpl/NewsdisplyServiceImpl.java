package com.ldu.newsdisplay.display.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.ldu.newsdisplay.display.dao.NewsdisplyMapper;
import com.ldu.newsdisplay.display.domain.Newsdisply;
import com.ldu.newsdisplay.display.domain.NewsdisplyExample;
import com.ldu.newsdisplay.display.service.NewsdisplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//TODO 接口实现
@Service
public class NewsdisplyServiceImpl implements NewsdisplyService {
    @Resource
    private NewsdisplyMapper newsdisplyMapper;

    //分页查询
    @Override
    public List<Newsdisply> queryAllNewsForPage(Long datasetId,Integer pageNum, Integer pageSize,String title) {
        NewsdisplyExample newsdisplyExample = new NewsdisplyExample();
        newsdisplyExample.setOrderByClause("id asc");
        NewsdisplyExample.Criteria criteria = newsdisplyExample.createCriteria();
        criteria.andDatasetIdEqualTo(datasetId);
        //模糊查询--根据标题左右模糊查询
        if (title != null && !"".equals(title)){
            criteria.andTitleLike("%"+title+"%");
        }
        if(pageNum!=null && pageSize!=null){
            newsdisplyExample.setPageSize(pageSize);
            newsdisplyExample.setStartRow(pageNum*pageSize);
            return newsdisplyMapper.selectByExampleWithBLOBs(newsdisplyExample);
        }
        return newsdisplyMapper.selectByExampleWithBLOBs(newsdisplyExample);
    }

    //查询--根据id查询
    @Override
    public Newsdisply queryNewsById(Long id) {
        return newsdisplyMapper.selectByPrimaryKey(id);
    }

    //新增新闻
    @Override
    public Newsdisply insertNews(Newsdisply newsdisply) {
        newsdisplyMapper.insertSelective(newsdisply);
        return newsdisplyMapper.selectByPrimaryKey(newsdisply.getId());
    }

    //删除新闻
    @Override
    public void deleteNewsById(Long id) {
        newsdisplyMapper.deleteByPrimaryKey(id);
    }

    //编辑新闻
    @Override
    public Newsdisply updateNews(Newsdisply newsdisply) {
        newsdisplyMapper.updateByPrimaryKeySelective(newsdisply);
        return newsdisplyMapper.selectByPrimaryKey(newsdisply.getId());
    }
}
