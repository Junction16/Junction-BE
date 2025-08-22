package junction.domain.s3.presentation.application.impl;

import junction.domain.s3.domain.entity.S3Video;
import junction.domain.s3.domain.entity.VideoType;
import junction.domain.s3.domain.repository.S3ImageRepository;
import junction.domain.s3.presentation.application.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class S3ServiceImpl implements S3Service {
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName; // 버킷 이름 설정

    @Value("${cloud.aws.region.static}")
    private String bucketRegion; // 지역 설정

    private final S3Client s3Client; // AmazonS3Client 대신 S3Client 사용

    private final S3ImageRepository s3ImageRepository;

    @Override
    public String storeFile(MultipartFile multipartFile, String name, String caption,
                            VideoType videoType, String sentence) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        // 원본 파일 이름 가져오기
        String originalFilename = multipartFile.getOriginalFilename(); // 예: video.mp4

        String keyFile = name + ".mp3"; // 또는 .mp3로 저장해도 무방

        // S3에 파일 업로드 (AWS SDK v2 방식)
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(keyFile)
                .contentType(multipartFile.getContentType())
                .build();

        s3Client.putObject(putObjectRequest,
                RequestBody.fromInputStream(multipartFile.getInputStream(), multipartFile.getSize()));

        // 업로드된 파일의 URL 생성
        String fileUrl = String.format("https://%s.s3.%s.amazonaws.com/%s",
                bucketName,               // S3 버킷 이름
                bucketRegion,             // S3 리전
                keyFile            // 저장된 파일 이름
        );

        S3Video s3Image = S3Video.builder()
                .name(name)
                .videoUrl(fileUrl)
                .caption(caption)
                .videoType(videoType)
                .sentence(sentence)
                .build();

        s3ImageRepository.save(s3Image);

        return fileUrl;
    }



}
