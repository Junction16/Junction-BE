package junction.domain.s3.presentation.application;

import junction.domain.s3.domain.entity.VideoType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;



public interface S3Service {
    String storeFile(MultipartFile multipartFile, String name, String caption,
                     VideoType videoType, String sentence) throws IOException;
}
