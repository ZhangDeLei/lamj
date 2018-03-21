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
    @Value("#{fileProperties['file.upload.app']}")
    private String FileUploadApp;

    @Override
    public Result fileUpload(MultipartFile file, String type, HttpServletRequest request) {
        Result result = null;
        if (!file.isEmpty()) {
            try {
                Path path = getFilePath(type, request);
                if (NullUtil.isNull(path)) {
                    result = Result.error("文件路径不正确，文件上传失败");
                } else {
                    String fileName = getFileName(type);
                    String fileAttr = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'), file.getOriginalFilename().length());
                    String fileUrl = path.getPath() + File.separator + fileName + fileAttr;
                    File filePath = new File(path.getPath(), fileName + fileAttr);
                    if (!filePath.getParentFile().exists()) {
                        filePath.getParentFile().mkdirs();
                    }
                    file.transferTo(new File(fileUrl));
                    result = Result.success(path.getReturnPath() + File.separator + fileName + fileAttr);
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
     * 获取文件路径
     *
     * @return
     */
    private Path getFilePath(String type, HttpServletRequest request) {
        Path path = new Path();
        switch (type) {
            case Const.File_Type_Image:
                path.setPath(request.getServletContext().getRealPath(FileUploadImagePhotoPath));
                path.setReturnPath(FileUploadImagePhotoPath);
                break;
            case Const.File_Type_Article:
                path.setPath(request.getServletContext().getRealPath(FileUploadDocumentArticle));
                path.setReturnPath(FileUploadDocumentArticle);
                break;
            case Const.File_Type_App:
                path.setPath(request.getServletContext().getRealPath(FileUploadApp));
                path.setReturnPath(FileUploadApp);
                break;
        }
        return path;
    }

    /**
     * 获取文件名称
     *
     * @return
     */
    private String getFileName(String type) {
        String fileName = "";
        switch (type) {
            case Const.File_Type_Article:
                fileName = "article_" + new Date().getTime();
                break;
            case Const.File_Type_Image:
                fileName = "photo_" + new Date().getTime();
                break;
            case Const.File_Type_App:
                fileName = "app_" + new Date().getTime();
                break;
        }
        return fileName;
    }

    private class Path {
        private String path;
        private String returnPath;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getReturnPath() {
            return returnPath;
        }

        public void setReturnPath(String returnPath) {
            this.returnPath = returnPath;
        }
    }
}
