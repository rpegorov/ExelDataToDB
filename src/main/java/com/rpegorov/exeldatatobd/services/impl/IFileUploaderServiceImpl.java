package com.rpegorov.exeldatatobd.services.impl;

import com.rpegorov.exeldatatobd.models.entity.Product;
import com.rpegorov.exeldatatobd.services.interf.IFileUploaderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Class service to upload file.
 */
@Service
public class IFileUploaderServiceImpl implements IFileUploaderService {

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    @Override
    public void uploadFile(MultipartFile file) {

        try {
            var copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
    }
}
