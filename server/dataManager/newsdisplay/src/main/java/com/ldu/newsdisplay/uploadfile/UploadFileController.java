package com.ldu.newsdisplay.uploadfile;

import com.ldu.newsdisplay.utils.ControllerResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;
import java.util.UUID;

@RestController
public class UploadFileController {
   private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

   @RequestMapping("/uploadFile")
   @ResponseBody
   public ControllerResponse handleUploadFile(@RequestParam("file")MultipartFile file){
       if (!file.isEmpty()) {
           try {
               String filename =  UUID.randomUUID().toString() + ".csv";//UUID作为文件唯一标识
               BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("uploadFile/"+filename)));
               System.out.println("文件名："+filename);
               out.write(file.getBytes());
               out.flush();
               out.close();
           } catch (IOException e) {
               e.printStackTrace();
               //return "上传失败," + e.getMessage();
               logger.error("上传失败, message:{}",e.getMessage());
               return ControllerResponse.fail();
           }

           return ControllerResponse.success("上传成功");

       } else {

           return ControllerResponse.fail("上传失败");
       }
   }

}
