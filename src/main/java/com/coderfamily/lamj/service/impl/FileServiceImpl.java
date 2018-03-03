package com.coderfamily.lamj.service.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.service.IFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author ZhangDL
 * @date 2018/3/2 14:20
 */
@Service
public class FileServiceImpl implements IFileService {

    @Value("#{fileProperties['file.upload.image.photo']}")
    private String FileUploadImagePhotoPath;

    @Override
    public Result fileUpload(MultipartFile file, HttpServletRequest request) {
        Result result = null;
        if (!file.isEmpty()) {
            try {
                String contentType = file.getContentType();
                String fileName = getPhotoFileName();
                String fileAttr = contentType.split("/")[1];
                String path = request.getServletContext().getRealPath(FileUploadImagePhotoPath);
                String fileUrl = path + File.separator + fileName + "." + fileAttr;
                File filePath = new File(path, fileName);
                if (!filePath.getParentFile().exists()) {
                    filePath.getParentFile().mkdirs();
                }

                file.transferTo(new File(fileUrl));
                result = Result.success(FileUploadImagePhotoPath + File.separator + fileName + "." + fileAttr);
            } catch (IOException e) {
                result = Result.error("文件上传失败");
            }
        } else {
            result = Result.error("请不要上传空文件");
        }
        return result;
    }

    @Override
    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.exists() ? file.delete() : true;
    }

    /**
     * 获取头像文件名称
     *
     * @return
     */
    private String getPhotoFileName() {
        String fileName = "photo_" + new Date().getTime();
        return fileName;
    }
}
