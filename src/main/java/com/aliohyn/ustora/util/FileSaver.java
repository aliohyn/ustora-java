package com.aliohyn.ustora.util;

import com.aliohyn.ustora.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileSaver {
    @Value("${upload.path}")
    private String baseUploadPath;

    private String uploadPath;


    public String save(MultipartFile file, String type) throws IOException {
        String resultFilename = new String();
        uploadPath = baseUploadPath + "/" + type;
        if (file != null && !file.getOriginalFilename().isEmpty()) {

            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));
        }

        return resultFilename;
    }
}
