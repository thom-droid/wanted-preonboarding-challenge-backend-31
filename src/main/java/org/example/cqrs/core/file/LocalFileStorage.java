package org.example.cqrs.core.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class LocalFileStorage implements FileStorageService {

    private final FileStorageProperties fileStorageProperties;

    @Override
    public String uploadFile(MultipartFile file) {
        String filePath = fileStorageProperties.getUploadDir() + "/" + file.getOriginalFilename();
        try {
            file.transferTo(new java.io.File(filePath));
        } catch (Exception e) {
            throw new RuntimeException("Failed to store file " + file.getOriginalFilename(), e);
        }
        return filePath;
    }

}
