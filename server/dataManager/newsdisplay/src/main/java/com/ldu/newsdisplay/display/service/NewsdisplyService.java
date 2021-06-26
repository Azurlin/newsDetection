package com.ldu.newsdisplay.display.service;

import com.github.pagehelper.PageInfo;
import com.ldu.newsdisplay.display.domain.Newsdisply;
import org.springframework.stereotype.Service;

import java.util.List;


public interface NewsdisplyService {

    List<Newsdisply> queryAllNewsForPage(Long datasetId,Integer pageNum, Integer pageSize,String title);

    Newsdisply queryNewsById(Long id);

    Newsdisply insertNews(Newsdisply newsdisply);

    void deleteNewsById(Long id);

    Newsdisply updateNews(Newsdisply newsdisply);
}
