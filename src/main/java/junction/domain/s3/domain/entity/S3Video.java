package junction.domain.s3.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class S3Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이름
    String name;

    // 예문
    private String sentence;

    // 비디오 링크
    private String videoUrl;

    // 비디오 타입 어떤 유형인지
    private VideoType videoType;

    // 캡션
    private String caption;

    @Builder
    public S3Video(String name, String sentence, String videoUrl, VideoType videoType, String caption ){
        this.name= name;
        this.sentence= sentence;
        this.videoUrl = videoUrl;
        this.videoType = videoType;
        this.caption = caption;
    }
}