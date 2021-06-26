package com.ldu.newsdisplay.display.rest;

import com.github.pagehelper.PageInfo;
import com.ldu.newsdisplay.display.domain.Newsdisply;
import com.ldu.newsdisplay.display.service.NewsdisplyService;
import com.ldu.newsdisplay.utils.ControllerResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class NewsdisplyController {
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Resource
    private NewsdisplyService newsdisplyService;

    /**
    *根据id查询
    *@param id
    *@return com.ldu.newsdisplay.utils.ControllerResponse
    * */
    @GetMapping("/newsdisply/query/{id}")
    public ControllerResponse getNewsById(@PathVariable("id") Long id){
        try {
            Newsdisply newsdisply =  newsdisplyService.queryNewsById(id);
            return ControllerResponse.success("查询成功",newsdisply);
        }catch (Exception e){
            logger.error("查询异常, message:{}",e.getMessage());
            return ControllerResponse.fail("查询失败");
        }
    }

    /**
    * 分页查询
     * -根据新闻标题title模糊查询
     * -无page参数则查询所有记录
     *@param datasetId 数据集id
     *@param pageSize 每页记录数
     *@param pageNum 当前页
     *@param title 标题模糊查询条件
     *@return com.ldu.newsdisplay.utils.ControllerResponse
    * */
    @GetMapping("/newsdisply/query/newsList/{datasetId}")
    public ControllerResponse getNewsList(@PathVariable("datasetId") Long datasetId,
                                          @RequestParam(value = "pageNum",required = false) Integer pageNum,
                                          @RequestParam(value = "pageSize",required = false) Integer pageSize,
                                          @RequestParam(value = "title",required = false) String title){
        try {
            List<Newsdisply> page = newsdisplyService.queryAllNewsForPage(datasetId,pageNum,pageSize,title);
            return ControllerResponse.success("查询成功",page);
        }catch (Exception e){
            logger.error("查询异常, message:{}",e.getMessage());
            return ControllerResponse.fail("查询失败");
        }
    }

    /**
    *添加新闻 TODO --传入数据集id
    *@param newsdisply
    * */
    @PostMapping("/newsdisply/add")
    public ControllerResponse insertNews(@RequestBody Newsdisply newsdisply){
        try {
            Newsdisply result =  newsdisplyService.insertNews(newsdisply);
            return  ControllerResponse.success("添加成功",result);
        }catch (Exception e){
            logger.error("新增异常, message:{}",e.getMessage());
            return ControllerResponse.fail("添加失败");
        }
    }

    /**
    * 删除新闻
    * @param id
    * */
    @PostMapping("/newsdisply/{id}/delete")
    public ControllerResponse deleteNews(@PathVariable("id") Long id){
        try {
            newsdisplyService.deleteNewsById(id);
            return ControllerResponse.success("删除成功");
        }catch (Exception e){
            logger.error("删除异常, message:{}",e.getMessage());
            return ControllerResponse.fail("删除失败");
        }
    }

    /**
    * 编辑新闻
    * @param id
    * @param newsdisply
    * */
    @PostMapping("/newsdisply/{id}/modify")
    public ControllerResponse updateNews(@PathVariable("id") Long id,
                                         @RequestBody Newsdisply newsdisply){
        try {
            newsdisply.setId(id);
            Newsdisply result = newsdisplyService.updateNews(newsdisply);
            return  ControllerResponse.success("修改成功",result);
        }catch (Exception e){
            logger.error("修改异常, message:{}",e.getMessage());
            return ControllerResponse.fail("修改失败");
        }
    }
}
