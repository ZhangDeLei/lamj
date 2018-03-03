package com.coderfamily.lamj.service;

import com.coderfamily.lamj.common.data.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhangDL
 * @date 2018/3/2 14:19
 */
public interface IFileService {

    /**
     * 文件上传
     * @param file
     * @return
     */
    Result fileUpload(MultipartFile file, HttpServletRequest request);

    /**
     * 删除文件
     * @param filePath
     * @return
     */
    boolean deleteFile(String filePath);
}
