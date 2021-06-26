package com.ldu.newsdisplay.display.rest;

import com.ldu.newsdisplay.display.domain.News;
import com.ldu.newsdisplay.display.service.NewsService;
import com.ldu.newsdisplay.utils.ControllerResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class NewsController {
    @Resource
    private NewsService newsService;
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @GetMapping("/query/{id}")
    public ControllerResponse getNewsById(@PathVariable(value = "id") Long id){
        try {
            News res = newsService.queryNewsById(id);
            return ControllerResponse.success(res);
        }catch (Exception e){
            logger.error("查询异常，Message:{}",e.getMessage());
            return ControllerResponse.fail();
        }
    }

    /*
    列表查询
    条件查询
        --按标题
        --按作者
        --按分类
        --按标识（1真，0假，2待检测）
     */
    @GetMapping("/queryList")
    public ControllerResponse getNewsList(@RequestParam(value = "title",required = false) String title,
                                          @RequestParam(value = "author",required = false) String author,
                                          @RequestParam(value = "label",required = false) String label,
                                          @RequestParam(value = "sign",required = false) Byte sign,
                                          @RequestParam(value = "pageNum",required = false) Integer pageNum,
                                          @RequestParam(value = "pageSize",required = false) Integer pageSize
    ){
        try {
            List<News> resList =  newsService.queryNewsList(title,author,label,sign,pageNum,pageSize);
            return ControllerResponse.success(resList);
        }catch (Exception e){
            logger.error("查询异常，Message:{}",e.getMessage());
            return ControllerResponse.fail();
        }
    }

    /* 新增新闻

     */
    @PostMapping("/add")
    public ControllerResponse addNews(@RequestBody News news){
        try {
            newsService.insertNews(news);
            return ControllerResponse.success();
        }catch (Exception e){
            logger.error("新增异常，Message:{}",e.getMessage());
            return ControllerResponse.fail();
        }
    }

    /* 删除新闻 --单条删除

     */
    @PostMapping("/delete/{id}")
    public ControllerResponse deleteNews(@PathVariable(value = "id") Long id){
        try {
            newsService.deleteNews(id);
            return ControllerResponse.success();
        }catch (Exception e){
            logger.error("删除异常，Message:{}",e.getMessage());
            return ControllerResponse.fail();
        }
    }

    /* 修改新闻

     */
    @PostMapping("/modify")
    public  ControllerResponse updateNews(@RequestBody News news){
        try {
            newsService.updateNews(news);
            return ControllerResponse.success();
        }catch (Exception e){
            logger.error("删除异常，Message:{}",e.getMessage());
            return ControllerResponse.fail();
        }
    }
}
