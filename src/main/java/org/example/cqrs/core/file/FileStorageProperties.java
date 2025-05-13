package org.example.cqrs.core.file;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class FileStorageProperties {

    @Value("${file.upload-dir}")
    private String uploadDir;

}
