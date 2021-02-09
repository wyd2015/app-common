package com.example.appcommon.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: wcg
 * @Date: 2021/2/7 9:05
 */
@Data
public class FileVO {
    @Schema(description = "MultipartFile文件", required = true)
    private MultipartFile file;
}
