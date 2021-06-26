package com.ldu.newsdisplay.display.service.serviceImpl;

import com.ldu.newsdisplay.display.dao.NewsMapper;
import com.ldu.newsdisplay.display.domain.News;
import com.ldu.newsdisplay.display.domain.NewsExample;
import com.ldu.newsdisplay.display.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class NewsServiceImpl implements NewsService {
    @Resource
    private NewsMapper newsMapper;

    @Override
    public News queryNewsById(Long id) {
        return newsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<News> queryNewsList(String title, String author, String label, Byte sign,Integer pageNum,Integer pageSize) {
        NewsExample newsExample = new NewsExample();
        NewsExample.Criteria criteria = newsExample.createCriteria();
        if (title != null && !"".equals(title)){
            criteria.andTitleLike("%"+title+"%");
        }
        if (author != null && !"".equals(author)){
            criteria.andAuthorLike("%"+author+"%");
        }
        if (!"0".equals(label) && label != null && !"".equals(label)){
            criteria.andLabelEqualTo(label);
        }
        if (sign != null){
            criteria.andSignEqualTo(sign);
        }
        if(pageNum!=null && pageSize!=null){ //分页
            newsExample.setPageSize(pageSize);
            newsExample.setStartRow(pageNum*pageSize);
            return newsMapper.selectByExampleWithBLOBs(newsExample);
        }
        return newsMapper.selectByExampleWithBLOBs(newsExample);
    }

    @Override
    public void insertNews(News news) {
        newsMapper.insertSelective(news);
    }

    @Override
    public void deleteNews(Long id) {
        newsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateNews(News news) {
        newsMapper.updateByPrimaryKeySelective(news);
    }
}
