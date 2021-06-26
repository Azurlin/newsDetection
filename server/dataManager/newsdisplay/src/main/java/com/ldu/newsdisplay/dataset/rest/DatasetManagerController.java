package com.ldu.newsdisplay.dataset.rest;

import com.ldu.newsdisplay.dataset.domain.Dataset;
import com.ldu.newsdisplay.dataset.service.DatasetManagerService;
import com.ldu.newsdisplay.dataset.service.serviceImpl.DatasetManagerServiceImpl;
import com.ldu.newsdisplay.utils.ControllerResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
public class DatasetManagerController {
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Resource
    private DatasetManagerService datasetManagerService;

    /**
     * 新增数据集
     * @param dataset
     **/
    @PostMapping("/datasetManager/add")
    public ControllerResponse insertDataset(@RequestBody Dataset dataset){
        try {
            Dataset res = datasetManagerService.insertDataset(dataset);
            return ControllerResponse.success("新增成功",res);
        }catch (Exception e){
            logger.error("新增异常, message:{}",e.getMessage());
            return ControllerResponse.fail("新增失败");
        }
    }

    /**
     * 删除数据集
     * @param datasetId
     **/
    @PostMapping("/datasetManager/{datasetId}/delete")
    public ControllerResponse deleteDataset(@PathVariable("datasetId") Long datasetId){
        try {
            datasetManagerService.deleteDataset(datasetId);
            return ControllerResponse.success("删除成功");
        }catch (Exception e){
            logger.error("删除异常, message:{}",e.getMessage());
            return ControllerResponse.fail("删除失败");
        }
    }

    /**
     * 修改数据集信息
     * @param datasetId
     * @param dataset
     **/
    @PostMapping("/datasetManager/{datasetId}/modify")
    public ControllerResponse updateDataset(@PathVariable("datasetId") Long datasetId,
                                            @RequestBody Dataset dataset){
        try {
            dataset.setDatasetId(datasetId);
            Dataset res = datasetManagerService.updateDataset(datasetId,dataset);
            return ControllerResponse.success("修改成功",res);
        }catch (Exception e){
            logger.error("修改异常, message:{}",e.getMessage());
            return ControllerResponse.fail("修改失败");
        }
    }

    /**
     * 列表查询
     **/
    @GetMapping("/datasetManager/queryList")
    public ControllerResponse queryDataset(){
        try {
            List<Dataset> resList =  datasetManagerService.selectDatasetList();
            return ControllerResponse.success("查询成功",resList);
        }catch (Exception e){
            logger.error("查询异常, message:{}",e.getMessage());
            return ControllerResponse.fail("查询失败");
        }
    }

    /**
     *根据用户查询数据集
     * @param UserId
     */
    @GetMapping("/datasetManager/queryByUser/{UserId}")
    public ControllerResponse getDatasetByUser(@PathVariable(value = "UserId",required = false) Long UserId){
        try {
            List<Dataset> resList =  datasetManagerService.selectDatasetByUserId(UserId);
            return ControllerResponse.success("查询成功",resList);
        }catch (Exception e){
            logger.error("查询异常, message:{}",e.getMessage());
            return ControllerResponse.fail("查询失败");
        }
    }

    /**
     * 查询数据集
     * @param datasetId
     **/
    @GetMapping("/datasetManager/query/{datasetId}")
    public ControllerResponse queryDataset(@PathVariable("datasetId") Long datasetId){
        try {
            Dataset dataset=  datasetManagerService.selectDatasetBydatasetId(datasetId);
            return ControllerResponse.success("查询成功",dataset);
        }catch (Exception e){
            logger.error("查询异常, message:{}",e.getMessage());
            return ControllerResponse.fail("查询失败");
        }
    }

    /**
     * 统计
     *@param datasetId
     **/
    @PostMapping("/datasetManager/count/{datasetId}")
    public ControllerResponse countNews(@PathVariable("datasetId") Long datasetId){
        try {
            List<Integer> resList = datasetManagerService.countNews(datasetId);
            return ControllerResponse.success(resList);
        }catch (Exception e){
            logger.error("count异常，mes:{}",e.getMessage());
            return ControllerResponse.fail();
        }
    }
}
