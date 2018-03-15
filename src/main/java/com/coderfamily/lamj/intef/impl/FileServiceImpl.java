package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.Const;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.intef.IFileService;
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
    @Value("#{fileProperties['file.upload.document.article']}")
    private String FileUploadDocumentArticle;

    @Override
    public Result fileUpload(MultipartFile file, String type, HttpServletRequest request) {
        Result result = null;
        if (!file.isEmpty()) {
            try {
                String path = "";
                switch (type) {
                    case Const.File_Type_Image:
                        request.getServletContext().getRealPath(FileUploadImagePhotoPath);
                        break;
                    case Const.File_Type_Article:
                        request.getServletContext().getRealPath(FileUploadDocumentArticle);
                        break;
                }
                if (NullUtil.isNull(path)) {
                    result = Result.error("文件路径不正确，文件上传失败");
                } else {
                    String contentType = file.getContentType();
                    String fileName = getPhotoFileName();
                    String fileAttr = contentType.split("/")[1];
                    String fileUrl = path + File.separator + fileName + "." + fileAttr;
                    File filePath = new File(path, fileName);
                    if (!filePath.getParentFile().exists()) {
                        filePath.getParentFile().mkdirs();
                    }
                    file.transferTo(new File(fileUrl));
                    result = Result.success(FileUploadImagePhotoPath + File.separator + fileName + "." + fileAttr);
                }
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
