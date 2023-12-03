package com.suye.personalblog.admincontroller;

import com.suye.personalblog.FileClient.task.UploadTask;
import com.suye.personalblog.StaticField;
import com.suye.personalblog.service.FileService;
import com.suye.personalblog.service.LogMessageService;
import com.suye.personalblog.tool.FileNameConversion;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
public class FileController {
    @Value("${img.address}")
    private String temp;
    @Value("${img.server}")
    private String tempIp;

    @Autowired
    private FileService fileService;
    @Autowired
    private LogMessageService logMessageService;
    @Autowired
    private UploadTask uploadTask;

    @RequestMapping("/admin/attach/delete")
    @ResponseBody
    public String deleteFile(@RequestParam("id")int id){
        //System.out.println("123");
        if (fileService.deleteFileById(id)>0){
            logMessageService.addALog(StaticField.DELETE_FILE);
            return "{\n" + "  \"success\":\"删除成功\"\n" + "}";
        }
        return "{\n" + "  \"msg\":\"删除异常\"\n" + "}";
    }

    @RequestMapping("/admin/attach/upload")
    @ResponseBody
    public String uploadFile(MultipartFile file) throws IOException {
        String origName=file.getOriginalFilename();
        File imgFile=new File(temp,origName);
        while (imgFile.exists()){
            origName=FileNameConversion.fileNameConversion(origName);
            imgFile=new File(temp,origName);
        }
        if (!imgFile.getParentFile().exists()){
            imgFile.getParentFile().mkdirs();
        }
        System.out.println(imgFile.getAbsoluteFile());
        OutputStream outputStream=new FileOutputStream(imgFile);
        InputStream inputStream=file.getInputStream();
        IOUtils.copy(file.getInputStream(),outputStream);
        outputStream.flush();
        fileService.addFile(imgFile.getName(),tempIp+imgFile.getName());
        int fileId=fileService.findFileIdByFileName(imgFile.getName());
        //netty传输
        outputStream.close();
        inputStream.close();
        logMessageService.addALog(StaticField.ADD_FILE);
        return "{\n" + "  \"success\":\""+fileId+"\"\n" + "}";
    }
}
