package junction.domain.s3.presentation.application.impl;

import junction.domain.record.domain.entity.Note;
import junction.domain.record.domain.repository.NoteRepository;
import junction.domain.s3.domain.entity.S3Choice;
import junction.domain.s3.domain.entity.S3Video;
import junction.domain.s3.domain.entity.VideoType;
import junction.domain.s3.domain.repository.S3ChoiceRepository;
import junction.domain.s3.domain.repository.S3VideoRepository;
import junction.domain.s3.presentation.application.S3Service;
import junction.domain.s3.presentation.dto.req.PostVideoReq;
import junction.domain.s3.presentation.dto.res.*;
import junction.domain.user.domain.entity.User;
import junction.domain.user.domain.repository.UserRepository;
import junction.global.infra.exception.error.ErrorCode;
import junction.global.infra.exception.error.JunctionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class S3ServiceImpl implements S3Service {
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName; // 버킷 이름 설정

    @Value("${cloud.aws.region.static}")
    private String bucketRegion; // 지역 설정

    private final S3Client s3Client; // AmazonS3Client 대신 S3Client 사용

    private final S3VideoRepository s3VideoRepository;

    private final S3ChoiceRepository s3ChoiceRepository;

    private final NoteRepository noteRepository;

    private final UserRepository userRepository;

    @Override
    public String storeFile(MultipartFile multipartFile, String successWord, String name, String caption,
                            VideoType videoType, String sentence, String compareWord) throws IOException {
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
                .successWord(successWord)
                .videoType(videoType)
                .sentence(sentence)
                .chat("")
                .compareWord(compareWord)
                .build();

        s3VideoRepository.save(s3Image);

        return fileUrl;
    }

    @Override
    public RandomQuiz randomQuizSelect(VideoType videoType) {
        // 타입별로 가져옴 아마 5개씩일거임
        List<S3Video> all = s3VideoRepository.findAllByVideoType(videoType);
        Collections.shuffle(all);

        S3Video s3Video = all.get(0);

        List<S3Choice> allS3Choice = s3ChoiceRepository.findAllByS3Video(s3Video);

        List<ChoiceRes> choiceResult = new ArrayList<>();

        for (S3Choice s3Choice : allS3Choice) {
            choiceResult.add(ChoiceRes.of(s3Choice.getDummyWord()));
        }

        return RandomQuiz.of(s3Video, choiceResult);
    }

    @Override
    public List<RandomHomeRes> randomHome() {

        List<S3Video> all = s3VideoRepository.findAll();
        Collections.shuffle(all);

        List<RandomHomeRes> result = new ArrayList<>();
        for (S3Video s3Video : all) {
            result.add(RandomHomeRes.of(s3Video.getVideoUrl(), s3Video.getChat()));
        }
        return result;
    }

    @Override
    public ClipRes clipSelect(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new JunctionException(ErrorCode.USER_NOT_EXIST));

        List<Note> allNote = noteRepository.findAllByUser(user);
        List<ClipVideoRes> videoRes = new ArrayList<>();
        for (Note note : allNote) {
            videoRes.add(ClipVideoRes.of(note.getS3Video().getVideoUrl()));
        }
        return ClipRes.of((long) allNote.size(), videoRes);

    }

    @Override
    public void postVideo(String userId, PostVideoReq req) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new JunctionException(ErrorCode.USER_NOT_EXIST));

        S3Video s3Video = s3VideoRepository.findById(req.videoId())
                .orElseThrow(() -> new JunctionException(ErrorCode.VIDEO_NOT_EXIST));

        Note note = Note.builder().user(user).s3Video(s3Video).build();
        noteRepository.save(note);
    }

}
