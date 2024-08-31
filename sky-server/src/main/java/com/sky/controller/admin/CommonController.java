package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Tag(name = "common interfaces")
@Slf4j
public class CommonController {

    // TODO: upload file to cloud providers (e.g., Azure, AWS, AliYun)
    @PostMapping("/upload")
    @Operation(description = "upload file")
    public Result<String> upload(MultipartFile file) {
        log.info("upload file: {}", file.getOriginalFilename());

        try {
            String originalFilename = file.getOriginalFilename();

            assert originalFilename != null;
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            String objectName = UUID.randomUUID().toString() + extension;

            File localFile = new File("E:/images/" + objectName);
            log.info("upload file to: {}", localFile.getAbsolutePath());

            file.transferTo(localFile);
            return Result.success(objectName);
        } catch (Exception e) {
            log.error("failed to upload file", e);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
