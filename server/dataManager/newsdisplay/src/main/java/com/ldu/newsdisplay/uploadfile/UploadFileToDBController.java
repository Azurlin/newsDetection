package com.ldu.newsdisplay.uploadfile;

import com.ldu.newsdisplay.dataset.dao.DatasetMapper;
import com.ldu.newsdisplay.dataset.domain.Dataset;
import com.ldu.newsdisplay.display.dao.NewsdisplyMapper;
import com.ldu.newsdisplay.display.domain.Newsdisply;
import com.ldu.newsdisplay.utils.ControllerResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

@RestController
public class UploadFileToDBController {
    @Resource
    private NewsdisplyMapper newsdisplyMapper;
    @Resource
    private DatasetMapper datasetMapper;
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public static String NOTITLE = "无标题";

    /*
    * 上传文件并将数据导入数据库
    * filename:UUID
    * filepath:uploadFile/
    * */
    @RequestMapping("/upload")
    @ResponseBody
    @Transactional
    public ControllerResponse handleUploadFile(@RequestParam("datasetId") Long datasetId,
                                               @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                String filename =  UUID.randomUUID().toString() + ".csv";//UUID作为文件唯一标识
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("uploadFile/"+filename)));
                System.out.println("文件名："+filename);
                out.write(file.getBytes());
                out.flush();
                out.close();
                try{
                    FileDataToDB(filename,datasetId);
                }catch (Exception e){
                    logger.error("上传失败, message:{}",e.getMessage());
                    return ControllerResponse.fail("写入数据库失败");
                }
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("上传失败, message:{}",e.getMessage());
                return ControllerResponse.fail("上传失败");
            }

            return ControllerResponse.success("上传成功");

        } else {

            return ControllerResponse.fail("上传失败,空文件");
        }
    }
    //读文件并写入数据库
    public void FileDataToDB(String fileName, Long datasetId){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("uploadFile/"+fileName));
            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            while((line=reader.readLine())!=null){
                String item[] = line.split(",",2);//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                String title =item[0];
                String text =item[1];
                if(title==null || title.length()==0){
                    title = NOTITLE;
                }
                Newsdisply newsdisply = new Newsdisply();
                newsdisply.setDatasetId(datasetId);
                newsdisply.setText(text);
                newsdisply.setTitle(title);
                newsdisplyMapper.insertSelective(newsdisply);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    @Test
//    public void test(){
//        String str =  UUID.randomUUID().toString();
//        System.out.println(str);
//    }
}
