package com.ldu.newsdisplay.display.service;

import com.ldu.newsdisplay.display.domain.News;

import java.util.List;

public interface NewsService {
    News queryNewsById(Long id);

    List<News> queryNewsList(String title, String author, String label, Byte sign,Integer pageNum,Integer pageSize);

    void insertNews(News news);

    void deleteNews(Long id);

    void updateNews(News news);
}
