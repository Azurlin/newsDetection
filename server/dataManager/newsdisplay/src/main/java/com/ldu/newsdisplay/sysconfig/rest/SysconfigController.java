package com.ldu.newsdisplay.sysconfig.rest;

import com.ldu.newsdisplay.sysconfig.domain.Sysconfig;
import com.ldu.newsdisplay.sysconfig.service.SysconfigService;
import com.ldu.newsdisplay.utils.ControllerResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SysconfigController {
    @Resource
    private SysconfigService sysconfigService;
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    @GetMapping("/admin/sysconfigManager/query/{configId}")
    public ControllerResponse getSysconfigById(@PathVariable("configId") Long configId){
        try {
            //TODO
            Sysconfig res = sysconfigService.querySysconfigById(configId);
            return ControllerResponse.success(res);
        }catch (Exception e){
            logger.error("message:{}",e.getMessage());
            return ControllerResponse.fail("查询失败");
        }
    }
    @GetMapping("/admin/sysconfigManager/queryList")
    public ControllerResponse getSysconfigAll(@RequestParam(value = "name", required = false) String name){
        try {
            //TODO
            List<Sysconfig> resList = sysconfigService.querySysconfigList(name);
            return ControllerResponse.success();
        }catch (Exception e){
            logger.error("message:{}",e.getMessage());
            return ControllerResponse.fail("查询失败");
        }
    }
    @PostMapping("/admin/sysconfigManager/add")
    public ControllerResponse insertSysconfig(@RequestBody Sysconfig sysconfig){
        try {
            //TODO
            sysconfigService.insertSysconfig(sysconfig);
            return ControllerResponse.success();
        }catch (Exception e){
            logger.error("message:{}",e.getMessage());
            return ControllerResponse.fail("新增失败");
        }
    }
    @PostMapping("/admin/sysconfigManager/{configId}/delete")
    public ControllerResponse deleteSysconfig(@PathVariable("configId") Long configId){
        try {
            //TODO
            sysconfigService.deleteSysconfig(configId);
            return ControllerResponse.success();
        }catch (Exception e){
            logger.error("message:{}",e.getMessage());
            return ControllerResponse.fail("删除失败");
        }
    }
    @PostMapping("/admin/sysconfigManager/modify")
    public ControllerResponse modifySysconfig(@RequestBody Sysconfig sysconfig){
        try {
            //TODO
            sysconfigService.updateSysconfig(sysconfig);
            return ControllerResponse.success();
        }catch (Exception e){
            logger.error("message:{}",e.getMessage());
            return ControllerResponse.fail("修改失败");
        }
    }
}
