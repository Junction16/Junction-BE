package junction.domain.s3.presentation.application;

import junction.domain.s3.domain.entity.VideoType;
import junction.domain.s3.presentation.dto.req.PostVideoReq;
import junction.domain.s3.presentation.dto.res.ClipRes;
import junction.domain.s3.presentation.dto.res.RandomHomeRes;
import junction.domain.s3.presentation.dto.res.RandomQuizRes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface S3Service {
    String storeFile(MultipartFile multipartFile,  String successWord, String name, String caption,
                     VideoType videoType, String sentence, String compareWord, String chat, String korea) throws IOException;

    RandomQuizRes randomQuizSelect(VideoType videoType);


    List<RandomHomeRes> randomHome(String userId);

    ClipRes clipSelect(String userId);

    // 비디오 사용자 저장
    void postVideo(String userId, PostVideoReq req);

}
